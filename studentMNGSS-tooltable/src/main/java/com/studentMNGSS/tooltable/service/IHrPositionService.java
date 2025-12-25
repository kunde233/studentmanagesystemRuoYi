package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrPosition;

/**
 * 职位Service接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface IHrPositionService
{
    /**
     * 查询职位
     *
     * @param positionId 职位主键
     * @return 职位
     */
    public HrPosition selectHrPositionByPositionId(Long positionId);

    /**
     * 查询职位列表
     *
     * @param hrPosition 职位
     * @return 职位集合
     */
    public List<HrPosition> selectHrPositionList(HrPosition hrPosition);

    /**
     * 根据部门ID查询职位列表
     *
     * @param deptId 部门ID
     * @return 职位集合
     */
    public List<HrPosition> selectHrPositionByDeptId(Long deptId);

    /**
     * 查询所有职位列表
     *
     * @return 职位集合
     */
    public List<HrPosition> selectHrPositionAll();

    /**
     * 新增职位
     *
     * @param hrPosition 职位
     * @return 结果
     */
    public int insertHrPosition(HrPosition hrPosition);

    /**
     * 修改职位
     *
     * @param hrPosition 职位
     * @return 结果
     */
    public int updateHrPosition(HrPosition hrPosition);

    /**
     * 批量删除职位
     *
     * @param positionIds 需要删除的职位主键集合
     * @return 结果
     */
    public int deleteHrPositionByPositionIds(Long[] positionIds);

    /**
     * 删除职位信息
     *
     * @param positionId 职位主键
     * @return 结果
     */
    public int deleteHrPositionByPositionId(Long positionId);

    /**
     * 校验职位编码是否唯一
     *
     * @param hrPosition 职位
     * @return 结果
     */
    public boolean checkPositionCodeUnique(HrPosition hrPosition);

    /**
     * 校验职位名称是否唯一
     *
     * @param hrPosition 职位
     * @return 结果
     */
    public boolean checkPositionNameUnique(HrPosition hrPosition);
}
