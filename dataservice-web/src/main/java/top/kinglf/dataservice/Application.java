package top.kinglf.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        //TODO mongodb存储原始数据
        //TODO Exception控制   AOP加日志埋点
        //TODO 日志存在mongodb

        //TODO APP 自动翻页，自动切换城市
    }
}
