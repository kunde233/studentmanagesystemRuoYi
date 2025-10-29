package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.LeaveApro;
/**
 * 请假审批Service接口
 * 
 * @author ruoyi
 * @date 2025-10-29
 */
public interface LleaveAproService
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
     * 批量删除请假审批
     * 
     * @param applicationIds 需要删除的请假审批主键集合
     * @return 结果
     */
    public int deleteleaveAproByApplicationIds(Long[] applicationIds);

    /**
     * 删除请假审批信息
     * 
     * @param applicationId 请假审批主键
     * @return 结果
     */
    public int deleteleaveAproByApplicationId(Long applicationId);
}
