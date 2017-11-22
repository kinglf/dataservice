package top.kinglf.dataservice.service;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.kinglf.dataservice.common.model.Project;
import top.kinglf.dataservice.repository.ProjectRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    private  Map<Integer,Project> canParserProjectMap;
    public  String getCanParserProjectIDs(boolean isSync){
        if(isSync){
            syncCanParserProjectMap();
        }
        Set<Integer> idSet = canParserProjectMap.keySet();
        return Joiner.on("||").join(idSet);
    }
    public  void syncCanParserProjectMap(){
        List<Project> hasParserProject = projectRepository.findHasParserProject();
        canParserProjectMap=new HashMap<>();
        for(Project project:hasParserProject){
            canParserProjectMap.put((int) project.getId(),project);
        }
    }

    public  Map<Integer, Project> getCanParserProjectMap() {
        return canParserProjectMap;
    }

}
