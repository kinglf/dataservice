package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.KMessage;

import java.util.Date;

public class WuTong_WuTongWangPeiHuoJingJiRenCarParser implements Parser<Car> {
    @Override
    public Car parser(KMessage msg) throws ParserException {
        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();
        try {
            JSONObject carJson = JSON.parseObject(context);
            Car car = new Car();
            car.setCrawlDate(crawlDate);
            car.setProjectId(projectId);
            car.setDataId(carJson.getString("custId") + "-" + carJson.getString("cheid"));
            car.setCarCardNo(carJson.getString("carNumber"));
            car.setCarType(carJson.getString("carType"));
            car.setCarLength(Double.parseDouble(carJson.getString("carLength").replace("米", "")));
            car.setCarWeight(Double.parseDouble(carJson.getString("carLoad").replace("吨", "")));
            car.setContact(carJson.getString("carContect"));
            car.setTel1(carJson.getString("carPhone"));
            car.setTel2(carJson.getString("carOwnerPhone"));
            String[] carOfenRunLine = carJson.getString("carOfenRunLine").split("→");
            if (carOfenRunLine.length == 2) {
                car.setStartArea(carOfenRunLine[0]);
                car.setEndArea(carOfenRunLine[1]);
            } else {
                car.setStartArea(carJson.getString("carOfenStation"));
                car.setEndArea(carJson.getString("carOfenRunLine"));
            }
            car.setCompany(carJson.getString("carPublisher"));
            car.setRemark(carJson.getString("carPic"));

            return car;
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
