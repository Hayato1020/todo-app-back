package jp.cloudace.backend.todoapp.infra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.firebase.auth.FirebaseAuthException;
import jp.cloudace.backend.todoapp.dao.entity.LabelsEntity;
import jp.cloudace.backend.todoapp.dao.entity.StatusesEntity;
import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import jp.cloudace.backend.todoapp.usecase.Hello;
import jp.cloudace.backend.todoapp.usecase.Labels;
import jp.cloudace.backend.todoapp.usecase.Tasks;
import jp.cloudace.backend.todoapp.usecase.Statuses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.FirebaseAuth;



@JsonInclude(JsonInclude.Include.NON_EMPTY)//NULLの場合にJsonへの変換はしない
class ResponseDTO{
    ResponseDTO(String successOrError){
        if(successOrError == "success") {
            success = new Success();
        }else if(successOrError == "error"){
            error = new Error();
        }
    }
    public class Success{
        public String message;
        public int code;
    }

    public class Error{
        public String message;
        public int code;
    }

    public Success success;
    public Error error;
    public List<TasksEntity> taskList;
    public String taskId;
    public List<LabelsEntity> labelList;
    public String labelId;
    public List<StatusesEntity> statusesList;

}


@RestController
@CrossOrigin
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final Hello hello;
    private final Tasks tasks;
    private final Labels labels;
    private final Statuses statuses;

    public MainController(Hello hello, Tasks tasks, Labels labels, Statuses statuses) {
        this.hello = hello;
        this.tasks = tasks;
        this.labels = labels;
        this.statuses = statuses;
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (IOException e){
            System.out.println(e);
        }

    }

    @GetMapping("/task")
    public ResponseDTO getUserTask(HttpServletRequest request) {
        logger.debug("MainController#getTaskList");
        String token = request.getHeader("Authorization");
        String fireBaseUid;

        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
            System.out.println(fireBaseUid);
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        responseBody = new ResponseDTO("success");
        responseBody.taskList = tasks.getUserTaskList(fireBaseUid);
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";
        return responseBody;
    }

    @PostMapping("/task")
    public ResponseDTO postTask(@RequestBody TasksEntity req, HttpServletRequest request){
        logger.debug("MainController#postTask");
        String token = request.getHeader("Authorization");
        String fireBaseUid;
        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        //エラー処理 必須項目がnullと空文字の時にエラー処理をする
        if(req.task == null || req.priorityOfTask == null || req.task == ""){
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Bad request";
            responseBody.error.code = 400;
            return responseBody;
        }

        //現在時刻の取得とTimestamp型への変換
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        TasksEntity tasksEntity = new TasksEntity();

        tasksEntity.userId = fireBaseUid;
        tasksEntity.taskId = UUID.randomUUID().toString();
        tasksEntity.task = req.task;
        tasksEntity.taskInfo = req.taskInfo;
        tasksEntity.deadlineOfTask = req.deadlineOfTask;
        tasksEntity.priorityOfTask = req.priorityOfTask;
        tasksEntity.statusId = req.statusId;
        tasksEntity.labelId = req.labelId;
        tasksEntity.createdAt = timestamp;
        tasksEntity.updatedAt = timestamp;

        //テーブルへの挿入処理
        tasks.postTask(tasksEntity);

        //成功時の処理
        responseBody = new ResponseDTO("success");
        responseBody.taskId = tasksEntity.taskId;
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";
        return responseBody;
    }

    @PutMapping("/task")
    public ResponseDTO putTask(@RequestBody TasksEntity req, HttpServletRequest request){
        logger.debug("MainController#putTask");

        String token = request.getHeader("Authorization");
        System.out.println("アクセストークン"+token);
        String fireBaseUid;
        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        //エラー処理 必須項目がnullと空文字の時にエラー処理をする
        if(req.taskId == null || req.task == null || req.priorityOfTask == null ||
                req.taskId == "" || req.task == "" ){
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Bad request";
            responseBody.error.code = 400;
            return responseBody;
        }

        //現在時刻の取得とTimestamp型への変換
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.userId = fireBaseUid;
        tasksEntity.taskId = req.taskId;
        tasksEntity.task = req.task;
        tasksEntity.taskInfo = req.taskInfo;
        tasksEntity.deadlineOfTask = req.deadlineOfTask;
        tasksEntity.priorityOfTask = req.priorityOfTask;
        tasksEntity.statusId = req.statusId;
        tasksEntity.labelId = req.labelId;
        tasksEntity.updatedAt = timestamp;

        //テーブルの更新処理
        tasks.putTask(tasksEntity);

        //成功時の処理
        responseBody = new ResponseDTO("success");
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";
        return responseBody;

    }

    @DeleteMapping("/task")
    public ResponseDTO deleteTask(@RequestBody TasksEntity req, HttpServletRequest request){
        logger.debug("MainController#deleteTask");

        String token = request.getHeader("Authorization");
        String fireBaseUid;
        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        //エラー処理 必須項目がnullと空文字の時にエラー処理をする
        if(req.taskId == null || req.taskId == ""){
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Bad request";
            responseBody.error.code = 400;
            return responseBody;
        }

        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.userId = fireBaseUid;
        tasksEntity.taskId = req.taskId;

        //削除するレコードがない場合
        if (tasks.deleteTask(tasksEntity) != 1) {
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "There is no field to delete.";
            responseBody.error.code = 400;
            return responseBody;
        }

        responseBody = new ResponseDTO("success");
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";

        return responseBody;
    }


    @GetMapping("/label")
    public ResponseDTO getLabel(HttpServletRequest request){
        logger.debug("MainController#getLabel");
        String token = request.getHeader("Authorization");
        String fireBaseUid;
        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
            System.out.println(fireBaseUid);
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        responseBody = new ResponseDTO("success");
        responseBody.labelList = labels.getLabel(fireBaseUid);
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";
        return responseBody;
    }

    @PostMapping("/label")
    public ResponseDTO postLabel(@RequestBody LabelsEntity req,HttpServletRequest request){
        logger.debug("MainController#postLabel");
        String token = request.getHeader("Authorization");
        String fireBaseUid;
        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
            System.out.println(fireBaseUid);
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        //エラー処理 必須項目がnullと空文字の時にエラー処理をする
        if(req.label == null || req.label == ""){
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Bad request";
            responseBody.error.code = 400;
            return responseBody;
        }

        //現在時刻の取得とTimestamp型への変換
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        LabelsEntity labelsEntity = new LabelsEntity();
        labelsEntity.labelId = UUID.randomUUID().toString();
        labelsEntity.label = req.label;
        labelsEntity.userId = fireBaseUid;
        labelsEntity.createdAt = timestamp;

        //テーブルへの挿入処理
        labels.postLabel(labelsEntity);

        //成功時の処理
        responseBody = new ResponseDTO("success");
        responseBody.labelId = labelsEntity.labelId;
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";
        return responseBody;
    }

    @DeleteMapping("/label")
    public ResponseDTO deleteLabel(@RequestBody LabelsEntity req,HttpServletRequest request){
        logger.debug("MainController#deleteLabel");
        String token = request.getHeader("Authorization");
        String fireBaseUid;
        ResponseDTO responseBody;
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            fireBaseUid = decodedToken.getUid();
        }catch(FirebaseAuthException e){
            System.out.println(e);
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "HTTP request was failed: Token is invalid.";
            responseBody.error.code = 400;
            return responseBody;
        }

        LabelsEntity labelsEntity = new LabelsEntity();
        labelsEntity.userId = fireBaseUid;
        labelsEntity.labelId = req.labelId;

        //削除するレコードがない場合
        if (labels.deleteLabel(labelsEntity) != 1) {
            responseBody = new ResponseDTO("error");
            responseBody.error.message = "There is no field to delete.";
            responseBody.error.code = 400;
            return responseBody;
        }

        responseBody = new ResponseDTO("success");
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";

        return responseBody;
    }

    @GetMapping("/status")
    public ResponseDTO getStatus(){
        logger.debug("MainController#getStatuses");
        ResponseDTO responseBody = new ResponseDTO("success");
        responseBody.statusesList = statuses.getStatuses();
        responseBody.success.code = 200;
        responseBody.success.message = "HTTP request was succeed: OK";
        return responseBody;
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
