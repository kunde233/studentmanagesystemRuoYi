package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrEmployee;

/**
 * 员工档案Service接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface IHrEmployeeService
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
     * 新增员工档案（登记）
     *
     * @param hrEmployee 员工档案
     * @param username 登记人
     * @return 结果
     */
    public int insertHrEmployee(HrEmployee hrEmployee, String username);

    /**
     * 修改员工档案
     *
     * @param hrEmployee 员工档案
     * @return 结果
     */
    public int updateHrEmployee(HrEmployee hrEmployee);

    /**
     * 复核员工档案
     *
     * @param hrEmployee 员工档案
     * @param username 复核人
     * @return 结果
     */
    public int reviewHrEmployee(HrEmployee hrEmployee, String username);

    /**
     * 批量删除员工档案（软删除）
     *
     * @param employeeIds 需要删除的员工档案主键集合
     * @return 结果
     */
    public int deleteHrEmployeeByEmployeeIds(Long[] employeeIds);

    /**
     * 删除员工档案信息（软删除）
     *
     * @param employeeId 员工档案主键
     * @return 结果
     */
    public int deleteHrEmployeeByEmployeeId(Long employeeId);

    /**
     * 恢复已删除的员工档案
     *
     * @param employeeId 员工档案主键
     * @return 结果
     */
    public int restoreHrEmployee(Long employeeId);

    /**
     * 校验身份证号是否唯一
     *
     * @param hrEmployee 员工档案
     * @return 结果
     */
    public boolean checkIdCardUnique(HrEmployee hrEmployee);

    /**
     * 生成员工档案编号
     *
     * @param deptIdFirst 一级机构ID
     * @param deptIdSecond 二级机构ID
     * @param deptIdThird 三级机构ID
     * @return 档案编号
     */
    public String generateEmployeeCode(Long deptIdFirst, Long deptIdSecond, Long deptIdThird);
}
