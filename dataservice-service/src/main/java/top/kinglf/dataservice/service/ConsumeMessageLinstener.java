package top.kinglf.dataservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.service.jms.JMSService;
import top.kinglf.dataservice.service.parser.ParserService;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class ConsumeMessageLinstener implements MessageListenerConcurrently {
    @Autowired
    private ParserService parserService;
    @Autowired
    private JMSService jmsService;
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msg : list) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(new String(msg.getBody(), "UTF-8"));
                KMessage kMessage = JSONObject.toJavaObject(jsonObject, KMessage.class);
                parserService.parser(kMessage);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                try {
                    jmsService.send(msg);
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
