package com.studentMNGSS.tooltable.service.impl;

import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentMNGSS.tooltable.mapper.ReturnAproMapper;
import com.studentMNGSS.tooltable.domain.ReturnApro;
import com.studentMNGSS.tooltable.service.IReturnAproService;

/**
 * 回校审批Service业务层处理
 * 
 * @author studentMNGSS
 * @date 2025-11-03
 */
@Service
public class ReturnAproServiceImpl implements IReturnAproService 
{
    @Autowired
    private ReturnAproMapper returnAproMapper;

    /**
     * 查询回校审批
     * 
     * @param applicationId 回校审批主键
     * @return 回校审批
     */
    @Override
    public ReturnApro selectReturnAproByApplicationId(Long applicationId)
    {
        return returnAproMapper.selectReturnAproByApplicationId(applicationId);
    }

    /**
     * 查询回校审批列表
     * 
     * @param returnApro 回校审批
     * @return 回校审批
     */
    @Override
    public List<ReturnApro> selectReturnAproList(ReturnApro returnApro)
    {
        return returnAproMapper.selectReturnAproList(returnApro);
    }

    /**
     * 新增回校审批
     * 
     * @param returnApro 回校审批
     * @return 结果
     */
    @Override
    public int insertReturnApro(ReturnApro returnApro)
    {
        returnApro.setCreateTime(DateUtils.getNowDate());
        return returnAproMapper.insertReturnApro(returnApro);
    }

    /**
     * 修改回校审批
     * 
     * @param returnApro 回校审批
     * @return 结果
     */
    @Override
    public int updateReturnApro(ReturnApro returnApro)
    {
        return returnAproMapper.updateReturnApro(returnApro);
    }

    /**
     * 批量删除回校审批
     * 
     * @param applicationIds 需要删除的回校审批主键
     * @return 结果
     */
    @Override
    public int deleteReturnAproByApplicationIds(Long[] applicationIds)
    {
        return returnAproMapper.deleteReturnAproByApplicationIds(applicationIds);
    }

    /**
     * 删除回校审批信息
     * 
     * @param applicationId 回校审批主键
     * @return 结果
     */
    @Override
    public int deleteReturnAproByApplicationId(Long applicationId)
    {
        return returnAproMapper.deleteReturnAproByApplicationId(applicationId);
    }
}
