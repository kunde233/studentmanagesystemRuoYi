package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.ReturnApro;

/**
 * 回校审批Service接口
 * 
 * @author studentMNGSS
 * @date 2025-11-03
 */
public interface IReturnAproService 
{
    /**
     * 查询回校审批
     * 
     * @param applicationId 回校审批主键
     * @return 回校审批
     */
    public ReturnApro selectReturnAproByApplicationId(Long applicationId);

    /**
     * 查询回校审批列表
     * 
     * @param returnApro 回校审批
     * @return 回校审批集合
     */
    public List<ReturnApro> selectReturnAproList(ReturnApro returnApro);

    /**
     * 新增回校审批
     * 
     * @param returnApro 回校审批
     * @return 结果
     */
    public int insertReturnApro(ReturnApro returnApro);

    /**
     * 修改回校审批
     * 
     * @param returnApro 回校审批
     * @return 结果
     */
    public int updateReturnApro(ReturnApro returnApro);

    /**
     * 批量删除回校审批
     * 
     * @param applicationIds 需要删除的回校审批主键集合
     * @return 结果
     */
    public int deleteReturnAproByApplicationIds(Long[] applicationIds);

    /**
     * 删除回校审批信息
     * 
     * @param applicationId 回校审批主键
     * @return 结果
     */
    public int deleteReturnAproByApplicationId(Long applicationId);
}
