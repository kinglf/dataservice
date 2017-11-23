package top.kinglf.dataservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.service.db.CarService;
import top.kinglf.dataservice.service.db.GoodService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/data/*")
public class DownloadController {
    @Autowired
    CarService carService;
    @Autowired
    GoodService goodService;

    @RequestMapping(value = "searchCar")
    public @ResponseBody
    String getCarList(long pid, long sDate, long eDate) {
        if(String.valueOf(sDate).length()==10){
            sDate=sDate*1000;
        }
        if(String.valueOf(eDate).length()==10){
            eDate=eDate*1000;
        }
        List<Car> carList;
        if (eDate == 0) {
            carList=carService.getCarList(pid,new Date(sDate));
        }else {
            carList=carService.getCarList(pid,new Date(sDate),new Date(eDate));
        }
        return JSON.toJSONString(carList);
    }
    @RequestMapping(value = "searchGood")
    public @ResponseBody
    String getGoodList(long pid, long sDate, long eDate) {
        if(String.valueOf(sDate).length()==10){
            sDate=sDate*1000;
        }
        if(String.valueOf(eDate).length()==10){
            eDate=eDate*1000;
        }
        List<Good> goodList;
        if (eDate == 0) {
            goodList=goodService.getGoodList(pid,new Date(sDate));
        }else {
            goodList=goodService.getGoodList(pid,new Date(sDate),new Date(eDate));
        }
        return JSON.toJSONString(goodList);
    }

}
