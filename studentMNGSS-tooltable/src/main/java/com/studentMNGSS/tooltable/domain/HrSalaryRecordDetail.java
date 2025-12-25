package com.studentMNGSS.tooltable.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 薪酬发放明细对象 hr_salary_record_detail
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public class HrSalaryRecordDetail
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long detailId;

    /** 发放记录ID */
    private Long recordId;

    /** 薪酬项目ID */
    private Long itemId;

    /** 薪酬项目名称 */
    private String itemName;

    /** 薪酬项目类型 */
    private String itemType;

    /** 金额 */
    private BigDecimal amount;

    public void setDetailId(Long detailId)
    {
        this.detailId = detailId;
    }

    public Long getDetailId()
    {
        return detailId;
    }

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId()
    {
        return recordId;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public Long getItemId()
    {
        return itemId;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }

    public String getItemType()
    {
        return itemType;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("recordId", getRecordId())
            .append("itemId", getItemId())
            .append("itemName", getItemName())
            .append("itemType", getItemType())
            .append("amount", getAmount())
            .toString();
    }
}
