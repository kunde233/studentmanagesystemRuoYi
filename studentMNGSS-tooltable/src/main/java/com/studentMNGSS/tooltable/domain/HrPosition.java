package com.studentMNGSS.tooltable.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.studentMNGSS.common.annotation.Excel;
import com.studentMNGSS.common.core.domain.BaseEntity;

/**
 * 职位对象 hr_position
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public class HrPosition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职位ID */
    private Long positionId;

    /** 职位编码 */
    @Excel(name = "职位编码")
    private String positionCode;

    /** 职位名称 */
    @Excel(name = "职位名称")
    private String positionName;

    /** 所属部门ID */
    @Excel(name = "所属部门ID")
    private Long deptId;

    /** 所属部门名称 */
    @Excel(name = "所属部门")
    private String deptName;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer positionSort;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志 */
    private String delFlag;

    public void setPositionId(Long positionId)
    {
        this.positionId = positionId;
    }

    public Long getPositionId()
    {
        return positionId;
    }

    public void setPositionCode(String positionCode)
    {
        this.positionCode = positionCode;
    }

    public String getPositionCode()
    {
        return positionCode;
    }

    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }

    public String getPositionName()
    {
        return positionName;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setPositionSort(Integer positionSort)
    {
        this.positionSort = positionSort;
    }

    public Integer getPositionSort()
    {
        return positionSort;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("positionId", getPositionId())
            .append("positionCode", getPositionCode())
            .append("positionName", getPositionName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("positionSort", getPositionSort())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
