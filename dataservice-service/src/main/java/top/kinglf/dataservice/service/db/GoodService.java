package top.kinglf.dataservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.repository.GoodRepository;

import java.util.Date;
import java.util.List;

@Component
public class GoodService {
    @Autowired
    GoodRepository goodRepository;

    public boolean saveIfNotExist(Good good) {
//        goodRepository.
//        int exists = goodRepository.exists(good.getDataId(), good.getProjectId());
//        if (exists > 0) {
//            return false;
//        } else {
        try {
            goodRepository.save(good);
            System.out.println("保存成功");
            return true;
        } catch (Exception e) {
            return false;
        }
//        }
    }
    public List<Good> getGoodList(long projectId, Date startDate, Date endDate){

        return goodRepository.getGoodsByDate(projectId,startDate,endDate);
    }

    public List<Good> getGoodList(long project_id,Date startDate){
        return getGoodList(project_id,startDate,new Date());
    }
}
