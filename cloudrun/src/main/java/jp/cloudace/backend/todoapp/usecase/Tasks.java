package jp.cloudace.backend.todoapp.usecase;

import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;

import java.util.List;

public interface Tasks {
    List<TasksEntity> getTaskList();
}
