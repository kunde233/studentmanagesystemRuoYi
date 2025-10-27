package com.ruoyi.application.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请假申请对象 app_leave_application
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
public class AppLeaveApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 请假申请ID */
    private Long leaveId;

    /** 申请人ID */
    private Long userId;

    /** 请假类型（事假/病假/其他） */
    @Excel(name = "请假类型", readConverterExp = "事=假/病假/其他")
    private String leaveType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDatetime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDatetime;

    /** 请假事由 */
    @Excel(name = "请假事由")
    private String leaveReason;

    /** 审批状态（pending/approved/rejected） */
    @Excel(name = "审批状态", readConverterExp = "p=ending/approved/rejected")
    private String approvalStatus;

    /** 当前审批人角色 */
    @Excel(name = "当前审批人角色")
    private String currentApprover;

    /** 附件URL */
    @Excel(name = "附件URL")
    private String attachmentUrl;

    public void setLeaveId(Long leaveId) 
    {
        this.leaveId = leaveId;
    }

    public Long getLeaveId() 
    {
        return leaveId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setLeaveType(String leaveType) 
    {
        this.leaveType = leaveType;
    }

    public String getLeaveType() 
    {
        return leaveType;
    }

    public void setStartDatetime(Date startDatetime) 
    {
        this.startDatetime = startDatetime;
    }

    public Date getStartDatetime() 
    {
        return startDatetime;
    }

    public void setEndDatetime(Date endDatetime) 
    {
        this.endDatetime = endDatetime;
    }

    public Date getEndDatetime() 
    {
        return endDatetime;
    }

    public void setLeaveReason(String leaveReason) 
    {
        this.leaveReason = leaveReason;
    }

    public String getLeaveReason() 
    {
        return leaveReason;
    }

    public void setApprovalStatus(String approvalStatus) 
    {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatus() 
    {
        return approvalStatus;
    }

    public void setCurrentApprover(String currentApprover) 
    {
        this.currentApprover = currentApprover;
    }

    public String getCurrentApprover() 
    {
        return currentApprover;
    }

    public void setAttachmentUrl(String attachmentUrl) 
    {
        this.attachmentUrl = attachmentUrl;
    }

    public String getAttachmentUrl() 
    {
        return attachmentUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("leaveId", getLeaveId())
            .append("userId", getUserId())
            .append("leaveType", getLeaveType())
            .append("startDatetime", getStartDatetime())
            .append("endDatetime", getEndDatetime())
            .append("leaveReason", getLeaveReason())
            .append("approvalStatus", getApprovalStatus())
            .append("currentApprover", getCurrentApprover())
            .append("attachmentUrl", getAttachmentUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
