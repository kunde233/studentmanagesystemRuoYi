package com.ruoyi.application.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.application.mapper.AppLeaveApplicationMapper;
import com.ruoyi.application.domain.AppLeaveApplication;
import com.ruoyi.application.service.IAppLeaveApplicationService;

/**
 * 请假申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
@Service
public class AppLeaveApplicationServiceImpl implements IAppLeaveApplicationService 
{
    @Autowired
    private AppLeaveApplicationMapper appLeaveApplicationMapper;

    /**
     * 查询请假申请
     * 
     * @param leaveId 请假申请主键
     * @return 请假申请
     */
    @Override
    public AppLeaveApplication selectAppLeaveApplicationByLeaveId(Long leaveId)
    {
        return appLeaveApplicationMapper.selectAppLeaveApplicationByLeaveId(leaveId);
    }

    /**
     * 查询请假申请列表
     * 
     * @param appLeaveApplication 请假申请
     * @return 请假申请
     */
    @Override
    public List<AppLeaveApplication> selectAppLeaveApplicationList(AppLeaveApplication appLeaveApplication)
    {
        return appLeaveApplicationMapper.selectAppLeaveApplicationList(appLeaveApplication);
    }

    /**
     * 新增请假申请
     * 
     * @param appLeaveApplication 请假申请
     * @return 结果
     */
    @Override
    public int insertAppLeaveApplication(AppLeaveApplication appLeaveApplication)
    {
        appLeaveApplication.setCreateTime(DateUtils.getNowDate());
        return appLeaveApplicationMapper.insertAppLeaveApplication(appLeaveApplication);
    }

    /**
     * 修改请假申请
     * 
     * @param appLeaveApplication 请假申请
     * @return 结果
     */
    @Override
    public int updateAppLeaveApplication(AppLeaveApplication appLeaveApplication)
    {
        appLeaveApplication.setUpdateTime(DateUtils.getNowDate());
        return appLeaveApplicationMapper.updateAppLeaveApplication(appLeaveApplication);
    }

    /**
     * 批量删除请假申请
     * 
     * @param leaveIds 需要删除的请假申请主键
     * @return 结果
     */
    @Override
    public int deleteAppLeaveApplicationByLeaveIds(Long[] leaveIds)
    {
        return appLeaveApplicationMapper.deleteAppLeaveApplicationByLeaveIds(leaveIds);
    }

    /**
     * 删除请假申请信息
     * 
     * @param leaveId 请假申请主键
     * @return 结果
     */
    @Override
    public int deleteAppLeaveApplicationByLeaveId(Long leaveId)
    {
        return appLeaveApplicationMapper.deleteAppLeaveApplicationByLeaveId(leaveId);
    }
}
