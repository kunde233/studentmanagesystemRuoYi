package com.studentMNGSS.tooltable.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.studentMNGSS.tooltable.mapper.HrSalaryRecordMapper;
import com.studentMNGSS.tooltable.mapper.HrEmployeeMapper;
import com.studentMNGSS.tooltable.mapper.HrSalaryStandardMapper;
import com.studentMNGSS.tooltable.domain.HrSalaryRecord;
import com.studentMNGSS.tooltable.domain.HrSalaryRecordDetail;
import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.domain.HrSalaryStandardDetail;
import com.studentMNGSS.tooltable.service.IHrSalaryRecordService;

/**
 * 薪酬发放记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@Service
public class HrSalaryRecordServiceImpl implements IHrSalaryRecordService
{
    @Autowired
    private HrSalaryRecordMapper hrSalaryRecordMapper;

    @Autowired
    private HrEmployeeMapper hrEmployeeMapper;

    @Autowired
    private HrSalaryStandardMapper hrSalaryStandardMapper;

    /**
     * 查询薪酬发放记录
     *
     * @param recordId 薪酬发放记录主键
     * @return 薪酬发放记录
     */
    @Override
    public HrSalaryRecord selectHrSalaryRecordByRecordId(Long recordId)
    {
        HrSalaryRecord record = hrSalaryRecordMapper.selectHrSalaryRecordByRecordId(recordId);
        if (record != null)
        {
            record.setDetails(hrSalaryRecordMapper.selectHrSalaryRecordDetailByRecordId(recordId));
        }
        return record;
    }

    /**
     * 查询薪酬发放记录列表
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 薪酬发放记录
     */
    @Override
    public List<HrSalaryRecord> selectHrSalaryRecordList(HrSalaryRecord hrSalaryRecord)
    {
        return hrSalaryRecordMapper.selectHrSalaryRecordList(hrSalaryRecord);
    }

    /**
     * 查询待复核的薪酬发放记录列表
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 薪酬发放记录集合
     */
    @Override
    public List<HrSalaryRecord> selectHrSalaryRecordPendingReviewList(HrSalaryRecord hrSalaryRecord)
    {
        return hrSalaryRecordMapper.selectHrSalaryRecordPendingReviewList(hrSalaryRecord);
    }

    /**
     * 新增薪酬发放记录
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertHrSalaryRecord(HrSalaryRecord hrSalaryRecord)
    {
        // 生成编号
        String recordCode = generateRecordCode(hrSalaryRecord.getSalaryMonth());
        hrSalaryRecord.setRecordCode(recordCode);

        // 计算金额
        calculateAmounts(hrSalaryRecord);

        // 设置状态为待复核
        hrSalaryRecord.setStatus("0");

        hrSalaryRecord.setCreateTime(DateUtils.getNowDate());

        int rows = hrSalaryRecordMapper.insertHrSalaryRecord(hrSalaryRecord);

        // 插入明细
        if (hrSalaryRecord.getDetails() != null && !hrSalaryRecord.getDetails().isEmpty())
        {
            for (HrSalaryRecordDetail detail : hrSalaryRecord.getDetails())
            {
                detail.setRecordId(hrSalaryRecord.getRecordId());
                hrSalaryRecordMapper.insertHrSalaryRecordDetail(detail);
            }
        }

        return rows;
    }

    /**
     * 修改薪酬发放记录
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @return 结果
     */
    @Override
    @Transactional
    public int updateHrSalaryRecord(HrSalaryRecord hrSalaryRecord)
    {
        // 计算金额
        calculateAmounts(hrSalaryRecord);

        hrSalaryRecord.setUpdateTime(DateUtils.getNowDate());

        // 删除原有明细
        hrSalaryRecordMapper.deleteHrSalaryRecordDetailByRecordId(hrSalaryRecord.getRecordId());

        // 插入新明细
        if (hrSalaryRecord.getDetails() != null && !hrSalaryRecord.getDetails().isEmpty())
        {
            for (HrSalaryRecordDetail detail : hrSalaryRecord.getDetails())
            {
                detail.setRecordId(hrSalaryRecord.getRecordId());
                hrSalaryRecordMapper.insertHrSalaryRecordDetail(detail);
            }
        }

        return hrSalaryRecordMapper.updateHrSalaryRecord(hrSalaryRecord);
    }

    /**
     * 复核薪酬发放记录
     *
     * @param hrSalaryRecord 薪酬发放记录
     * @param username 复核人
     * @return 结果
     */
    @Override
    public int reviewHrSalaryRecord(HrSalaryRecord hrSalaryRecord, String username)
    {
        hrSalaryRecord.setStatus("1");
        hrSalaryRecord.setReviewer(username);
        hrSalaryRecord.setReviewTime(DateUtils.getNowDate());
        hrSalaryRecord.setUpdateBy(username);
        hrSalaryRecord.setUpdateTime(DateUtils.getNowDate());

        return hrSalaryRecordMapper.updateHrSalaryRecord(hrSalaryRecord);
    }

    /**
     * 批量删除薪酬发放记录
     *
     * @param recordIds 需要删除的薪酬发放记录主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHrSalaryRecordByRecordIds(Long[] recordIds)
    {
        for (Long recordId : recordIds)
        {
            hrSalaryRecordMapper.deleteHrSalaryRecordDetailByRecordId(recordId);
        }
        return hrSalaryRecordMapper.deleteHrSalaryRecordByRecordIds(recordIds);
    }

    /**
     * 删除薪酬发放记录信息
     *
     * @param recordId 薪酬发放记录主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHrSalaryRecordByRecordId(Long recordId)
    {
        hrSalaryRecordMapper.deleteHrSalaryRecordDetailByRecordId(recordId);
        return hrSalaryRecordMapper.deleteHrSalaryRecordByRecordId(recordId);
    }

    /**
     * 检查员工当月是否已有发放记录
     *
     * @param employeeId 员工ID
     * @param salaryMonth 发放月份
     * @return 是否存在
     */
    @Override
    public boolean checkEmployeeMonthRecord(Long employeeId, String salaryMonth)
    {
        HrSalaryRecord record = hrSalaryRecordMapper.checkEmployeeMonthRecord(employeeId, salaryMonth);
        return record != null;
    }

    /**
     * 生成发放记录编号
     *
     * @param salaryMonth 发放月份
     * @return 编号
     */
    private String generateRecordCode(String salaryMonth)
    {
        String prefix = "SR" + salaryMonth.replace("-", "");
        String maxCode = hrSalaryRecordMapper.selectMaxRecordCode(prefix);
        int serialNumber = 1;
        if (StringUtils.isNotEmpty(maxCode))
        {
            try {
                serialNumber = Integer.parseInt(maxCode.substring(prefix.length())) + 1;
            } catch (NumberFormatException e) {
                serialNumber = 1;
            }
        }
        return prefix + String.format("%04d", serialNumber);
    }

    /**
     * 计算金额
     *
     * @param hrSalaryRecord 发放记录
     */
    private void calculateAmounts(HrSalaryRecord hrSalaryRecord)
    {
        BigDecimal baseAmount = BigDecimal.ZERO;

        if (hrSalaryRecord.getDetails() != null)
        {
            for (HrSalaryRecordDetail detail : hrSalaryRecord.getDetails())
            {
                if (detail.getAmount() != null)
                {
                    // 收入类型加，扣除类型减
                    if ("0".equals(detail.getItemType()))
                    {
                        baseAmount = baseAmount.add(detail.getAmount());
                    }
                    else
                    {
                        baseAmount = baseAmount.subtract(detail.getAmount());
                    }
                }
            }
        }

        hrSalaryRecord.setBaseAmount(baseAmount);

        // 计算实发金额 = 标准金额 + 奖励金额 - 扣除金额
        BigDecimal bonusAmount = hrSalaryRecord.getBonusAmount() != null ? hrSalaryRecord.getBonusAmount() : BigDecimal.ZERO;
        BigDecimal deductAmount = hrSalaryRecord.getDeductAmount() != null ? hrSalaryRecord.getDeductAmount() : BigDecimal.ZERO;
        BigDecimal actualAmount = baseAmount.add(bonusAmount).subtract(deductAmount);
        hrSalaryRecord.setActualAmount(actualAmount);
    }

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
    @Override
    @Transactional
    public int batchInsertHrSalaryRecord(Long deptIdFirst, Long deptIdSecond, Long deptIdThird, String salaryMonth, String username)
    {
        // 查询符合条件的员工（有薪酬标准且状态正常）
        HrEmployee queryEmployee = new HrEmployee();
        queryEmployee.setDeptIdFirst(deptIdFirst);
        queryEmployee.setDeptIdSecond(deptIdSecond);
        queryEmployee.setDeptIdThird(deptIdThird);
        List<HrEmployee> employees = hrEmployeeMapper.selectHrEmployeeWithSalaryStandard(queryEmployee);

        int successCount = 0;
        for (HrEmployee employee : employees)
        {
            // 检查该员工本月是否已有发放记录
            if (checkEmployeeMonthRecord(employee.getEmployeeId(), salaryMonth))
            {
                continue;
            }

            // 获取员工的薪酬标准明细
            List<HrSalaryStandardDetail> standardDetails = hrSalaryStandardMapper.selectHrSalaryStandardDetailByStandardId(employee.getSalaryStandardId());

            // 创建发放记录
            HrSalaryRecord record = new HrSalaryRecord();
            record.setEmployeeId(employee.getEmployeeId());
            record.setStandardId(employee.getSalaryStandardId());
            record.setSalaryMonth(salaryMonth);
            record.setRecordCode(generateRecordCode(salaryMonth));
            record.setStatus("0"); // 待复核
            record.setCreateBy(username);
            record.setCreateTime(DateUtils.getNowDate());

            // 转换标准明细为发放明细
            List<HrSalaryRecordDetail> recordDetails = new java.util.ArrayList<>();
            for (HrSalaryStandardDetail standardDetail : standardDetails)
            {
                HrSalaryRecordDetail recordDetail = new HrSalaryRecordDetail();
                recordDetail.setItemId(standardDetail.getItemId());
                recordDetail.setItemType(standardDetail.getItemType());
                recordDetail.setAmount(standardDetail.getAmount());
                recordDetails.add(recordDetail);
            }
            record.setDetails(recordDetails);

            // 计算金额
            calculateAmounts(record);

            // 插入记录
            hrSalaryRecordMapper.insertHrSalaryRecord(record);

            // 插入明细
            for (HrSalaryRecordDetail detail : recordDetails)
            {
                detail.setRecordId(record.getRecordId());
                hrSalaryRecordMapper.insertHrSalaryRecordDetail(detail);
            }

            successCount++;
        }

        return successCount;
    }

    /**
     * 查询有薪酬标准的员工信息（用于批量发放页面，排除当月已登记的员工）
     *
     * @param hrEmployee 查询条件
     * @param salaryMonth 发放月份
     * @return 员工薪酬信息列表
     */
    @Override
    public List<Map<String, Object>> selectEmployeeWithSalaryInfo(HrEmployee hrEmployee, String salaryMonth)
    {
        List<Map<String, Object>> result = new ArrayList<>();

        // 查询有薪酬标准的员工
        List<HrEmployee> employees = hrEmployeeMapper.selectHrEmployeeWithSalaryStandard(hrEmployee);

        for (HrEmployee employee : employees)
        {
            // 排除当月已有发放记录的员工
            if (salaryMonth != null && !salaryMonth.isEmpty())
            {
                if (checkEmployeeMonthRecord(employee.getEmployeeId(), salaryMonth))
                {
                    continue;
                }
            }

            Map<String, Object> empInfo = new HashMap<>();
            empInfo.put("employeeId", employee.getEmployeeId());
            empInfo.put("employeeCode", employee.getEmployeeCode());
            empInfo.put("employeeName", employee.getEmployeeName());
            empInfo.put("positionName", employee.getPositionName());
            empInfo.put("title", employee.getTitle());
            empInfo.put("salaryStandardId", employee.getSalaryStandardId());
            empInfo.put("salaryStandardName", employee.getSalaryStandardName());

            // 获取薪酬标准明细
            List<HrSalaryStandardDetail> standardDetails = hrSalaryStandardMapper.selectHrSalaryStandardDetailByStandardId(employee.getSalaryStandardId());

            // 计算基础金额
            BigDecimal baseAmount = BigDecimal.ZERO;
            List<Map<String, Object>> salaryDetails = new ArrayList<>();
            for (HrSalaryStandardDetail detail : standardDetails)
            {
                Map<String, Object> detailInfo = new HashMap<>();
                detailInfo.put("itemId", detail.getItemId());
                detailInfo.put("itemName", detail.getItemName());
                detailInfo.put("itemType", detail.getItemType());
                detailInfo.put("amount", detail.getAmount());
                salaryDetails.add(detailInfo);

                if (detail.getAmount() != null)
                {
                    if ("0".equals(detail.getItemType()))
                    {
                        baseAmount = baseAmount.add(detail.getAmount());
                    }
                    else
                    {
                        baseAmount = baseAmount.subtract(detail.getAmount());
                    }
                }
            }

            empInfo.put("baseAmount", baseAmount);
            empInfo.put("salaryDetails", salaryDetails);
            result.add(empInfo);
        }

        return result;
    }

    /**
     * 批量新增薪酬发放记录
     *
     * @param records 发放记录列表
     * @param username 操作人
     * @return 成功数量
     */
    @Override
    @Transactional
    public int batchInsertHrSalaryRecords(List<HrSalaryRecord> records, String username)
    {
        int successCount = 0;

        for (HrSalaryRecord record : records)
        {
            // 检查该员工本月是否已有发放记录
            if (checkEmployeeMonthRecord(record.getEmployeeId(), record.getSalaryMonth()))
            {
                continue;
            }

            // 生成编号
            record.setRecordCode(generateRecordCode(record.getSalaryMonth()));
            record.setStatus("0"); // 待复核
            record.setCreateBy(username);
            record.setCreateTime(DateUtils.getNowDate());

            // 插入记录
            hrSalaryRecordMapper.insertHrSalaryRecord(record);

            // 插入明细
            if (record.getDetails() != null && !record.getDetails().isEmpty())
            {
                for (HrSalaryRecordDetail detail : record.getDetails())
                {
                    detail.setRecordId(record.getRecordId());
                    hrSalaryRecordMapper.insertHrSalaryRecordDetail(detail);
                }
            }

            successCount++;
        }

        return successCount;
    }
}
