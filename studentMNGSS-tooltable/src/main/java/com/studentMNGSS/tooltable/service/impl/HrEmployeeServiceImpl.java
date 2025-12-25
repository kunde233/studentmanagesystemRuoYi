package com.studentMNGSS.tooltable.service.impl;

import java.util.Calendar;
import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentMNGSS.tooltable.mapper.HrEmployeeMapper;
import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.service.IHrEmployeeService;

/**
 * 员工档案Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@Service
public class HrEmployeeServiceImpl implements IHrEmployeeService
{
    @Autowired
    private HrEmployeeMapper hrEmployeeMapper;

    /**
     * 查询员工档案
     *
     * @param employeeId 员工档案主键
     * @return 员工档案
     */
    @Override
    public HrEmployee selectHrEmployeeByEmployeeId(Long employeeId)
    {
        return hrEmployeeMapper.selectHrEmployeeByEmployeeId(employeeId);
    }

    /**
     * 查询员工档案列表
     *
     * @param hrEmployee 员工档案
     * @return 员工档案
     */
    @Override
    public List<HrEmployee> selectHrEmployeeList(HrEmployee hrEmployee)
    {
        return hrEmployeeMapper.selectHrEmployeeList(hrEmployee);
    }

    /**
     * 查询待复核的员工档案列表
     *
     * @param hrEmployee 员工档案
     * @return 员工档案集合
     */
    @Override
    public List<HrEmployee> selectHrEmployeePendingReviewList(HrEmployee hrEmployee)
    {
        return hrEmployeeMapper.selectHrEmployeePendingReviewList(hrEmployee);
    }

    /**
     * 新增员工档案（登记）
     *
     * @param hrEmployee 员工档案
     * @param username 登记人
     * @return 结果
     */
    @Override
    public int insertHrEmployee(HrEmployee hrEmployee, String username)
    {
        // 生成档案编号
        String employeeCode = generateEmployeeCode(
            hrEmployee.getDeptIdFirst(),
            hrEmployee.getDeptIdSecond(),
            hrEmployee.getDeptIdThird()
        );
        hrEmployee.setEmployeeCode(employeeCode);

        // 设置状态为待复核
        hrEmployee.setStatus("0");

        // 设置登记人和登记时间
        hrEmployee.setRegisterBy(username);
        hrEmployee.setRegisterTime(DateUtils.getNowDate());

        hrEmployee.setCreateBy(username);
        hrEmployee.setCreateTime(DateUtils.getNowDate());

        return hrEmployeeMapper.insertHrEmployee(hrEmployee);
    }

    /**
     * 修改员工档案
     *
     * @param hrEmployee 员工档案
     * @return 结果
     */
    @Override
    public int updateHrEmployee(HrEmployee hrEmployee)
    {
        hrEmployee.setUpdateTime(DateUtils.getNowDate());
        return hrEmployeeMapper.updateHrEmployee(hrEmployee);
    }

    /**
     * 复核员工档案
     *
     * @param hrEmployee 员工档案
     * @param username 复核人
     * @return 结果
     */
    @Override
    public int reviewHrEmployee(HrEmployee hrEmployee, String username)
    {
        // 设置状态为正常
        hrEmployee.setStatus("1");

        // 设置复核人和复核时间
        hrEmployee.setReviewer(username);
        hrEmployee.setReviewTime(DateUtils.getNowDate());

        hrEmployee.setUpdateBy(username);
        hrEmployee.setUpdateTime(DateUtils.getNowDate());

        return hrEmployeeMapper.updateHrEmployee(hrEmployee);
    }

    /**
     * 批量删除员工档案（软删除）
     *
     * @param employeeIds 需要删除的员工档案主键
     * @return 结果
     */
    @Override
    public int deleteHrEmployeeByEmployeeIds(Long[] employeeIds)
    {
        return hrEmployeeMapper.deleteHrEmployeeByEmployeeIds(employeeIds);
    }

    /**
     * 删除员工档案信息（软删除）
     *
     * @param employeeId 员工档案主键
     * @return 结果
     */
    @Override
    public int deleteHrEmployeeByEmployeeId(Long employeeId)
    {
        return hrEmployeeMapper.deleteHrEmployeeByEmployeeId(employeeId);
    }

    /**
     * 恢复已删除的员工档案
     *
     * @param employeeId 员工档案主键
     * @return 结果
     */
    @Override
    public int restoreHrEmployee(Long employeeId)
    {
        HrEmployee hrEmployee = new HrEmployee();
        hrEmployee.setEmployeeId(employeeId);
        hrEmployee.setStatus("1");
        hrEmployee.setDelFlag("0");
        return hrEmployeeMapper.updateHrEmployee(hrEmployee);
    }

    /**
     * 校验身份证号是否唯一
     *
     * @param hrEmployee 员工档案
     * @return 结果
     */
    @Override
    public boolean checkIdCardUnique(HrEmployee hrEmployee)
    {
        Long employeeId = StringUtils.isNull(hrEmployee.getEmployeeId()) ? -1L : hrEmployee.getEmployeeId();
        HrEmployee info = hrEmployeeMapper.checkIdCardUnique(hrEmployee.getIdCard());
        if (StringUtils.isNotNull(info) && info.getEmployeeId().longValue() != employeeId.longValue())
        {
            return false;
        }
        return true;
    }

    /**
     * 生成员工档案编号
     * 格式：年份(4位)+一级机构编号(2位)+二级机构编号(2位)+三级机构编号(2位)+编号(2位)
     *
     * @param deptIdFirst 一级机构ID
     * @param deptIdSecond 二级机构ID
     * @param deptIdThird 三级机构ID
     * @return 档案编号
     */
    @Override
    public String generateEmployeeCode(Long deptIdFirst, Long deptIdSecond, Long deptIdThird)
    {
        // 获取当前年份
        int year = Calendar.getInstance().get(Calendar.YEAR);

        // 格式化机构编号为2位
        String deptCode1 = String.format("%02d", deptIdFirst % 100);
        String deptCode2 = String.format("%02d", deptIdSecond % 100);
        String deptCode3 = String.format("%02d", deptIdThird % 100);

        // 组合前缀
        String codePrefix = year + deptCode1 + deptCode2 + deptCode3;

        // 查询当年该机构下的最大编号
        String maxCode = hrEmployeeMapper.selectMaxEmployeeCode(codePrefix);

        int serialNumber = 1;
        if (StringUtils.isNotEmpty(maxCode) && maxCode.length() >= 12)
        {
            try {
                // 提取最后2位序号
                serialNumber = Integer.parseInt(maxCode.substring(10)) + 1;
            } catch (NumberFormatException e) {
                serialNumber = 1;
            }
        }

        // 生成完整编号
        return codePrefix + String.format("%02d", serialNumber);
    }
}
