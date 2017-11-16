package top.kinglf.dataservice.service.jms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;

import java.util.List;

@Component
public class RocketMQServiceImpl implements JMSService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RocketMQServiceImpl.class);
    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize; //4M
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;
    @Value("${rocketmq.producer.topic}")
    private String topic;
    private DefaultMQProducer producer;

    public RocketMQServiceImpl() {
    }

    private Message kMsg2Msg(KMessage kMessage) {
        return new Message(topic, kMessage.getProjectId() + "", kMessage.getId(), JSONObject.toJSONString(kMessage).getBytes());
    }

    @Override
    public void send(KMessage msg) throws JMSException {
        try {
            SendResult sendResult = getProducer().send(kMsg2Msg(msg));
            LOGGER.debug("JMS"+JSONObject.toJSONString(sendResult));
        } catch (MQClientException e) {
            throw new JMSException(e);
        } catch (RemotingException e) {
            throw new JMSException(e);
        } catch (MQBrokerException e) {
            throw new JMSException(e);
        } catch (InterruptedException e) {
            throw new JMSException(e);
        }
    }

    @Override
    public void send(List<KMessage> kMsgList) throws JMSException {
        for (KMessage kMessage : kMsgList) {
            send(kMessage);
        }
    }

    public DefaultMQProducer getProducer() throws JMSException {
        if (producer != null) {
            return producer;
        }
        if (StringUtils.isBlank(this.groupName)) {
            throw new JMSException("groupName is blank");
        }
        if (StringUtils.isBlank(this.namesrvAddr)) {
            throw new JMSException("nameServerAddr is blank");
        }
        if (StringUtils.isBlank(this.instanceName)) {
            throw new JMSException("instanceName is blank");
        }

        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        producer.setSendMessageWithVIPChannel(false);
        try {
            producer.start();
            LOGGER.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            LOGGER.error(String.format("producer is error {}"
                    , e.getMessage(), e));
            throw new JMSException(e);
        }
        return producer;

    }
}
