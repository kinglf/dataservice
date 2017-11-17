package top.kinglf.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.kinglf.dataservice.common.model.Good;

public interface GoodRepository extends JpaRepository<Good,String> {

    @Query(value = "select count(*) FROM goods g where g.id=?1 AND g.project_id =?2",nativeQuery = true)
    int exists(String id,Long projectId);
}
