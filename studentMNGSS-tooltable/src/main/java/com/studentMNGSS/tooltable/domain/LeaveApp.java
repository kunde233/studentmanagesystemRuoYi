package com.studentMNGSS.tooltable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.studentMNGSS.common.annotation.Excel;
import com.studentMNGSS.common.core.domain.BaseEntity;

/**
 * 请假申请对象 biz_leave_application
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
public class LeaveApp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    private Long applicationId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 请假类型 */
    @Excel(name = "请假类型")
    private String leaveType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 请假事由 */
    @Excel(name = "请假事由")
    private String reason;

    /** 附件路径 */
    @Excel(name = "附件路径")
    private String attachment;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 审批人ID */
    private Long approverId;

    /** 审批时间 */
    private Date approveTime;

    /** 驳回理由 */
    @Excel(name = "驳回理由")
    private String rejectReason;

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

    public void setLeaveType(String leaveType) 
    {
        this.leaveType = leaveType;
    }

    public String getLeaveType() 
    {
        return leaveType;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    public void setAttachment(String attachment) 
    {
        this.attachment = attachment;
    }

    public String getAttachment() 
    {
        return attachment;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setApproverId(Long approverId) 
    {
        this.approverId = approverId;
    }

    public Long getApproverId() 
    {
        return approverId;
    }

    public void setApproveTime(Date approveTime) 
    {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() 
    {
        return approveTime;
    }

    public void setRejectReason(String rejectReason) 
    {
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() 
    {
        return rejectReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applicationId", getApplicationId())
            .append("studentId", getStudentId())
            .append("leaveType", getLeaveType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("reason", getReason())
            .append("attachment", getAttachment())
            .append("status", getStatus())
            .append("approverId", getApproverId())
            .append("approveTime", getApproveTime())
            .append("rejectReason", getRejectReason())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
