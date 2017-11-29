package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;

import java.util.Date;

public class TianTianYouHuo_Good_Parser implements Parser<Good> {
    /**
     * {
     "provideUserCompanyVerify": true,
     "providerFixPhoneNo": "13162316166",
     "valid": 1,
     "createTime": "7秒前",
     "packTypeStr": "袋装",
     "lastUpdateTime": "7秒前",
     "provideUserVerifyFlag": 5,
     "providerJobPosition": "负责人",
     "provideUserDriverVerify": false,
     "identity": 1,
     "providerCompany": "上海连桥货运代理有限公司",
     "useTypeStr": "整车",
     "totalProductCount": 104768,
     "provideUserImgUrl": "http://ttyhuo-img.b0.upaiyun.com/2016/03/03/02/50_47_upload_usericonimage_file34.png",
     "providerUserName": "天天有货刘晓春",
     "payTypeStr": "预付部分",
     "class": "class com.ttyhuo.core.vo.ProductForList",
     "alreadyFavorite": false,
     "loadingTypeStr": "铲车、叉车",
     "product": {
     "toLng": "0",
     "packTypeStr": "袋装",
     "truckTypeStr": "厢式",
     "autoCheckFlagTemp": 0,
     "spaceNeeded": null,
     "provideUserMobileNo": "13162316166",
     "loadProTime": "2017-11-28 不限",
     "description": "快运/快递货",
     "isClosed": false,
     "fromcityscore": 0,
     "tocityscore": 0,
     "pcRelease": 0,
     "status": 2,
     "toAddr": "",
     "departureTime": 1511859600000,
     "class": "class com.ttyhuo.api.model.disused.ProductWithWindow",
     "isExpired": false,
     "createTimeStr": null,
     "toLat": "0",
     "productCount": null,
     "payType": 2,
     "theBase": 0,
     "theBaseStr": null,
     "alreadyFellow": null,
     "fromCity": "浙江 杭州 萧山区",
     "totalscore": 0,
     "createTime": 1511923221000,
     "valid": 1,
     "memo": "货主在等,长期有货,价高急走,好装好卸,结款快速(预付部分其余回单结算)",
     "truckType": 5,
     "productVolume": 0,
     "truckLength": 9.6,
     "loadingType": 2,
     "productID": 47629039,
     "identity": 1,
     "distance": null,
     "title": "快运/快递货",
     "useTypeStr": "整车",
     "autoCheckFlag": 0,
     "fromLat": "0",
     "fromAddr": "",
     "useType": 1,
     "truckVerifyStatus": 0,
     "payTypeStr": "预付部分",
     "loadLimit": 0,
     "packType": 4,
     "loadingTypeStr": "铲车、叉车",
     "userVerifyStatus": 0,
     "arrivalTime": 1511923221000,
     "source": null,
     "companyVerifyStatus": 0,
     "toCity": "浙江 杭州 萧山区",
     "identityStatus": 1,
     "fromLng": "0",
     "productName": "快运/快递货",
     "provideUserID": 75768
     },
     "tableMatchInfo": null,
     "source": 0,
     "provideUserSfzVerify": true,
     "recommendCount": null,
     "identityStatus": 1,
     "isMyProduct": false
     }
     * @param msg
     * @return
     * @throws ParserException
     */
    @Override
    public Good parser(KMessage msg) throws ParserException {
        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();//9
        try {
            JSONObject goodJson = JSON.parseObject(context);
            JSONObject productJson = goodJson.getJSONObject("product");
            Good good = new Good();
            good.setProjectId(projectId);
            good.setCrawlDate(crawlDate);
            good.setTel1(goodJson.getString("providerFixPhoneNo"));
            good.setCompany(goodJson.getString("providerCompany"));
            good.setContactName(goodJson.getString("providerUserName"));
            good.setGoodName(productJson.getString("description"));
            good.setGoodType(productJson.getString("description"));
            good.setGoodWeight(productJson.getString("loadLimit"));
            good.setCarLength(productJson.getDoubleValue("truckLength"));
            good.setCarType(productJson.getString("truckTypeStr"));
            good.setContent(productJson.getString("description"));
            good.setRemark(productJson.getString("memo"));
            good.setPublishDate(new Date(productJson.getLongValue("createTime")));
            good.setTitle(productJson.getString("title"));
            good.setDataId(productJson.getString("productID"));
            good.setStartArea(productJson.getString("fromCity"));
            good.setEndArea(productJson.getString("toCity"));
            good.setTel2(productJson.getString("provideUserMobileNo"));
            return good;

        }catch (Exception e){
            throw new ParserException(e);
        }
    }
}
