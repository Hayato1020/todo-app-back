package jp.cloudace.backend.todoapp.dao.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
public class TasksEntity {
    /**
     * ユーザーID
     */
    @Column(name = "user_id")
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
    public Timestamp deadlineOfTask;

    @Column(name = "priority_of_task")
    public Short priorityOfTask;

    @Column(name = "status_id")
    public Integer statusId;

    @Column(name = "label_id")
    public String labelId;

    @Column(name = "created_at")
    public Timestamp createdAt;

    @Column(name = "updated_at")
    public Timestamp updatedAt;

    @Column(name = "deleted_at")
    public Timestamp deletedAt;
}
