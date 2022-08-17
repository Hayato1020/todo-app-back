package jp.cloudace.backend.todoapp.usecase.impl;

import jp.cloudace.backend.todoapp.dao.TasksDao;
import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import jp.cloudace.backend.todoapp.usecase.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TasksImpl implements Tasks {
    private final TasksDao tasksDao;

    private static final Logger logger = LoggerFactory.getLogger(TasksImpl.class);

    public TasksImpl(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }
/*
    @Override
    public List<TasksEntity> getTaskList() {
        logger.debug("TasksImpl#getTaskList");
        return tasksDao.getAll();
    }


 */
    @Override
    public List<TasksEntity> getUserTaskList(String userId){
        logger.debug("TasksImpl#getTaskList");
        List<TasksEntity> taskList= tasksDao.getTasks(userId);
        return taskList;
    }

    @Override
    public int postTask(TasksEntity tasksEntity){
        logger.debug("TasksImpl#postTask");
        return tasksDao.insert(tasksEntity);
    }

    @Override
    public int putTask(TasksEntity tasksEntity){
        logger.debug("TasksImpl#putTask");
        return tasksDao.updateTask(tasksEntity);
    }

    @Override
    public int deleteTask(TasksEntity tasksEntity){
        logger.debug("TasksImpl#deleteTask");
        return tasksDao.deleteTask(tasksEntity);
    }

}
