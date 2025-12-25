package com.studentMNGSS.tooltable.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.studentMNGSS.common.annotation.Excel;
import com.studentMNGSS.common.core.domain.BaseEntity;

/**
 * 员工档案对象 hr_employee
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public class HrEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工ID */
    private Long employeeId;

    /** 档案编号 */
    @Excel(name = "档案编号")
    private String employeeCode;

    /** 姓名 */
    @Excel(name = "姓名")
    private String employeeName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String gender;

    /** 一级机构ID */
    private Long deptIdFirst;

    /** 一级机构名称 */
    @Excel(name = "一级机构")
    private String deptNameFirst;

    /** 二级机构ID */
    private Long deptIdSecond;

    /** 二级机构名称 */
    @Excel(name = "二级机构")
    private String deptNameSecond;

    /** 三级机构ID */
    private Long deptIdThird;

    /** 三级机构名称 */
    @Excel(name = "三级机构")
    private String deptNameThird;

    /** 职位ID */
    private Long positionId;

    /** 职位名称 */
    @Excel(name = "职位")
    private String positionName;

    /** 职称 */
    @Excel(name = "职称")
    private String title;

    /** 薪酬标准ID */
    private Long salaryStandardId;

    /** 薪酬标准名称 */
    @Excel(name = "薪酬标准")
    private String salaryStandardName;

    /** Email */
    @Excel(name = "Email")
    private String email;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** QQ */
    private String qq;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 住址 */
    private String address;

    /** 邮编 */
    private String postCode;

    /** 国籍 */
    private String nationality;

    /** 出生地 */
    private String birthplace;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 民族 */
    private String nation;

    /** 宗教信仰 */
    private String religion;

    /** 政治面貌 */
    private String politicalStatus;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idCard;

    /** 学历 */
    @Excel(name = "学历")
    private String education;

    /** 照片路径 */
    private String photo;

    /** 个人履历 */
    private String resume;

    /** 家庭关系信息 */
    private String familyInfo;

    /** 状态（0待复核 1正常 2已删除） */
    @Excel(name = "状态", readConverterExp = "0=待复核,1=正常,2=已删除")
    private String status;

    /** 登记人 */
    private String registerBy;

    /** 登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /** 复核人 */
    private String reviewer;

    /** 复核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    /** 删除标志 */
    private String delFlag;

    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeCode(String employeeCode)
    {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode()
    {
        return employeeCode;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }

    public void setDeptIdFirst(Long deptIdFirst)
    {
        this.deptIdFirst = deptIdFirst;
    }

    public Long getDeptIdFirst()
    {
        return deptIdFirst;
    }

    public void setDeptNameFirst(String deptNameFirst)
    {
        this.deptNameFirst = deptNameFirst;
    }

    public String getDeptNameFirst()
    {
        return deptNameFirst;
    }

    public void setDeptIdSecond(Long deptIdSecond)
    {
        this.deptIdSecond = deptIdSecond;
    }

    public Long getDeptIdSecond()
    {
        return deptIdSecond;
    }

    public void setDeptNameSecond(String deptNameSecond)
    {
        this.deptNameSecond = deptNameSecond;
    }

    public String getDeptNameSecond()
    {
        return deptNameSecond;
    }

    public void setDeptIdThird(Long deptIdThird)
    {
        this.deptIdThird = deptIdThird;
    }

    public Long getDeptIdThird()
    {
        return deptIdThird;
    }

    public void setDeptNameThird(String deptNameThird)
    {
        this.deptNameThird = deptNameThird;
    }

    public String getDeptNameThird()
    {
        return deptNameThird;
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

    public void setSalaryStandardId(Long salaryStandardId)
    {
        this.salaryStandardId = salaryStandardId;
    }

    public Long getSalaryStandardId()
    {
        return salaryStandardId;
    }

    public void setSalaryStandardName(String salaryStandardName)
    {
        this.salaryStandardName = salaryStandardName;
    }

    public String getSalaryStandardName()
    {
        return salaryStandardName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getQq()
    {
        return qq;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setBirthplace(String birthplace)
    {
        this.birthplace = birthplace;
    }

    public String getBirthplace()
    {
        return birthplace;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setNation(String nation)
    {
        this.nation = nation;
    }

    public String getNation()
    {
        return nation;
    }

    public void setReligion(String religion)
    {
        this.religion = religion;
    }

    public String getReligion()
    {
        return religion;
    }

    public void setPoliticalStatus(String politicalStatus)
    {
        this.politicalStatus = politicalStatus;
    }

    public String getPoliticalStatus()
    {
        return politicalStatus;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public String getEducation()
    {
        return education;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setResume(String resume)
    {
        this.resume = resume;
    }

    public String getResume()
    {
        return resume;
    }

    public void setFamilyInfo(String familyInfo)
    {
        this.familyInfo = familyInfo;
    }

    public String getFamilyInfo()
    {
        return familyInfo;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setRegisterBy(String registerBy)
    {
        this.registerBy = registerBy;
    }

    public String getRegisterBy()
    {
        return registerBy;
    }

    public void setRegisterTime(Date registerTime)
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime()
    {
        return registerTime;
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
            .append("employeeId", getEmployeeId())
            .append("employeeCode", getEmployeeCode())
            .append("employeeName", getEmployeeName())
            .append("gender", getGender())
            .append("deptIdFirst", getDeptIdFirst())
            .append("deptIdSecond", getDeptIdSecond())
            .append("deptIdThird", getDeptIdThird())
            .append("positionId", getPositionId())
            .append("title", getTitle())
            .append("salaryStandardId", getSalaryStandardId())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("qq", getQq())
            .append("mobile", getMobile())
            .append("address", getAddress())
            .append("postCode", getPostCode())
            .append("nationality", getNationality())
            .append("birthplace", getBirthplace())
            .append("birthday", getBirthday())
            .append("nation", getNation())
            .append("religion", getReligion())
            .append("politicalStatus", getPoliticalStatus())
            .append("idCard", getIdCard())
            .append("education", getEducation())
            .append("photo", getPhoto())
            .append("resume", getResume())
            .append("familyInfo", getFamilyInfo())
            .append("status", getStatus())
            .append("registerBy", getRegisterBy())
            .append("registerTime", getRegisterTime())
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
