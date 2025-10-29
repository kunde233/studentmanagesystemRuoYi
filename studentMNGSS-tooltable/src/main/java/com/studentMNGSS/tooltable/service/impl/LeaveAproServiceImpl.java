package com.studentMNGSS.tooltable.service.impl;

import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.tooltable.domain.LeaveApro;
import com.studentMNGSS.tooltable.service.LleaveAproService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentMNGSS.tooltable.mapper.leaveAproMapper;


/**
 * 请假审批Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-29
 */
@Service
public class LeaveAproServiceImpl implements LleaveAproService
{
    @Autowired
    private leaveAproMapper leaveAproMapper;

    /**
     * 查询请假审批
     * 
     * @param applicationId 请假审批主键
     * @return 请假审批
     */
    @Override
    public LeaveApro selectleaveAproByApplicationId(Long applicationId)
    {
        return leaveAproMapper.selectleaveAproByApplicationId(applicationId);
    }

    /**
     * 查询请假审批列表
     * 
     * @param leaveApro 请假审批
     * @return 请假审批
     */
    @Override
    public List<LeaveApro> selectleaveAproList(LeaveApro leaveApro)
    {
        return leaveAproMapper.selectleaveAproList(leaveApro);
    }

    /**
     * 新增请假审批
     * 
     * @param leaveApro 请假审批
     * @return 结果
     */
    @Override
    public int insertleaveApro(LeaveApro leaveApro)
    {
        leaveApro.setCreateTime(DateUtils.getNowDate());
        return leaveAproMapper.insertleaveApro(leaveApro);
    }

    /**
     * 修改请假审批
     * 
     * @param leaveApro 请假审批
     * @return 结果
     */
    @Override
    public int updateleaveApro(LeaveApro leaveApro)
    {
        leaveApro.setUpdateTime(DateUtils.getNowDate());
        return leaveAproMapper.updateleaveApro(leaveApro);
    }

    /**
     * 批量删除请假审批
     * 
     * @param applicationIds 需要删除的请假审批主键
     * @return 结果
     */
    @Override
    public int deleteleaveAproByApplicationIds(Long[] applicationIds)
    {
        return leaveAproMapper.deleteleaveAproByApplicationIds(applicationIds);
    }

    /**
     * 删除请假审批信息
     * 
     * @param applicationId 请假审批主键
     * @return 结果
     */
    @Override
    public int deleteleaveAproByApplicationId(Long applicationId)
    {
        return leaveAproMapper.deleteleaveAproByApplicationId(applicationId);
    }
}
