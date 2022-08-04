package jp.cloudace.backend.todoapp.infra.controller;

import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import jp.cloudace.backend.todoapp.usecase.Hello;
import jp.cloudace.backend.todoapp.usecase.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final Hello hello;
    private final Tasks tasks;

    public MainController(Hello hello, Tasks tasks) {
        this.hello = hello;
        this.tasks = tasks;
    }

    @GetMapping("/tasks")
    public List<TasksEntity> getTaskList(HttpServletRequest request) {
        logger.debug("MainController#getTaskList");
        return tasks.getTaskList();
    }

    @GetMapping("/")
    public String helloCloud(HttpServletRequest request) {
        logger.debug("DemoController#HelloCloud");
        return hello.action();
    }

    @PutMapping("/now")
    public String now() {
        logger.debug("DemoController#now");
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        String res = "LocalDateTime.now() : " + now1 + "<BR>";
        res += "LocalDateTime.now(ZoneId.of(\"Asia/Tokyo\")) : " + now2 + "<BR>";
        return res;
    }
}
