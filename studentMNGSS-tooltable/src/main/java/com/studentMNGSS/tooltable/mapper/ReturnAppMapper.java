package com.studentMNGSS.tooltable.mapper;

import java.util.List;
import com.studentMNGSS.tooltable.domain.ReturnApp;

/**
 * 回校申请Mapper接口
 * 
 * @author studentMNGSS
 * @date 2025-10-29
 */
public interface ReturnAppMapper 
{
    /**
     * 查询回校申请
     * 
     * @param applicationId 回校申请主键
     * @return 回校申请
     */
    public ReturnApp selectReturnAppByApplicationId(Long applicationId);

    /**
     * 查询回校申请列表
     * 
     * @param returnApp 回校申请
     * @return 回校申请集合
     */
    public List<ReturnApp> selectReturnAppList(ReturnApp returnApp);

    /**
     * 新增回校申请
     * 
     * @param returnApp 回校申请
     * @return 结果
     */
    public int insertReturnApp(ReturnApp returnApp);

    /**
     * 修改回校申请
     * 
     * @param returnApp 回校申请
     * @return 结果
     */
    public int updateReturnApp(ReturnApp returnApp);

    /**
     * 删除回校申请
     * 
     * @param applicationId 回校申请主键
     * @return 结果
     */
    public int deleteReturnAppByApplicationId(Long applicationId);

    /**
     * 批量删除回校申请
     * 
     * @param applicationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReturnAppByApplicationIds(Long[] applicationIds);
}
