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
 * 薪酬标准对象 hr_salary_standard
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public class HrSalaryStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 薪酬标准ID */
    private Long standardId;

    /** 薪酬标准编号 */
    @Excel(name = "薪酬标准编号")
    private String standardCode;

    /** 薪酬标准名称 */
    @Excel(name = "薪酬标准名称")
    private String standardName;

    /** 适用职位ID */
    @Excel(name = "适用职位")
    private Long positionId;

    /** 适用职位名称（非数据库字段） */
    private String positionName;

    /** 适用职称 */
    @Excel(name = "适用职称")
    private String title;

    /** 薪酬总额 */
    @Excel(name = "薪酬总额")
    private BigDecimal totalAmount;

    /** 制定人 */
    @Excel(name = "制定人")
    private String maker;

    /** 变更人 */
    private String changer;

    /** 变更时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date changeTime;

    /** 复核人 */
    @Excel(name = "复核人")
    private String reviewer;

    /** 复核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "复核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    /** 状态（0待复核 1已复核 2停用） */
    @Excel(name = "状态", readConverterExp = "0=待复核,1=已复核,2=停用")
    private String status;

    /** 删除标志 */
    private String delFlag;

    /** 薪酬标准明细列表 */
    private List<HrSalaryStandardDetail> details;

    public void setStandardId(Long standardId)
    {
        this.standardId = standardId;
    }

    public Long getStandardId()
    {
        return standardId;
    }

    public void setStandardCode(String standardCode)
    {
        this.standardCode = standardCode;
    }

    public String getStandardCode()
    {
        return standardCode;
    }

    public void setStandardName(String standardName)
    {
        this.standardName = standardName;
    }

    public String getStandardName()
    {
        return standardName;
    }

    public void setPositionId(Long positionId)
    {
        this.positionId = positionId;
    }

    public Long getPositionId()
    {
        return positionId;
    }

    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }

    public String getPositionName()
    {
        return positionName;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }

    public void setMaker(String maker)
    {
        this.maker = maker;
    }

    public String getMaker()
    {
        return maker;
    }

    public void setChanger(String changer)
    {
        this.changer = changer;
    }

    public String getChanger()
    {
        return changer;
    }

    public void setChangeTime(Date changeTime)
    {
        this.changeTime = changeTime;
    }

    public Date getChangeTime()
    {
        return changeTime;
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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDetails(List<HrSalaryStandardDetail> details)
    {
        this.details = details;
    }

    public List<HrSalaryStandardDetail> getDetails()
    {
        return details;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("standardId", getStandardId())
            .append("standardCode", getStandardCode())
            .append("standardName", getStandardName())
            .append("positionId", getPositionId())
            .append("positionName", getPositionName())
            .append("title", getTitle())
            .append("totalAmount", getTotalAmount())
            .append("maker", getMaker())
            .append("status", getStatus())
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
