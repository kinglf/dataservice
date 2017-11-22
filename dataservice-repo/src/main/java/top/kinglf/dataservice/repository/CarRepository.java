package top.kinglf.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.kinglf.dataservice.common.model.Car;

public interface CarRepository extends JpaRepository<Car,String> {
}
