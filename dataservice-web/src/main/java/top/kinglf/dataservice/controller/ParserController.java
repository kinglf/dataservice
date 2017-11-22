package top.kinglf.dataservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kinglf.dataservice.common.exception.JMSException;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.service.jms.JMSService;

@RestController
@RequestMapping("/parser/*")
public class ParserController {
    @Autowired
    JMSService jmsService;

    @RequestMapping("start")
    public String start() {
        try {
            jmsService.startConsumerMessage();
            return "服务开启成功";
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "服务开启失败";
    }

    @RequestMapping("updateTags")
    public String change() {
        try {
            jmsService.updateSubscribe();
            return "ok";
        } catch (JMSException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("addTestData")
    public String add(String flag) {
        for (int i = 0; i < 10; i++) {
            KMessage kMsg = new KMessage();
            kMsg.setProjectId(Long.parseLong(flag));
            kMsg.setContext("asdlkasdkljasdkl");
            try {
                jmsService.send(kMsg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        System.out.println("添加10条flag=" + flag + "的信息");
        return "";
    }
    /**
     *重置消费位置功能在后续添加,先在console中操作
     */

}
