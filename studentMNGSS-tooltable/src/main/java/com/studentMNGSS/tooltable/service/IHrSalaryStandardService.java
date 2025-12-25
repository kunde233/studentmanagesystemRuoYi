package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrSalaryStandard;

/**
 * 薪酬标准Service接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface IHrSalaryStandardService
{
    /**
     * 查询薪酬标准
     *
     * @param standardId 薪酬标准主键
     * @return 薪酬标准
     */
    public HrSalaryStandard selectHrSalaryStandardByStandardId(Long standardId);

    /**
     * 查询薪酬标准列表
     *
     * @param hrSalaryStandard 薪酬标准
     * @return 薪酬标准集合
     */
    public List<HrSalaryStandard> selectHrSalaryStandardList(HrSalaryStandard hrSalaryStandard);

    /**
     * 查询所有启用的薪酬标准
     *
     * @return 薪酬标准集合
     */
    public List<HrSalaryStandard> selectHrSalaryStandardAll();

    /**
     * 新增薪酬标准
     *
     * @param hrSalaryStandard 薪酬标准
     * @return 结果
     */
    public int insertHrSalaryStandard(HrSalaryStandard hrSalaryStandard);

    /**
     * 修改薪酬标准
     *
     * @param hrSalaryStandard 薪酬标准
     * @return 结果
     */
    public int updateHrSalaryStandard(HrSalaryStandard hrSalaryStandard);

    /**
     * 复核薪酬标准
     *
     * @param hrSalaryStandard 薪酬标准
     * @param username 复核人
     * @return 结果
     */
    public int reviewHrSalaryStandard(HrSalaryStandard hrSalaryStandard, String username);

    /**
     * 批量删除薪酬标准
     *
     * @param standardIds 需要删除的薪酬标准主键集合
     * @return 结果
     */
    public int deleteHrSalaryStandardByStandardIds(Long[] standardIds);

    /**
     * 删除薪酬标准信息
     *
     * @param standardId 薪酬标准主键
     * @return 结果
     */
    public int deleteHrSalaryStandardByStandardId(Long standardId);

    /**
     * 生成薪酬标准编号
     *
     * @return 编号
     */
    public String generateStandardCode();
}
