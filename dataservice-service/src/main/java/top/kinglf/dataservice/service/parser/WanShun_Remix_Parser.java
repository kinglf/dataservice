package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.utils.TelUtils;
import top.kinglf.dataservice.common.utils.UcsDateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WanShun_Remix_Parser implements Parser<Good> {
    private int projectId = 1;

    //
    @Override
    public Good parser(KMessage msg) throws ParserException {
        try {
            JSONObject jsonObject = JSONObject.parseObject(msg.getContext());
            String id = jsonObject.getString("id");
            String tel = (String) jsonObject.getOrDefault("tel", "");
            String context = (String) jsonObject.getOrDefault("context", jsonObject.toJSONString());
            String pubdate = (String) jsonObject.getOrDefault("date", "");
            Date crawlDate = msg.getCrawlDate();
            String type = (String) jsonObject.getOrDefault("type", "货物");
            String city = (String) jsonObject.getOrDefault("city", "未知");
            Good good = new Good();
            good.setDataId(id);
            good.setProjectId(msg.getProjectId()==0?projectId:msg.getProjectId());
            ArrayList<String> telList = TelUtils.getTelList(tel);
            good.setTel1(telList.get(0));
            good.setTel2(telList.get(1));
            good.setTel3(telList.get(2));
            good.setTel4(telList.get(3));
            good.setTel5(telList.get(4));
            good.setContent(context);
            good.setCrawlDate(crawlDate);
            try {
                Date publishDate = UcsDateUtils.megerDate(crawlDate, pubdate);
                good.setPublishDate(publishDate);
            } catch (ParseException e) {
                Date date = UcsDateUtils.stringToDate(pubdate);
                if (date == null) {
                    good.setPublishDate(crawlDate);
                }
            }
            good.setGoodType(type);
            good.setGoodName(type);
            good.setStartArea(city);
            return good;
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
