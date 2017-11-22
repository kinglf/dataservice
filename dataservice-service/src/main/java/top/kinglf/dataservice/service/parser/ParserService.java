package top.kinglf.dataservice.service.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.kinglf.dataservice.common.model.Car;
import top.kinglf.dataservice.common.model.Good;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.model.Project;
import top.kinglf.dataservice.repository.GoodRepository;
import top.kinglf.dataservice.service.ProjectService;
import top.kinglf.dataservice.service.db.GoodService;

import java.util.HashMap;
import java.util.Map;

@Component
public class ParserService {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private GoodService goodService;
    private Map<Long, Parser> serviceMap;


//    public ParserService(){
//        sync();
//    }

    public void parser(KMessage msg) {
        if(serviceMap==null){
            projectService.syncCanParserProjectMap();
            init();
        }
        Parser parser = getParser(msg.getProjectId());
        if (parser != null) {
            Object res = parser.parser(msg);
            if (res instanceof Good) {
                Good good= (Good) res;
                goodService.saveIfNotExist(good);
                //TODO 转成商品,然后用商品数据服务更新/存储
            } else if (res instanceof Car) {
                //TODO 转成车主,然后用商品数据服务更新/存储
            } else {
                //TODO 异常,记录在日志中
            }
            //TODO 解析完成后存储
        }else {
            //TODO 无解析器属于异常行为,记录日志
        }

    }

    public void init() {
        Map<Integer, Project> canParserProjectMap = projectService.getCanParserProjectMap();
        serviceMap=new HashMap<>();
        for (Project project : canParserProjectMap.values()) {
            String parserName = project.getParser();
            long id = project.getId();
            Parser parser = getParser(parserName);
            if (parser != null) {
                serviceMap.put(id, parser);
            }
        }
    }

    public void sync() {
        try{
        projectService.syncCanParserProjectMap();
        init();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Parser getParser(long projectId) {
        return serviceMap.get(projectId);
    }

    private Parser getParser(String className) {
        String fullName = getClass().getPackage().getName() + "." + className;
        try {
            return (Parser) Class.forName(fullName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
