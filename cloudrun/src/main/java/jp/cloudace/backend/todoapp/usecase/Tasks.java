package jp.cloudace.backend.todoapp.usecase;

import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;

import java.sql.Timestamp;
import java.util.List;

public interface Tasks {

    List<TasksEntity> getUserTaskList(String userId);

    int postTask(TasksEntity tasksEntity);

    int putTask(TasksEntity tasksEntity);

    int deleteTask(TasksEntity tasksEntity);
}
