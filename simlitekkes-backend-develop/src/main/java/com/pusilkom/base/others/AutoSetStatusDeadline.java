package com.pusilkom.base.others;

import com.pusilkom.base.dto.WorkflowPenelitianDTO;
import com.pusilkom.base.model.StatusTahapPenelitian;
import com.pusilkom.base.model.WorkflowPenelitian;
import com.pusilkom.base.service.WorkflowPenelitianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class AutoSetStatusDeadline {

    /**
    * Add springboot native scheduling
    * cron param list =>  @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
    */

    @Autowired
    WorkflowPenelitianService workflowPenelitianService;

    @Scheduled(cron = "${tanggal.selesai.pengusulan.cron}")
    public void process(){
        try{
            System.out.println("=============== masuk scheduling ganti status ==================");
            List<WorkflowPenelitianDTO> listWorkflow = workflowPenelitianService.getListWorkflowDraft();
            StatusTahapPenelitian status = workflowPenelitianService.getStatusTahapPenelitianByKode("DEADLINE_PASSED");
            for (WorkflowPenelitianDTO workflow : listWorkflow) {
                workflowPenelitianService.update(workflow);
                workflow.setUsername("System");
                workflow.setIsLast(true);
                workflow.setTimestampUpdate(new Date());
                workflow.setIdStatusTahapPenelitian(status.getId());
                workflowPenelitianService.save(workflow);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
