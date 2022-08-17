package jp.cloudace.backend.todoapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;


@Entity
@Table(name = "statuses")
@JsonInclude(JsonInclude.Include.NON_EMPTY)//NULLの場合にJsonへの変換はしない
public class StatusesEntity {
    /**
     * ステータスID
     */
    @Id
    @Column(name = "status_id")
    public String statusId;

    /**
     * ラベルID
     */
    @Column(name = "status")
    public String status;

}
