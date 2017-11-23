package top.kinglf.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;

import java.util.Date;
import java.util.List;

public interface GoodRepository extends JpaRepository<Good,Long> {

    @Query(value = "select count(*) FROM goods g where g.id=?1 AND g.project_id =?2",nativeQuery = true)
    int exists(String id,Long projectId);
    @Query(value = "select c from Good c where c.projectId=?1 and c.crawlDate>?2 and c.crawlDate<?3")
    List<Good> getGoodsByDate(long projectId, Date startDate, Date endDate);
}
