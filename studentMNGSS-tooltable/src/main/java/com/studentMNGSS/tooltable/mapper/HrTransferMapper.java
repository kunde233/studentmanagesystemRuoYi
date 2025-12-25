package com.studentMNGSS.tooltable.mapper;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrTransfer;

/**
 * 人员调动Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface HrTransferMapper
{
    /**
     * 查询人员调动
     *
     * @param transferId 人员调动主键
     * @return 人员调动
     */
    public HrTransfer selectHrTransferByTransferId(Long transferId);

    /**
     * 查询人员调动列表
     *
     * @param hrTransfer 人员调动
     * @return 人员调动集合
     */
    public List<HrTransfer> selectHrTransferList(HrTransfer hrTransfer);

    /**
     * 新增人员调动
     *
     * @param hrTransfer 人员调动
     * @return 结果
     */
    public int insertHrTransfer(HrTransfer hrTransfer);

    /**
     * 修改人员调动
     *
     * @param hrTransfer 人员调动
     * @return 结果
     */
    public int updateHrTransfer(HrTransfer hrTransfer);

    /**
     * 删除人员调动
     *
     * @param transferId 人员调动主键
     * @return 结果
     */
    public int deleteHrTransferByTransferId(Long transferId);

    /**
     * 批量删除人员调动
     *
     * @param transferIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrTransferByTransferIds(Long[] transferIds);

    /**
     * 获取今日调动记录数量（用于生成编号）
     *
     * @return 数量
     */
    public int selectTodayTransferCount();
}
