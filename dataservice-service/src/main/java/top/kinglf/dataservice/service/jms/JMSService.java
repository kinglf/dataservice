package top.kinglf.dataservice.service.jms;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.Message;
import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;

import java.util.List;

public interface JMSService {
    void send(Message msg) throws JMSException;
    void send(KMessage msg) throws JMSException;
    void send(List<KMessage> kMsgList) throws JMSException;
    void startConsumerMessage() throws JMSException;
    void stopConsumerMessage() throws JMSException;
    boolean getConsumerStatus();
    void updateSubscribe() throws JMSException;
}
