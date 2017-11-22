package top.kinglf.dataservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.repository.CarRepository;
import top.kinglf.dataservice.repository.GoodRepository;
@Component
public class CarService {
    @Autowired
    CarRepository carRepository;

    public boolean saveIfNotExist(Car car) {
        try {
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
