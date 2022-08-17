package jp.cloudace.backend.todoapp.usecase.impl;

import jp.cloudace.backend.todoapp.dao.LabelsDao;
import jp.cloudace.backend.todoapp.dao.StatusesDAO;
import jp.cloudace.backend.todoapp.dao.entity.StatusesEntity;
import jp.cloudace.backend.todoapp.usecase.Statuses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusesImpl implements Statuses {

    private final StatusesDAO statusesDAO;

    private static final Logger logger = LoggerFactory.getLogger(TasksImpl.class);

    public StatusesImpl(StatusesDAO statusesDao) {
        this.statusesDAO = statusesDao;
    }

    @Override
    public List<StatusesEntity> getStatuses(){
        logger.debug("TasksImpl#getstatus");
        return statusesDAO.getStatuses();
    };
}
