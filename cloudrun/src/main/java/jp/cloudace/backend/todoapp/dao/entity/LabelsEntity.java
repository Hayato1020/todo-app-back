package jp.cloudace.backend.todoapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "labels")
@JsonInclude(JsonInclude.Include.NON_EMPTY)//NULLの場合にJsonへの変換はしない
public class LabelsEntity {
    /**
     * ラベルID
     */
    @Id
    @Column(name = "label_id")
    public String labelId;

    @Column(name = "label")
    public String label;

    @Column(name = "user_id")
    public String userId;

    @Column(name = "created_at")
    public Timestamp createdAt;

    @Column(name = "deleted_at")
    public Timestamp deletedAt;

}
