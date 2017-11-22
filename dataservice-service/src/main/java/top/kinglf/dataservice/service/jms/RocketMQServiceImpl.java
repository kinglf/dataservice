package top.kinglf.dataservice.service.jms;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.model.Project;
import top.kinglf.dataservice.service.ConsumeMessageLinstener;
import top.kinglf.dataservice.service.ProjectService;

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
    @Value("${rocketmq.consumer.groupName}")
    private String consumerGroupName;
    @Autowired
    private ConsumeMessageLinstener consumeMessageLinstener;
    private DefaultMQProducer producer;
    private DefaultMQPushConsumer consumer;

    public RocketMQServiceImpl() {
    }

    private Message kMsg2Msg(KMessage kMessage) {
        return new Message(topic, kMessage.getProjectId() + "", kMessage.getId(), JSONObject.toJSONString(kMessage).getBytes());
    }

    @Override
    public void send(Message msg) throws JMSException {
        try {
            SendResult send = getProducer().send(msg);
            LOGGER.debug("JMS" + JSONObject.toJSONString(send));
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
    public void send(KMessage msg) throws JMSException {
        Message message = kMsg2Msg(msg);
        send(message);
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

    public DefaultMQPushConsumer getConsumer() throws JMSException {
        if (consumer != null) {
            return consumer;
        }
        consumer = new DefaultMQPushConsumer(consumerGroupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        updateSubscribe();//设定订阅的消息
        consumer.registerMessageListener(consumeMessageLinstener);
        return consumer;
    }

    @Override
    public void startConsumerMessage() throws JMSException {
        if (consumer == null) {
            getConsumer();
            try {
                consumer.start();
            } catch (MQClientException e) {
                throw new JMSException(e);
            }
        } else {
            throw new JMSException("消费服务已经开启,不能重复启动");
        }
    }

    @Override
    public void stopConsumerMessage() throws JMSException {
        if (consumer == null) {
            throw new JMSException("消费服务不存在,无法停止");
        } else {
            consumer.shutdown();
            consumer = null;
        }
    }

    @Override
    public boolean getConsumerStatus() {
        if (consumer == null) {
            return false;
        }
        return true;
    }
    @Autowired
    private ProjectService projectService;

    @Override
    public void updateSubscribe() throws JMSException {
        String tags = projectService.getCanParserProjectIDs(true);
        if (getConsumerStatus()) {
            try {
                if (tags != null) {
                    getConsumer().subscribe(topic, tags);
                    System.out.println("更新tags="+tags);
                } else {
                    //-1为不订阅任何Tag
                    getConsumer().subscribe(topic, "-1");
                    LOGGER.error("从数据库中得到Tags错误,设定消费者订阅tags=-1,即不订阅,但是服务依旧启动");
                }
            } catch (MQClientException e) {
                throw new JMSException(e);
            }
        }else {
            throw new JMSException("消费服务未开启,请先开启服务");
        }
    }

}
