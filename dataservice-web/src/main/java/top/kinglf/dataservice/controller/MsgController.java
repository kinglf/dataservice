package top.kinglf.dataservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.service.jms.JMSService;

import java.util.List;

@Controller
@RequestMapping("/msg/*")
public class MsgController {
    @Autowired
    private JMSService jmsService;

    /**
     * 接收消息
     * @param data
     * @return
     */
    @RequestMapping(value = "receives", method = RequestMethod.POST)
    public @ResponseBody
    String receives(String data) {
        try {

            List<KMessage> kMessages = JSON.parseArray(data, KMessage.class);
            System.out.println("接收到数据" + kMessages.size() + "----" + data);
            try {
                jmsService.send(kMessages);
                return "ok";
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

}
