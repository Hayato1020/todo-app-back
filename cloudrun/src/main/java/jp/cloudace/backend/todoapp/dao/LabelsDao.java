package jp.cloudace.backend.todoapp.dao;

import jp.cloudace.backend.todoapp.dao.entity.LabelsEntity;
import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface LabelsDao {
    /**
     * 登録
     *
     * @param labelsEntity 　エンティティ
     * @return 件数
     */
    @Insert
    int insert(LabelsEntity labelsEntity);

    /**
     * 更新
     *
     * @param labelsEntity 　エンティティ
     * @return 件数
     */
    @Update
    int update(LabelsEntity labelsEntity);

    /**
     * 削除
     *
     * @param labelsEntity 　エンティティ
     * @return 件数
     */
    @Delete(sqlFile = true)
    int delete(LabelsEntity labelsEntity);

    /**
     * 全データ取得
     *
     * @return
     */
    @Select
    List<LabelsEntity> getLabels(String userId);

}
