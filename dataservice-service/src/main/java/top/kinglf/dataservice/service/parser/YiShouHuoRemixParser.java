package top.kinglf.dataservice.service.parser;

import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;

public class YiShouHuoRemixParser implements Parser<Good>{
    @Override
    public Good parser(KMessage msg) {
        return new Good();
    }
}
