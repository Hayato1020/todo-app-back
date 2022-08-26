package jp.cloudace.backend.todoapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.sql.Timestamp;
import java.sql.Date;

@Entity
@Table(name = "tasks")
public class TasksEntity {
    /**
     * ユーザーID
     */
    @Column(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//NULLの場合にJsonへの変換はしない
    public String userId;

    /**
     * タスクID
     */
    @Id
    @Column(name = "task_id")
    public String taskId;

    @Column(name = "task")
    public String task;

    @Column(name = "task_info")
    public String taskInfo;

    @Column(name = "deadline_of_task")
    public Date deadlineOfTask;

    @Column(name = "priority_of_task")
    public Short priorityOfTask;

    @Column(name = "status_id")
    public Integer statusId;

    @Column(name = "label_id")
    public String labelId;

    @Column(name = "created_at")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Timestamp createdAt;

    @Column(name = "updated_at")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Timestamp updatedAt;

    @Column(name = "deleted_at")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Timestamp deletedAt;
}
