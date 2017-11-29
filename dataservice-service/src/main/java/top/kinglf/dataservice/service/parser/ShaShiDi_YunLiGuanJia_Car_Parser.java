package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.utils.TelUtils;
import top.kinglf.dataservice.common.utils.UcsDateUtils;

import java.util.Date;

public class ShaShiDi_YunLiGuanJia_Car_Parser implements Parser<Car> {
    @Override
    public Car parser(KMessage msg) throws ParserException {
        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();
        try {
            JSONObject carJson = JSON.parseObject(context);
            Car car = new Car();
            car.setProjectId(projectId);
            car.setCrawlDate(crawlDate);
            car.setDataId(carJson.getString("cyz_id"));
            car.setCarLength(carJson.getDoubleValue("vlength"));
            car.setCarType(carJson.getString("car_type"));
            car.setContact(carJson.getString("cyz_name"));
            car.setCarWeight(carJson.getDoubleValue("car_load"));
            Date date = UcsDateUtils.stringToDate(carJson.getString("apply_time"));
            car.setPublishDate(date);
            TelUtils.putTels2Bean(car,carJson.getString("cyz_tel"));
            return car;
        }catch (Exception e){
            throw new ParserException(e);
        }
    }
}
