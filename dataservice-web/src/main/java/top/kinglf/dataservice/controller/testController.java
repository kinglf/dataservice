package top.kinglf.dataservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kinglf.dataservice.common.model.KMessage;
import top.kinglf.dataservice.common.model.Project;
import top.kinglf.dataservice.repository.ProjectRepository;
import top.kinglf.dataservice.service.parser.ParserService;

import java.util.List;

@RestController
@RequestMapping("/test/*")
@EnableSpringConfigured
public class testController {
    @Autowired
    ParserService parserService;
    @Autowired
    ProjectRepository projectRepository;
    @RequestMapping("parser")
    public void testParser(){
        parserService.parser(new KMessage());
    }
    @RequestMapping("projectList")
    public void projectList(){
        List<Project> list = projectRepository.findHasParserProject();
        System.out.println(JSON.toJSONString(list));
    }
}
