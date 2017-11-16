package top.kinglf.dataservice.service.jms;


import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;

import java.util.List;

public interface JMSService {
    void send(KMessage msg) throws JMSException;
    void send(List<KMessage> kMsgList) throws JMSException;
}
