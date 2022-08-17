package jp.cloudace.backend.todoapp.dao;


import jp.cloudace.backend.todoapp.dao.entity.TasksEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import java.sql.Timestamp;
import java.util.List;

/**
 * タスク Dao実装クラスを自動で生成
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
    @Update(sqlFile = true)
    int updateTask(TasksEntity tasksEntity);

    /**
     * 削除
     *
     * @param tasksEntity 　エンティティ
     * @return 件数
     */
    @Delete(sqlFile = true)
    int deleteTask(TasksEntity tasksEntity);

    /**
     * 全データ取得
     *
     * @return
     */
    @Select
    List<TasksEntity> getAll();

    @Select
    List<TasksEntity> getTasks(String userId);

}
