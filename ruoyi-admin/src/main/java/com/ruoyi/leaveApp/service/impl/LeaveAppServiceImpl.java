package com.ruoyi.leaveApp.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.leaveApp.mapper.LeaveAppMapper;
import com.ruoyi.leaveApp.domain.LeaveApp;
import com.ruoyi.leaveApp.service.ILeaveAppService;

import javax.security.auth.Subject;

/**
 * 请假申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
@Service
public class LeaveAppServiceImpl implements ILeaveAppService 
{
    @Autowired
    private LeaveAppMapper leaveAppMapper;

    /**
     * 查询请假申请
     * 
     * @param applicationId 请假申请主键
     * @return 请假申请
     */
    @Override
    public LeaveApp selectLeaveAppByApplicationId(Long applicationId)
    {
        return leaveAppMapper.selectLeaveAppByApplicationId(applicationId);
    }

    /**
     * 查询请假申请列表
     * 
     * @param leaveApp 请假申请
     * @return 请假申请
     */
    @Override
    public List<LeaveApp> selectLeaveAppList(LeaveApp leaveApp)
    {
        return leaveAppMapper.selectLeaveAppList(leaveApp);
    }

    /**
     * 新增请假申请
     * 
     * @param leaveApp 请假申请
     * @return 结果
     */
    @Override
    public int insertLeaveApp(LeaveApp leaveApp)
    {
        leaveApp.setCreateTime(DateUtils.getNowDate());
        //获得当前用户的id
        Long userId = SecurityUtils.getUserId();
        leaveApp.setStudentId(userId);
        return leaveAppMapper.insertLeaveApp(leaveApp);
    }

    /**
     * 修改请假申请
     * 
     * @param leaveApp 请假申请
     * @return 结果
     */
    @Override
    public int updateLeaveApp(LeaveApp leaveApp)
    {
        leaveApp.setUpdateTime(DateUtils.getNowDate());
        return leaveAppMapper.updateLeaveApp(leaveApp);
    }

    /**
     * 批量删除请假申请
     * 
     * @param applicationIds 需要删除的请假申请主键
     * @return 结果
     */
    @Override
    public int deleteLeaveAppByApplicationIds(Long[] applicationIds)
    {
        return leaveAppMapper.deleteLeaveAppByApplicationIds(applicationIds);
    }

    /**
     * 删除请假申请信息
     * 
     * @param applicationId 请假申请主键
     * @return 结果
     */
    @Override
    public int deleteLeaveAppByApplicationId(Long applicationId)
    {
        return leaveAppMapper.deleteLeaveAppByApplicationId(applicationId);
    }
}
