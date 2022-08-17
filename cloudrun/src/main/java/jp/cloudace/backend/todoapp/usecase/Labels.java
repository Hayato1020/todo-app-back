package jp.cloudace.backend.todoapp.usecase;

import jp.cloudace.backend.todoapp.dao.entity.LabelsEntity;

import java.util.List;


public interface Labels {

    List<LabelsEntity> getLabel(String userId);

    int postLabel(LabelsEntity labelsEntity);

    int deleteLabel(LabelsEntity labelsEntity);

}
