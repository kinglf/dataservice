import org.junit.Test;
import top.kinglf.dataservice.common.exception.ParserException;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.service.parser.Parser;
import top.kinglf.dataservice.service.parser.WanShun_Remix_Parser;

public class testParser {
    @Test
    public void testT(){
        Parser parser=new WanShun_Remix_Parser();
        Object parser1 = null;
        try {
            parser1 = parser.parser(new KMessage());
        } catch (ParserException e) {
            e.printStackTrace();
        }
        if(parser1 instanceof Good){
            System.out.println("货物信息");
        }else if(parser1 instanceof Car) {
            System.out.println("车主信息");
        }else{
            System.out.println("other");
        }
    }
}
