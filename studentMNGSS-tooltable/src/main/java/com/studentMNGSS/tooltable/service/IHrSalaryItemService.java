package com.studentMNGSS.tooltable.service;

import java.util.List;
import com.studentMNGSS.tooltable.domain.HrSalaryItem;

/**
 * 薪酬项目Service接口
 *
 * @author ruoyi
 * @date 2025-12-25
 */
public interface IHrSalaryItemService
{
    /**
     * 查询薪酬项目
     *
     * @param itemId 薪酬项目主键
     * @return 薪酬项目
     */
    public HrSalaryItem selectHrSalaryItemByItemId(Long itemId);

    /**
     * 查询薪酬项目列表
     *
     * @param hrSalaryItem 薪酬项目
     * @return 薪酬项目集合
     */
    public List<HrSalaryItem> selectHrSalaryItemList(HrSalaryItem hrSalaryItem);

    /**
     * 查询所有启用的薪酬项目
     *
     * @return 薪酬项目集合
     */
    public List<HrSalaryItem> selectHrSalaryItemAll();

    /**
     * 新增薪酬项目
     *
     * @param hrSalaryItem 薪酬项目
     * @return 结果
     */
    public int insertHrSalaryItem(HrSalaryItem hrSalaryItem);

    /**
     * 修改薪酬项目
     *
     * @param hrSalaryItem 薪酬项目
     * @return 结果
     */
    public int updateHrSalaryItem(HrSalaryItem hrSalaryItem);

    /**
     * 批量删除薪酬项目
     *
     * @param itemIds 需要删除的薪酬项目主键集合
     * @return 结果
     */
    public int deleteHrSalaryItemByItemIds(Long[] itemIds);

    /**
     * 删除薪酬项目信息
     *
     * @param itemId 薪酬项目主键
     * @return 结果
     */
    public int deleteHrSalaryItemByItemId(Long itemId);

    /**
     * 校验项目编码是否唯一
     *
     * @param hrSalaryItem 薪酬项目
     * @return 结果
     */
    public boolean checkItemCodeUnique(HrSalaryItem hrSalaryItem);
}
