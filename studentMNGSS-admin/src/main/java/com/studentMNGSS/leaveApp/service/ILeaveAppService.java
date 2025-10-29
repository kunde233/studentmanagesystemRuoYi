package com.studentMNGSS.leaveApp.service;

import java.util.List;
import com.studentMNGSS.leaveApp.domain.LeaveApp;

/**
 * 请假申请Service接口
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
public interface ILeaveAppService 
{
    /**
     * 查询请假申请
     * 
     * @param applicationId 请假申请主键
     * @return 请假申请
     */
    public LeaveApp selectLeaveAppByApplicationId(Long applicationId);

    /**
     * 查询请假申请列表
     * 
     * @param leaveApp 请假申请
     * @return 请假申请集合
     */
    public List<LeaveApp> selectLeaveAppList(LeaveApp leaveApp);

    /**
     * 新增请假申请
     * 
     * @param leaveApp 请假申请
     * @return 结果
     */
    public int insertLeaveApp(LeaveApp leaveApp);

    /**
     * 修改请假申请
     * 
     * @param leaveApp 请假申请
     * @return 结果
     */
    public int updateLeaveApp(LeaveApp leaveApp);

    /**
     * 批量删除请假申请
     * 
     * @param applicationIds 需要删除的请假申请主键集合
     * @return 结果
     */
    public int deleteLeaveAppByApplicationIds(Long[] applicationIds);

    /**
     * 删除请假申请信息
     * 
     * @param applicationId 请假申请主键
     * @return 结果
     */
    public int deleteLeaveAppByApplicationId(Long applicationId);
}
