package top.kinglf.dataservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.repository.CarRepository;
import top.kinglf.dataservice.repository.GoodRepository;

import java.util.Date;
import java.util.List;

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
    public List<Car> getCarList(long projectId, Date startDate, Date endDate){

        return carRepository.getCarsByDate(projectId,startDate,endDate);
    }

    public List<Car> getCarList(long project_id,Date startDate){
        return getCarList(project_id,startDate,new Date());
    }
}
