package jp.cloudace.backend.todoapp.usecase;

import jp.cloudace.backend.todoapp.dao.entity.LabelsEntity;
import jp.cloudace.backend.todoapp.dao.entity.StatusesEntity;

import java.util.List;

public interface Statuses {
    List<StatusesEntity> getStatuses();
}
