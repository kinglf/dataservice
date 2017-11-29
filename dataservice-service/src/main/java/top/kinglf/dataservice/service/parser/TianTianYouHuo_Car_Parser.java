package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.utils.ExcelUtils;
import top.kinglf.dataservice.common.utils.UcsDateUtils;

import java.util.Date;

public class TianTianYouHuo_Car_Parser implements Parser<Car>{
    /**
     * {
     "identity": 3,
     "isExpired": null,
     "tableMatchInfo": null,
     "hasProduct": true,
     "isMyRoute": false,
     "class": "class com.ttyhuo.core.vo.UserForListItem",
     "userWithLatLng": {
     "valid": 1,
     "createTime": "2017-11-28",
     "truckID": 341400,
     "fromCities": "河南 郑州 中牟县",
     "truckType": 4,
     "truckTypeStr": "高栏",
     "orderByTime": 1511912134000,
     "truckLength": 4.2,
     "lng": "113.926852",
     "sfzVerify": true,
     "driverVerify": true,
     "routeTimeStr": "96分钟前",
     "faceImgUrl": "http://ttyhuo-img.b0.upaiyun.com/2017/11/28/05/43_05_usericonimage_5f0f7022ffab84ac_upload_picture.png",
     "licensePlate": "豫APQ260",
     "truckTagStr": "普通",
     "routeTime": 1511912134000,
     "dirverPoneNo": "15893170570",
     "identity": 3,
     "distance": null,
     "truckTag": 0,
     "loadProTime": "2017-11-29 不限",
     "primary": 1069344,
     "referType": 0,
     "userName": "张家保",
     "truckInfo": null,
     "identityName": "张家保",
     "createDate": "20小时前",
     "lat": "34.7277",
     "companyVerify": false,
     "loadLimit": 8,
     "class": "class com.ttyhuo.core.vo.UserWithLatLng",
     "latlngDate": "96分钟前",
     "routeID": 41347519,
     "alreadyFavorite": false,
     "orderByTimeStr": "96分钟前",
     "imgUrl": "http://ttyhuo-img.b0.upaiyun.com/2017/11/28/05/43_05_usericonimage_5f0f7022ffab84ac_upload_picture.png",
     "dirverName": "张家保",
     "userID": 1069344,
     "toCities": "河南 郑州 中牟县",
     "company": "",
     "truckStatusMsg": "",
     "identityStatus": 1,
     "alreadyFellow": false,
     "mobileNo": "15893170570"
     },
     "totalRouteInfoCount": 1,
     "recommendCount": 0,
     "identityStatus": 1,
     "isClosed": null
     }
     * @param msg
     * @return
     * @throws ParserException
     */
    @Override
    public Car parser(KMessage msg) throws ParserException {

        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();//9
        try {
            JSONObject carJson = JSON.parseObject(context).getJSONObject("userWithLatLng");
            Car car = new Car();
            car.setProjectId(projectId);
            car.setCrawlDate(crawlDate);
            car.setContent(context);
            car.setPublishDate(new Date(carJson.getLong("orderByTime")));
            car.setDataId(carJson.getString("userID")+"-"+carJson.getString("truckID"));
            car.setStartArea(carJson.getString("fromCities"));
            car.setEndArea(carJson.getString("toCities"));
            car.setCarType(carJson.getString("truckTypeStr"));
            car.setCarCardNo(carJson.getString("licensePlate"));
            car.setTel1(carJson.getString("mobileNo"));
            car.setTel2(carJson.getString("dirverPoneNo"));
            car.setContact(carJson.getString("identityName"));
            car.setCarLength(carJson.getDoubleValue("truckLength"));
            car.setCarWeight(carJson.getDoubleValue("loadLimit"));
            car.setCompany(carJson.getString("company"));
            return car;
        }catch (Exception e){
            throw new ParserException(e);
        }
    }
}
