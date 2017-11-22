import org.junit.Test;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.service.parser.Parser;
import top.kinglf.dataservice.service.parser.WanShunRemixParser;

public class testParser {
    @Test
    public void testT(){
        Parser parser=new WanShunRemixParser();
        Object parser1 = parser.parser(new KMessage());
        if(parser1 instanceof Good){
            System.out.println("货物信息");
        }else if(parser1 instanceof Car) {
            System.out.println("车主信息");
        }else{
            System.out.println("other");
        }
    }
}
