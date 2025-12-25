package com.studentMNGSS.tooltable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.studentMNGSS.common.annotation.Excel;
import com.studentMNGSS.common.core.domain.BaseEntity;

/**
 * 人员调动对象 hr_transfer
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public class HrTransfer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调动ID */
    private Long transferId;

    /** 调动编号 */
    @Excel(name = "调动编号")
    private String transferCode;

    /** 员工ID */
    @Excel(name = "员工ID")
    private Long employeeId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String employeeName;

    /** 调动类型（0机构调动 1职位调动 2机构和职位调动） */
    @Excel(name = "调动类型", readConverterExp = "0=机构调动,1=职位调动,2=机构和职位调动")
    private String transferType;

    /** 原机构ID */
    private Long oldDeptId;

    /** 原机构名称 */
    @Excel(name = "原机构")
    private String oldDeptName;

    /** 原职位ID */
    private Long oldPositionId;

    /** 原职位名称 */
    @Excel(name = "原职位")
    private String oldPositionName;

    /** 新一级机构ID */
    private Long newDeptIdFirst;

    /** 新二级机构ID */
    private Long newDeptIdSecond;

    /** 新三级机构ID */
    private Long newDeptIdThird;

    /** 新机构名称 */
    @Excel(name = "新机构")
    private String newDeptName;

    /** 新职位ID */
    private Long newPositionId;

    /** 新职位名称 */
    @Excel(name = "新职位")
    private String newPositionName;

    /** 调动日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调动日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transferDate;

    /** 调动原因 */
    @Excel(name = "调动原因")
    private String transferReason;

    /** 状态（0待复核 1已复核） */
    @Excel(name = "状态", readConverterExp = "0=待复核,1=已复核")
    private String status;

    /** 复核人 */
    @Excel(name = "复核人")
    private String reviewer;

    /** 复核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "复核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    /** 删除标志 */
    private String delFlag;

    public void setTransferId(Long transferId)
    {
        this.transferId = transferId;
    }

    public Long getTransferId()
    {
        return transferId;
    }

    public void setTransferCode(String transferCode)
    {
        this.transferCode = transferCode;
    }

    public String getTransferCode()
    {
        return transferCode;
    }

    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setTransferType(String transferType)
    {
        this.transferType = transferType;
    }

    public String getTransferType()
    {
        return transferType;
    }

    public void setOldDeptId(Long oldDeptId)
    {
        this.oldDeptId = oldDeptId;
    }

    public Long getOldDeptId()
    {
        return oldDeptId;
    }

    public void setOldDeptName(String oldDeptName)
    {
        this.oldDeptName = oldDeptName;
    }

    public String getOldDeptName()
    {
        return oldDeptName;
    }

    public void setOldPositionId(Long oldPositionId)
    {
        this.oldPositionId = oldPositionId;
    }

    public Long getOldPositionId()
    {
        return oldPositionId;
    }

    public void setOldPositionName(String oldPositionName)
    {
        this.oldPositionName = oldPositionName;
    }

    public String getOldPositionName()
    {
        return oldPositionName;
    }

    public void setNewDeptIdFirst(Long newDeptIdFirst)
    {
        this.newDeptIdFirst = newDeptIdFirst;
    }

    public Long getNewDeptIdFirst()
    {
        return newDeptIdFirst;
    }

    public void setNewDeptIdSecond(Long newDeptIdSecond)
    {
        this.newDeptIdSecond = newDeptIdSecond;
    }

    public Long getNewDeptIdSecond()
    {
        return newDeptIdSecond;
    }

    public void setNewDeptIdThird(Long newDeptIdThird)
    {
        this.newDeptIdThird = newDeptIdThird;
    }

    public Long getNewDeptIdThird()
    {
        return newDeptIdThird;
    }

    public void setNewDeptName(String newDeptName)
    {
        this.newDeptName = newDeptName;
    }

    public String getNewDeptName()
    {
        return newDeptName;
    }

    public void setNewPositionId(Long newPositionId)
    {
        this.newPositionId = newPositionId;
    }

    public Long getNewPositionId()
    {
        return newPositionId;
    }

    public void setNewPositionName(String newPositionName)
    {
        this.newPositionName = newPositionName;
    }

    public String getNewPositionName()
    {
        return newPositionName;
    }

    public void setTransferDate(Date transferDate)
    {
        this.transferDate = transferDate;
    }

    public Date getTransferDate()
    {
        return transferDate;
    }

    public void setTransferReason(String transferReason)
    {
        this.transferReason = transferReason;
    }

    public String getTransferReason()
    {
        return transferReason;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setReviewer(String reviewer)
    {
        this.reviewer = reviewer;
    }

    public String getReviewer()
    {
        return reviewer;
    }

    public void setReviewTime(Date reviewTime)
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime()
    {
        return reviewTime;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transferId", getTransferId())
            .append("transferCode", getTransferCode())
            .append("employeeId", getEmployeeId())
            .append("employeeName", getEmployeeName())
            .append("transferType", getTransferType())
            .append("oldDeptId", getOldDeptId())
            .append("oldDeptName", getOldDeptName())
            .append("oldPositionId", getOldPositionId())
            .append("oldPositionName", getOldPositionName())
            .append("newDeptIdFirst", getNewDeptIdFirst())
            .append("newDeptIdSecond", getNewDeptIdSecond())
            .append("newDeptIdThird", getNewDeptIdThird())
            .append("newDeptName", getNewDeptName())
            .append("newPositionId", getNewPositionId())
            .append("newPositionName", getNewPositionName())
            .append("transferDate", getTransferDate())
            .append("transferReason", getTransferReason())
            .append("status", getStatus())
            .append("reviewer", getReviewer())
            .append("reviewTime", getReviewTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
