package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.DateUtil;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.utils.TelUtils;
import top.kinglf.dataservice.common.utils.UcsDateUtils;

import java.util.ArrayList;
import java.util.Date;

public class BanCheXiongDiGoodParser implements Parser<Good> {
    @Override
    public Good parser(KMessage msg) throws ParserException {
        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();
        String type = msg.getType();
        try {
            JSONObject goodJson = JSON.parseObject(context);
            Good good=new Good();
            good.setProjectId(projectId);
            good.setGoodType(type);
            good.setStartArea(goodJson.getString("startAddress"));
            good.setEndArea(goodJson.getString("endAddress"));
            good.setContent(goodJson.getString("goodsDescription"));
            ArrayList<String> telList = TelUtils.getTelList(goodJson.getString("phone"));
            good.setTel1(telList.get(0));
            good.setTel2(telList.get(1));
            good.setTel3(telList.get(2));
            good.setTel4(telList.get(3));
            good.setTel5(telList.get(4));

            Date createDate = UcsDateUtils.stringToDate("2017-" + goodJson.getString("createDate") + ":00");
            good.setPublishDate(createDate);
            good.setCrawlDate(crawlDate);
            good.setDataId(goodJson.getString("sendgoodsId"));
            return good;

        }catch (Exception e){
            throw new ParserException(e);
        }
    }
}
