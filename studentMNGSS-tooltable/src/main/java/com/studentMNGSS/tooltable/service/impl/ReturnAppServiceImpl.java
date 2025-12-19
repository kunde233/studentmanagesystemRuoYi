package com.studentMNGSS.tooltable.service.impl;

import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentMNGSS.tooltable.mapper.ReturnAppMapper;
import com.studentMNGSS.tooltable.domain.ReturnApp;
import com.studentMNGSS.tooltable.service.IReturnAppService;

/**
 * 回校申请Service业务层处理
 * 
 * @author studentMNGSS
 * @date 2025-10-29
 */
@Service
public class ReturnAppServiceImpl implements IReturnAppService 
{
    @Autowired
    private ReturnAppMapper returnAppMapper;

    /**
     * 查询回校申请
     * 
     * @param applicationId 回校申请主键
     * @return 回校申请
     */
    @Override
    public ReturnApp selectReturnAppByApplicationId(Long applicationId)
    {
        return returnAppMapper.selectReturnAppByApplicationId(applicationId);
    }

    /**
     * 查询回校申请列表
     * 
     * @param returnApp 回校申请
     * @return 回校申请
     */
    @Override
    public List<ReturnApp> selectReturnAppList(ReturnApp returnApp)
    {
        return returnAppMapper.selectReturnAppList(returnApp);
    }

    /**
     * 新增回校申请
     * 
     * @param returnApp 回校申请
     * @return 结果
     */
    @Override
    public int insertReturnApp(ReturnApp returnApp)
    {
        returnApp.setCreateTime(DateUtils.getNowDate());
        return returnAppMapper.insertReturnApp(returnApp);
    }

    /**
     * 修改回校申请
     * 
     * @param returnApp 回校申请
     * @return 结果
     */
    @Override
    public int updateReturnApp(ReturnApp returnApp)
    {
        return returnAppMapper.updateReturnApp(returnApp);
    }

    /**
     * 批量删除回校申请
     * 
     * @param applicationIds 需要删除的回校申请主键
     * @return 结果
     */
    @Override
    public int deleteReturnAppByApplicationIds(Long[] applicationIds)
    {
        return returnAppMapper.deleteReturnAppByApplicationIds(applicationIds);
    }

    /**
     * 删除回校申请信息
     * 
     * @param applicationId 回校申请主键
     * @return 结果
     */
    @Override
    public int deleteReturnAppByApplicationId(Long applicationId)
    {
        return returnAppMapper.deleteReturnAppByApplicationId(applicationId);
    }
}
