package com.studentMNGSS.tooltable.mapper;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrEmployee;

/**
 * 员工档案Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface HrEmployeeMapper
{
    /**
     * 查询员工档案
     *
     * @param employeeId 员工档案主键
     * @return 员工档案
     */
    public HrEmployee selectHrEmployeeByEmployeeId(Long employeeId);

    /**
     * 查询员工档案列表
     *
     * @param hrEmployee 员工档案
     * @return 员工档案集合
     */
    public List<HrEmployee> selectHrEmployeeList(HrEmployee hrEmployee);

    /**
     * 查询待复核的员工档案列表
     *
     * @param hrEmployee 员工档案
     * @return 员工档案集合
     */
    public List<HrEmployee> selectHrEmployeePendingReviewList(HrEmployee hrEmployee);

    /**
     * 新增员工档案
     *
     * @param hrEmployee 员工档案
     * @return 结果
     */
    public int insertHrEmployee(HrEmployee hrEmployee);

    /**
     * 修改员工档案
     *
     * @param hrEmployee 员工档案
     * @return 结果
     */
    public int updateHrEmployee(HrEmployee hrEmployee);

    /**
     * 删除员工档案
     *
     * @param employeeId 员工档案主键
     * @return 结果
     */
    public int deleteHrEmployeeByEmployeeId(Long employeeId);

    /**
     * 批量删除员工档案
     *
     * @param employeeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrEmployeeByEmployeeIds(Long[] employeeIds);

    /**
     * 校验身份证号是否唯一
     *
     * @param idCard 身份证号
     * @return 结果
     */
    public HrEmployee checkIdCardUnique(String idCard);

    /**
     * 获取当年指定机构下的最大员工编号
     *
     * @param codePrefix 编号前缀（年份+机构编号）
     * @return 最大编号
     */
    public String selectMaxEmployeeCode(String codePrefix);

    /**
     * 统计指定状态的员工数量
     *
     * @param status 状态
     * @return 数量
     */
    public int countByStatus(String status);

    /**
     * 查询有薪酬标准的员工列表（用于批量发放）
     *
     * @param hrEmployee 查询条件
     * @return 员工列表
     */
    public List<HrEmployee> selectHrEmployeeWithSalaryStandard(HrEmployee hrEmployee);
}
