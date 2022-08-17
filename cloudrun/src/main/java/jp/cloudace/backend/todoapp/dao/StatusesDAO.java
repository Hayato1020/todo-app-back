package jp.cloudace.backend.todoapp.dao;

import jp.cloudace.backend.todoapp.dao.entity.LabelsEntity;
import jp.cloudace.backend.todoapp.dao.entity.StatusesEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import java.util.List;

@ConfigAutowireable
@Dao
public interface StatusesDAO {
    @Select
    List<StatusesEntity> getStatuses();

}
