package com.studentMNGSS.tooltable.service;

import java.util.List;
import java.util.Map;
import com.studentMNGSS.tooltable.domain.HrSalaryRecord;
import com.studentMNGSS.tooltable.domain.HrEmployee;

/**
 * 薪酬发放记录Service接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface IHrSalaryRecordService
{
    /**
     * 查询薪酬发放记录
     *
     * @param recordId 薪酬发放记录主键
     * @return 薪酬发放记录
     */
    public HrSalaryRecord selectHrSalaryRecordByRecordId(Long recordId);

    /**
     * 查询薪酬发放记录列表
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 薪酬发放记录集合
     */
    public List<HrSalaryRecord> selectHrSalaryRecordList(HrSalaryRecord hrSalaryRecord);

    /**
     * 查询待复核的薪酬发放记录列表
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 薪酬发放记录集合
     */
    public List<HrSalaryRecord> selectHrSalaryRecordPendingReviewList(HrSalaryRecord hrSalaryRecord);

    /**
     * 新增薪酬发放记录
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 结果
     */
    public int insertHrSalaryRecord(HrSalaryRecord hrSalaryRecord);

    /**
     * 修改薪酬发放记录
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 结果
     */
    public int updateHrSalaryRecord(HrSalaryRecord hrSalaryRecord);

    /**
     * 复核薪酬发放记录
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @param username 复核人
     * @return 结果
     */
    public int reviewHrSalaryRecord(HrSalaryRecord hrSalaryRecord, String username);

    /**
     * 批量删除薪酬发放记录
     *
     * @param recordIds 需要删除的薪酬发放记录主键集合
     * @return 结果
     */
    public int deleteHrSalaryRecordByRecordIds(Long[] recordIds);

    /**
     * 删除薪酬发放记录信息
     *
     * @param recordId 薪酬发放记录主键
     * @return 结果
     */
    public int deleteHrSalaryRecordByRecordId(Long recordId);

    /**
     * 检查员工当月是否已有发放记录
     *
     * @param employeeId 员工ID
     * @param salaryMonth 发放月份
     * @return 是否存在
     */
    public boolean checkEmployeeMonthRecord(Long employeeId, String salaryMonth);

    /**
     * 批量新增薪酬发放记录
     *
     * @param deptIdFirst 一级机构ID
     * @param deptIdSecond 二级机构ID
     * @param deptIdThird 三级机构ID
     * @param salaryMonth 发放月份
     * @param username 操作人
     * @return 成功数量
     */
    public int batchInsertHrSalaryRecord(Long deptIdFirst, Long deptIdSecond, Long deptIdThird, String salaryMonth, String username);

    /**
     * 查询有薪酬标准的员工信息（用于批量发放页面，排除当月已登记的员工）
     *
     * @param hrEmployee 查询条件
     * @param salaryMonth 发放月份
     * @return 员工薪酬信息列表
     */
    public List<Map<String, Object>> selectEmployeeWithSalaryInfo(HrEmployee hrEmployee, String salaryMonth);

    /**
     * 批量新增薪酬发放记录
     *
     * @param records 发放记录列表
     * @param username 操作人
     * @return 成功数量
     */
    public int batchInsertHrSalaryRecords(List<HrSalaryRecord> records, String username);
}
