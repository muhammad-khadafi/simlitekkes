package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.WorkflowPenelitianDTO;
import com.pusilkom.base.model.WorkflowPenelitian;
import com.pusilkom.base.model.WorkflowPenelitianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ars on 04/16/2020.
 */
public interface WorkflowPenelitianMapper extends BaseMapper<WorkflowPenelitian, Integer, WorkflowPenelitianExample>  {
    @Select("update workflow_penelitian set is_last = false where id_penelitian = #{idPenelitian} and is_last = true")
    void updateIsLast(@Param("idPenelitian") Integer idPenelitian);
    public List<WorkflowPenelitianDTO> getListWorkflowPenelitian(@Param("idPenelitian") Integer idPenelitian);
    public List<WorkflowPenelitianDTO> getListWorkflowDraft();
}
