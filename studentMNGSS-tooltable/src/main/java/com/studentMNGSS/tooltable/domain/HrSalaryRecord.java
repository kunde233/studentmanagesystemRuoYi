package com.studentMNGSS.tooltable.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.studentMNGSS.common.annotation.Excel;
import com.studentMNGSS.common.core.domain.BaseEntity;

/**
 * 薪酬发放记录对象 hr_salary_record
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public class HrSalaryRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发放记录ID */
    private Long recordId;

    /** 发放记录编号 */
    @Excel(name = "发放记录编号")
    private String recordCode;

    /** 员工ID */
    private Long employeeId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String employeeName;

    /** 档案编号 */
    @Excel(name = "档案编号")
    private String employeeCode;

    /** 薪酬标准ID */
    private Long standardId;

    /** 薪酬标准名称 */
    @Excel(name = "薪酬标准")
    private String standardName;

    /** 发放月份 */
    @Excel(name = "发放月份")
    private String salaryMonth;

    /** 标准金额 */
    @Excel(name = "标准金额")
    private BigDecimal baseAmount;

    /** 奖励金额 */
    @Excel(name = "奖励金额")
    private BigDecimal bonusAmount;

    /** 扣除金额 */
    @Excel(name = "扣除金额")
    private BigDecimal deductAmount;

    /** 实发金额 */
    @Excel(name = "实发金额")
    private BigDecimal actualAmount;

    /** 状态（0待复核 1已复核） */
    @Excel(name = "状态", readConverterExp = "0=待复核,1=已复核")
    private String status;

    /** 复核人 */
    private String reviewer;

    /** 复核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    /** 删除标志 */
    private String delFlag;

    /** 一级机构ID（查询条件） */
    private Long deptIdFirst;

    /** 二级机构ID（查询条件） */
    private Long deptIdSecond;

    /** 三级机构ID（查询条件） */
    private Long deptIdThird;

    /** 发放明细列表 */
    private List<HrSalaryRecordDetail> details;

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId()
    {
        return recordId;
    }

    public void setRecordCode(String recordCode)
    {
        this.recordCode = recordCode;
    }

    public String getRecordCode()
    {
        return recordCode;
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

    public void setEmployeeCode(String employeeCode)
    {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode()
    {
        return employeeCode;
    }

    public void setStandardId(Long standardId)
    {
        this.standardId = standardId;
    }

    public Long getStandardId()
    {
        return standardId;
    }

    public void setStandardName(String standardName)
    {
        this.standardName = standardName;
    }

    public String getStandardName()
    {
        return standardName;
    }

    public void setSalaryMonth(String salaryMonth)
    {
        this.salaryMonth = salaryMonth;
    }

    public String getSalaryMonth()
    {
        return salaryMonth;
    }

    public void setBaseAmount(BigDecimal baseAmount)
    {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getBaseAmount()
    {
        return baseAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount)
    {
        this.bonusAmount = bonusAmount;
    }

    public BigDecimal getBonusAmount()
    {
        return bonusAmount;
    }

    public void setDeductAmount(BigDecimal deductAmount)
    {
        this.deductAmount = deductAmount;
    }

    public BigDecimal getDeductAmount()
    {
        return deductAmount;
    }

    public void setActualAmount(BigDecimal actualAmount)
    {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getActualAmount()
    {
        return actualAmount;
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

    public void setDeptIdFirst(Long deptIdFirst)
    {
        this.deptIdFirst = deptIdFirst;
    }

    public Long getDeptIdFirst()
    {
        return deptIdFirst;
    }

    public void setDeptIdSecond(Long deptIdSecond)
    {
        this.deptIdSecond = deptIdSecond;
    }

    public Long getDeptIdSecond()
    {
        return deptIdSecond;
    }

    public void setDeptIdThird(Long deptIdThird)
    {
        this.deptIdThird = deptIdThird;
    }

    public Long getDeptIdThird()
    {
        return deptIdThird;
    }

    public void setDetails(List<HrSalaryRecordDetail> details)
    {
        this.details = details;
    }

    public List<HrSalaryRecordDetail> getDetails()
    {
        return details;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("recordCode", getRecordCode())
            .append("employeeId", getEmployeeId())
            .append("employeeName", getEmployeeName())
            .append("standardId", getStandardId())
            .append("standardName", getStandardName())
            .append("salaryMonth", getSalaryMonth())
            .append("baseAmount", getBaseAmount())
            .append("bonusAmount", getBonusAmount())
            .append("deductAmount", getDeductAmount())
            .append("actualAmount", getActualAmount())
            .append("status", getStatus())
            .append("reviewer", getReviewer())
            .append("reviewTime", getReviewTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("details", getDetails())
            .toString();
    }
}
