package com.studentMNGSS.tooltable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.studentMNGSS.common.annotation.Excel;
import com.studentMNGSS.common.core.domain.BaseEntity;

/**
 * 回校申请对象 biz_return_application
 * 
 * @author studentMNGSS
 * @date 2025-10-29
 */
public class ReturnApp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    private Long applicationId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 计划返校时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划返校时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedReturnTime;

    /** 交通方式 */
    @Excel(name = "交通方式")
    private String transportation;

    /** 健康状况 */
    @Excel(name = "健康状况")
    private String healthStatus;

    /** 行程码附件 */
    @Excel(name = "行程码附件")
    private String travelCodeAttachment;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String approvalComment;

    public void setApplicationId(Long applicationId) 
    {
        this.applicationId = applicationId;
    }

    public Long getApplicationId() 
    {
        return applicationId;
    }

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setPlannedReturnTime(Date plannedReturnTime) 
    {
        this.plannedReturnTime = plannedReturnTime;
    }

    public Date getPlannedReturnTime() 
    {
        return plannedReturnTime;
    }

    public void setTransportation(String transportation) 
    {
        this.transportation = transportation;
    }

    public String getTransportation() 
    {
        return transportation;
    }

    public void setHealthStatus(String healthStatus) 
    {
        this.healthStatus = healthStatus;
    }

    public String getHealthStatus() 
    {
        return healthStatus;
    }

    public void setTravelCodeAttachment(String travelCodeAttachment) 
    {
        this.travelCodeAttachment = travelCodeAttachment;
    }

    public String getTravelCodeAttachment() 
    {
        return travelCodeAttachment;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setApprovalComment(String approvalComment) 
    {
        this.approvalComment = approvalComment;
    }

    public String getApprovalComment() 
    {
        return approvalComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applicationId", getApplicationId())
            .append("studentId", getStudentId())
            .append("plannedReturnTime", getPlannedReturnTime())
            .append("transportation", getTransportation())
            .append("healthStatus", getHealthStatus())
            .append("travelCodeAttachment", getTravelCodeAttachment())
            .append("status", getStatus())
            .append("approvalComment", getApprovalComment())
            .append("createTime", getCreateTime())
            .toString();
    }
}
