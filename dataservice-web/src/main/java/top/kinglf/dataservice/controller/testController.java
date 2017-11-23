package top.kinglf.dataservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.model.Project;
import top.kinglf.dataservice.repository.ProjectRepository;
import top.kinglf.dataservice.service.db.CarService;
import top.kinglf.dataservice.service.parser.ParserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test/*")
@EnableSpringConfigured
public class testController {
    @Autowired
    ParserService parserService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CarService carService;
    @RequestMapping("parser")
    public void testParser(){
        try {
            parserService.parser(new KMessage());
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("projectList")
    public void projectList(){
        List<Project> list = projectRepository.findHasParserProject();
        System.out.println(JSON.toJSONString(list));
    }
    @RequestMapping("find") //13位时间戳
    public void findCar(long pid, long sDate){
        if(String.valueOf(sDate).length()==10){
            sDate=sDate*1000;
        }
        Date startDate=new Date(sDate);
        List<Car> carList = carService.getCarList(pid, startDate);
        for(Car car:carList){
            System.out.println(JSON.toJSONString(car));
        }
    }
}
