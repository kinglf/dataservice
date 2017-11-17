package top.kinglf.dataservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.repository.GoodRepository;

@Component
public class GoodService {
    @Autowired
    GoodRepository goodRepository;

    public boolean saveIfNotExist(Good good) {
//        goodRepository.
        int exists = goodRepository.exists(good.getId(), good.getProjectId());
        System.out.println("存在不存在="+exists);
        if (exists > 0) {
            System.out.println("不陈宫");
            return false;
        } else {
            goodRepository.save(good);
            System.out.println("保存成功");
            return true;
        }
    }
}
