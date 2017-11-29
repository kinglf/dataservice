import org.junit.Test;
import top.kinglf.dataservice.common.TableField;
import top.kinglf.dataservice.common.model.Car;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestAnnotation {
    @Test
    public void test(){
        Field[] fields = Car.class.getDeclaredFields();
        Car car=new Car();
        car.setId(1);
        Method[] methods = Car.class.getMethods();
       for(Field field:fields){
           System.out.println(field.getName());
       }


    }

}
