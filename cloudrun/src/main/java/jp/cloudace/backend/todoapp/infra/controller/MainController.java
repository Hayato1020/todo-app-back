package jp.cloudace.backend.todoapp.infra.controller;

//import com.google.cloud.Timestamp;
import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import jp.cloudace.backend.todoapp.usecase.Hello;
import jp.cloudace.backend.todoapp.usecase.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;


@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final Hello hello;
    private final Tasks tasks;

    public MainController(Hello hello, Tasks tasks) {
        this.hello = hello;
        this.tasks = tasks;
    }

    @GetMapping("/task/{userId}")
    public List<TasksEntity> getUserTask(
            @PathVariable String userId,
            HttpServletRequest request) {
        logger.debug("MainController#getTaskList");
        return tasks.getUserTaskList(userId);
    }

    @PostMapping("/task")
    public int postTask(@RequestBody TasksEntity req){
        logger.debug("MainController#postTask");

        //現在時刻の取得とTimestamp型への変換
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.userId = req.userId;
        tasksEntity.taskId = UUID.randomUUID().toString();
        tasksEntity.task = req.task;
        tasksEntity.taskInfo = req.taskInfo;
        tasksEntity.deadlineOfTask = req.deadlineOfTask;
        tasksEntity.priorityOfTask = req.priorityOfTask;
        tasksEntity.statusId = req.statusId;
        tasksEntity.labelId = req.labelId;
        tasksEntity.createdAt = timestamp;
        tasksEntity.updatedAt = timestamp;
        return tasks.postTask(tasksEntity);
    }

    @PutMapping("/task")
    public int putTask(@RequestBody TasksEntity req){
        logger.debug("MainController#putTask");
        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.userId = req.userId;
        tasksEntity.taskId = req.taskId;
        tasksEntity.task = req.task;
        tasksEntity.taskInfo = req.taskInfo;
        tasksEntity.deadlineOfTask = req.deadlineOfTask;
        tasksEntity.priorityOfTask = req.priorityOfTask;
        tasksEntity.statusId = req.statusId;
        tasksEntity.labelId = req.labelId;
        return tasks.putTask(tasksEntity);

    }

    @DeleteMapping("/task")
    public int deleteTask(@RequestBody TasksEntity req){
        logger.debug("MainController#deleteTask");
        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.userId = req.userId;
        tasksEntity.taskId = req.taskId;
        return tasks.deleteTask(tasksEntity);
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
