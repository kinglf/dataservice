package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.utils.TelUtils;
import top.kinglf.dataservice.common.utils.UcsDateUtils;

import java.util.ArrayList;
import java.util.Date;

public class YiShouHuo_Remix_Parser implements Parser<Good> {
    private int projectId = 2;


    /**
     * {
     * "text": "北京房山(燕尊)→河北保定需6.8米高护栏车1辆.",
     * "status": 0,
     * "telAreaCode": "010",
     * "orderNum": 1363393,
     * "andToCity": "北京",
     * "date": "2017-11-08 16:25:08",
     * "org": "",
     * "type": "car",
     * "toArea": "",
     * "userAddress": "",
     * "tels": [
     * "010-61200034"
     * ],
     * "_id": "5a02bf64cd5cbe110be575a5",
     * "userId": "_GL",
     * "userName": "",
     * "toCity": "",
     * "fromArea": "",
     * "postCity": "北京",
     * "fromCity": "北京",
     * "channel": "guolian"
     * }
     *
     * @param msg
     * @return
     */
    @Override
    public Good parser(KMessage msg) throws ParserException {
        try {
            Good good = new Good();
            Date crawlDate = msg.getCrawlDate();
            JSONObject jsonObject = JSONObject.parseObject(msg.getContext());
            String context = jsonObject.getString("text");
            String id=jsonObject.getString("orderNum");
            String endCity=jsonObject.getString("postCity");
            String fromCity=jsonObject.getString("fromCity");
            Date publishDate= UcsDateUtils.stringToDate(jsonObject.getString("date"));
            ArrayList<String> telList = TelUtils.getTelList(jsonObject.getString("tels"));
            good.setProjectId(msg.getProjectId()==0?projectId:msg.getProjectId());
            good.setTel1(telList.get(0));
            good.setTel2(telList.get(1));
            good.setTel3(telList.get(2));
            good.setTel4(telList.get(3));
            good.setTel5(telList.get(4));
            good.setDataId(id);
            good.setCrawlDate(crawlDate);
            good.setContent(context);
            good.setEndArea(endCity);
            good.setStartArea(fromCity);
            good.setPublishDate(publishDate);
            return good;

        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
