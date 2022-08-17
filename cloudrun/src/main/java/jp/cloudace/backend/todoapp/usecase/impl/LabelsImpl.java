package jp.cloudace.backend.todoapp.usecase.impl;

import jp.cloudace.backend.todoapp.dao.LabelsDao;
import jp.cloudace.backend.todoapp.dao.entity.LabelsEntity;
import jp.cloudace.backend.todoapp.usecase.Labels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelsImpl implements Labels {

    private final LabelsDao labelsDao;

    private static final Logger logger = LoggerFactory.getLogger(TasksImpl.class);

    public LabelsImpl(LabelsDao labelsDao) {
        this.labelsDao = labelsDao;
    }

    @Override
    public List<LabelsEntity> getLabel(String userId){
        logger.debug("TasksImpl#getLabel");
        return labelsDao.getLabels(userId);
    }

    @Override
    public int postLabel(LabelsEntity labelsEntity){
        logger.debug("TasksImpl#postLabel");
        return labelsDao.insert(labelsEntity);
    }

    @Override
    public int deleteLabel(LabelsEntity labelsEntity){
        logger.debug("TasksImpl#deleteLabel");
        return labelsDao.delete(labelsEntity);
    }
}
