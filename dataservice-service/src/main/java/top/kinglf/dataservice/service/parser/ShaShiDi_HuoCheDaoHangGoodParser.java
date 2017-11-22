package top.kinglf.dataservice.service.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;

import java.util.Date;

/**
 *
 */
public class ShaShiDi_HuoCheDaoHangGoodParser implements Parser<Good>{
    @Override
    public Good parser(KMessage msg) throws ParserException {
        String context = msg.getContext();
        Date crawlDate = msg.getCrawlDate();
        long projectId = msg.getProjectId();
        String type = msg.getType();
        try {
            JSONObject json = JSON.parseObject(context);
            Good good = new Good();
            good.setProjectId(projectId);
            good.setCrawlDate(crawlDate);
            //TODO 沙师弟货车导航解析没写完
        }catch (Exception e){
            throw new ParserException(e);
        }
        return null;
    }
}
