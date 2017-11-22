package top.kinglf.dataservice.service.parser;

import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.KMessage;

public interface Parser<T> {
    /**
     * Plan A:每一业务的每一类做单独的解析器,根据type去做区分  存储在Map中.返回对象不确定.. 通过instof判定
     */
    public T parser(KMessage msg) throws ParserException;
}
