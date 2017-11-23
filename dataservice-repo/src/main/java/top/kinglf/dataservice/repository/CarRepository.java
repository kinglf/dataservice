package top.kinglf.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.kinglf.dataservice.common.model.Car;

import java.util.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value = "select c from Car c where c.projectId=?1 and c.crawlDate>?2 and c.crawlDate<?3")
    List<Car> getCarsByDate(long projectId, Date startDate, Date endDate);
}
