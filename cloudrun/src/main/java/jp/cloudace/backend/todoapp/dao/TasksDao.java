package jp.cloudace.backend.todoapp.dao;


import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

/**
 * タスク Dao
 */
@ConfigAutowireable
@Dao
public interface TasksDao {
    /**
     * 登録
     *
     * @param tasksEntity 　エンティティ
     * @return 件数
     */
    @Insert
    int insert(TasksEntity tasksEntity);

    /**
     * 更新
     *
     * @param tasksEntity 　エンティティ
     * @return 件数
     */
    @Update
    int update(TasksEntity tasksEntity);

    /**
     * 削除
     *
     * @param tasksEntity 　エンティティ
     * @return 件数
     */
    @Delete
    int delete(TasksEntity tasksEntity);

    /**
     * 全データ取得
     *
     * @return
     */
    @Select
    List<TasksEntity> getAll();
}
