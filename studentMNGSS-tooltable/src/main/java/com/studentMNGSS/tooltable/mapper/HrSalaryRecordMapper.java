package com.studentMNGSS.tooltable.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studentMNGSS.tooltable.domain.HrSalaryRecord;
import com.studentMNGSS.tooltable.domain.HrSalaryRecordDetail;

/**
 * 薪酬发放记录Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface HrSalaryRecordMapper
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
     * 删除薪酬发放记录
     *
     * @param recordId 薪酬发放记录主键
     * @return 结果
     */
    public int deleteHrSalaryRecordByRecordId(Long recordId);

    /**
     * 批量删除薪酬发放记录
     *
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrSalaryRecordByRecordIds(Long[] recordIds);

    /**
     * 查询薪酬发放明细列表
     *
     * @param recordId 发放记录ID
     * @return 明细列表
     */
    public List<HrSalaryRecordDetail> selectHrSalaryRecordDetailByRecordId(Long recordId);

    /**
     * 新增薪酬发放明细
     *
     * @param detail 明细
     * @return 结果
     */
    public int insertHrSalaryRecordDetail(HrSalaryRecordDetail detail);

    /**
     * 删除薪酬发放明细
     *
     * @param recordId 发放记录ID
     * @return 结果
     */
    public int deleteHrSalaryRecordDetailByRecordId(Long recordId);

    /**
     * 获取最大发放记录编号
     *
     * @param prefix 编号前缀（月份）
     * @return 最大编号
     */
    public String selectMaxRecordCode(String prefix);

    /**
     * 检查员工当月是否已有发放记录
     *
     * @param employeeId 员工ID
     * @param salaryMonth 发放月份
     * @return 记录
     */
    public HrSalaryRecord checkEmployeeMonthRecord(@Param("employeeId") Long employeeId, @Param("salaryMonth") String salaryMonth);
}
