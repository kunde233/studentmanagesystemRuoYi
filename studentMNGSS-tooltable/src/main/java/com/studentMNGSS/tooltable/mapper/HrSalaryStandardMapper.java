package com.studentMNGSS.tooltable.mapper;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrSalaryStandard;
import com.studentMNGSS.tooltable.domain.HrSalaryStandardDetail;

/**
 * 薪酬标准Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface HrSalaryStandardMapper
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
     * 删除薪酬标准
     *
     * @param standardId 薪酬标准主键
     * @return 结果
     */
    public int deleteHrSalaryStandardByStandardId(Long standardId);

    /**
     * 批量删除薪酬标准
     *
     * @param standardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrSalaryStandardByStandardIds(Long[] standardIds);

    /**
     * 查询薪酬标准明细列表
     *
     * @param standardId 薪酬标准ID
     * @return 明细列表
     */
    public List<HrSalaryStandardDetail> selectHrSalaryStandardDetailByStandardId(Long standardId);

    /**
     * 新增薪酬标准明细
     *
     * @param detail 明细
     * @return 结果
     */
    public int insertHrSalaryStandardDetail(HrSalaryStandardDetail detail);

    /**
     * 删除薪酬标准明细
     *
     * @param standardId 薪酬标准ID
     * @return 结果
     */
    public int deleteHrSalaryStandardDetailByStandardId(Long standardId);

    /**
     * 校验薪酬标准编号是否唯一
     *
     * @param standardCode 编号
     * @return 结果
     */
    public HrSalaryStandard checkStandardCodeUnique(String standardCode);

    /**
     * 获取最大薪酬标准编号
     *
     * @return 最大编号
     */
    public String selectMaxStandardCode();
}
