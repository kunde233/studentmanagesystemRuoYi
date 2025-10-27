package com.ruoyi.application.mapper;

import java.util.List;
import com.ruoyi.application.domain.AppLeaveApplication;

/**
 * 请假申请Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
public interface AppLeaveApplicationMapper 
{
    /**
     * 查询请假申请
     * 
     * @param leaveId 请假申请主键
     * @return 请假申请
     */
    public AppLeaveApplication selectAppLeaveApplicationByLeaveId(Long leaveId);

    /**
     * 查询请假申请列表
     * 
     * @param appLeaveApplication 请假申请
     * @return 请假申请集合
     */
    public List<AppLeaveApplication> selectAppLeaveApplicationList(AppLeaveApplication appLeaveApplication);

    /**
     * 新增请假申请
     * 
     * @param appLeaveApplication 请假申请
     * @return 结果
     */
    public int insertAppLeaveApplication(AppLeaveApplication appLeaveApplication);

    /**
     * 修改请假申请
     * 
     * @param appLeaveApplication 请假申请
     * @return 结果
     */
    public int updateAppLeaveApplication(AppLeaveApplication appLeaveApplication);

    /**
     * 删除请假申请
     * 
     * @param leaveId 请假申请主键
     * @return 结果
     */
    public int deleteAppLeaveApplicationByLeaveId(Long leaveId);

    /**
     * 批量删除请假申请
     * 
     * @param leaveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppLeaveApplicationByLeaveIds(Long[] leaveIds);
}
