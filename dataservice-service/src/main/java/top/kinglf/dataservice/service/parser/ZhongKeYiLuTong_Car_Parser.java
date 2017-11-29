package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.utils.UcsDateUtils;

import java.util.Date;

public class ZhongKeYiLuTong_Car_Parser implements Parser<Car> {
    @Override
    public Car parser(KMessage msg) throws ParserException {
        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();
        try {
            JSONObject carJson= JSON.parseObject(context);
            Car car = new Car();
            car.setProjectId(projectId);
            String length = carJson.getString("length");
            try {
                car.setCarLength(Double.parseDouble(length.replace("米","")));
            }catch (Exception e){

            }
            car.setContact(carJson.getString("name"));
            String carNumber = carJson.getString("carNumber");
            String phone = carJson.getString("phone");
            car.setDataId((carNumber+phone).hashCode()+"");
            car.setCarCardNo(carNumber);
            car.setTel1(phone);
            car.setCompany(carJson.getString("companyName"));
            car.setCrawlDate(crawlDate);
            Date publishDate= UcsDateUtils.stringToDate(carJson.getString("date"));
            car.setPublishDate(publishDate);

            return car;
        }catch (Exception e){
            throw new ParserException(e);
        }
    }
}
