package com.studentMNGSS.tooltable.mapper;

import com.studentMNGSS.tooltable.domain.LeaveApro;

import java.util.List;


/**
 * 请假审批Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-29
 */
public interface leaveAproMapper 
{
    /**
     * 查询请假审批
     * 
     * @param applicationId 请假审批主键
     * @return 请假审批
     */
    public LeaveApro selectleaveAproByApplicationId(Long applicationId);

    /**
     * 查询请假审批列表
     * 
     * @param leaveApro 请假审批
     * @return 请假审批集合
     */
    public List<LeaveApro> selectleaveAproList(LeaveApro leaveApro);

    /**
     * 新增请假审批
     * 
     * @param leaveApro 请假审批
     * @return 结果
     */
    public int insertleaveApro(LeaveApro leaveApro);

    /**
     * 修改请假审批
     * 
     * @param leaveApro 请假审批
     * @return 结果
     */
    public int updateleaveApro(LeaveApro leaveApro);

    /**
     * 删除请假审批
     * 
     * @param applicationId 请假审批主键
     * @return 结果
     */
    public int deleteleaveAproByApplicationId(Long applicationId);

    /**
     * 批量删除请假审批
     * 
     * @param applicationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteleaveAproByApplicationIds(Long[] applicationIds);
}
