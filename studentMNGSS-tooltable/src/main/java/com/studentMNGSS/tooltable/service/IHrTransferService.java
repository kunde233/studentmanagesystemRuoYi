package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrTransfer;

/**
 * 人员调动Service接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface IHrTransferService
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
     * @param username 创建人
     * @return 结果
     */
    public int insertHrTransfer(HrTransfer hrTransfer, String username);

    /**
     * 修改人员调动
     *
     * @param hrTransfer 人员调动
     * @return 结果
     */
    public int updateHrTransfer(HrTransfer hrTransfer);

    /**
     * 复核人员调动
     *
     * @param hrTransfer 人员调动
     * @param username 复核人
     * @return 结果
     */
    public int reviewHrTransfer(HrTransfer hrTransfer, String username);

    /**
     * 批量删除人员调动
     *
     * @param transferIds 需要删除的人员调动主键集合
     * @return 结果
     */
    public int deleteHrTransferByTransferIds(Long[] transferIds);

    /**
     * 删除人员调动信息
     *
     * @param transferId 人员调动主键
     * @return 结果
     */
    public int deleteHrTransferByTransferId(Long transferId);
}
