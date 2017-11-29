package top.kinglf.dataservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.utils.ExcelUtils;
import top.kinglf.dataservice.common.utils.UcsDateUtils;
import top.kinglf.dataservice.service.db.CarService;
import top.kinglf.dataservice.service.db.GoodService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    String getCarListStr(long pid, long sDate, long eDate) {
        List<Car> carList = getCarList(pid, sDate, eDate);
        return JSON.toJSONString(carList);
    }

    public List<Car> getCarList(long pid, long sDate, long eDate) {
        if (String.valueOf(sDate).length() == 10) {
            sDate = sDate * 1000;
        }
        if (String.valueOf(eDate).length() == 10) {
            eDate = eDate * 1000;
        }
        List<Car> carList;
        if (eDate == 0) {
            carList = carService.getCarList(pid, new Date(sDate));
        } else {
            carList = carService.getCarList(pid, new Date(sDate), new Date(eDate));
        }
        return carList;
    }

    @RequestMapping(value = "searchGood")
    public @ResponseBody
    String getGoodListStr(long pid, long sDate, long eDate) {
        List<Good> goodList = getGoodList(pid, sDate, eDate);
        return JSON.toJSONString(goodList);
    }

    public List<Good> getGoodList(long pid, long sDate, long eDate) {
        if (String.valueOf(sDate).length() == 10) {
            sDate = sDate * 1000;
        }
        if (String.valueOf(eDate).length() == 10) {
            eDate = eDate * 1000;
        }
        List<Good> goodList;
        if (eDate == 0) {
            goodList = goodService.getGoodList(pid, new Date(sDate));
        } else {
            goodList = goodService.getGoodList(pid, new Date(sDate), new Date(eDate));
        }
        return goodList;
    }


    @RequestMapping(value = "download")
    public @ResponseBody
    String downloadData(String type, long pid, long sDate, long eDate) {
        String sDateStr = UcsDateUtils.dateToString(new Date(sDate), UcsDateUtils.F10);
        String eDateStr = UcsDateUtils.dateToString(new Date(eDate), UcsDateUtils.F10);
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        if ("car".equals(type)) {
            path += "ExportXls/car-" + pid + "-" + sDateStr + "-" + eDateStr + ".xlsx";
            List<Car> carList = getCarList(pid, sDate, eDate);
            ExcelUtils.saveBeanList2Excel(path, carList);
            return path;
        } else if ("good".equals(type)) {
            path += "ExportXls/good-" + pid + "-" + sDateStr + "-" + eDateStr + ".xlsx";
            List<Good> goodList = getGoodList(pid, sDate, eDate);
            ExcelUtils.saveBeanList2Excel(path, goodList);
            return path;
        } else {
            return "error";
        }
    }


}
