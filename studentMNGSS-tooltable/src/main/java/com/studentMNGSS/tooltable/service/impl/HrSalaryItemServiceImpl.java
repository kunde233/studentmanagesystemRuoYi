package com.studentMNGSS.tooltable.service.impl;

import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentMNGSS.tooltable.mapper.HrSalaryItemMapper;
import com.studentMNGSS.tooltable.domain.HrSalaryItem;
import com.studentMNGSS.tooltable.service.IHrSalaryItemService;

/**
 * 薪酬项目Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@Service
public class HrSalaryItemServiceImpl implements IHrSalaryItemService
{
    @Autowired
    private HrSalaryItemMapper hrSalaryItemMapper;

    /**
     * 查询薪酬项目
     *
     * @param itemId 薪酬项目主键
     * @return 薪酬项目
     */
    @Override
    public HrSalaryItem selectHrSalaryItemByItemId(Long itemId)
    {
        return hrSalaryItemMapper.selectHrSalaryItemByItemId(itemId);
    }

    /**
     * 查询薪酬项目列表
     *
     * @param hrSalaryItem 薪酬项目
     * @return 薪酬项目
     */
    @Override
    public List<HrSalaryItem> selectHrSalaryItemList(HrSalaryItem hrSalaryItem)
    {
        return hrSalaryItemMapper.selectHrSalaryItemList(hrSalaryItem);
    }

    /**
     * 查询所有启用的薪酬项目
     *
     * @return 薪酬项目集合
     */
    @Override
    public List<HrSalaryItem> selectHrSalaryItemAll()
    {
        return hrSalaryItemMapper.selectHrSalaryItemAll();
    }

    /**
     * 新增薪酬项目
     *
     * @param hrSalaryItem 薪酬项目
     * @return 结果
     */
    @Override
    public int insertHrSalaryItem(HrSalaryItem hrSalaryItem)
    {
        hrSalaryItem.setCreateTime(DateUtils.getNowDate());
        return hrSalaryItemMapper.insertHrSalaryItem(hrSalaryItem);
    }

    /**
     * 修改薪酬项目
     *
     * @param hrSalaryItem 薪酬项目
     * @return 结果
     */
    @Override
    public int updateHrSalaryItem(HrSalaryItem hrSalaryItem)
    {
        hrSalaryItem.setUpdateTime(DateUtils.getNowDate());
        return hrSalaryItemMapper.updateHrSalaryItem(hrSalaryItem);
    }

    /**
     * 批量删除薪酬项目
     *
     * @param itemIds 需要删除的薪酬项目主键
     * @return 结果
     */
    @Override
    public int deleteHrSalaryItemByItemIds(Long[] itemIds)
    {
        return hrSalaryItemMapper.deleteHrSalaryItemByItemIds(itemIds);
    }

    /**
     * 删除薪酬项目信息
     *
     * @param itemId 薪酬项目主键
     * @return 结果
     */
    @Override
    public int deleteHrSalaryItemByItemId(Long itemId)
    {
        return hrSalaryItemMapper.deleteHrSalaryItemByItemId(itemId);
    }

    /**
     * 校验项目编码是否唯一
     *
     * @param hrSalaryItem 薪酬项目
     * @return 结果
     */
    @Override
    public boolean checkItemCodeUnique(HrSalaryItem hrSalaryItem)
    {
        Long itemId = StringUtils.isNull(hrSalaryItem.getItemId()) ? -1L : hrSalaryItem.getItemId();
        HrSalaryItem info = hrSalaryItemMapper.checkItemCodeUnique(hrSalaryItem.getItemCode());
        if (StringUtils.isNotNull(info) && info.getItemId().longValue() != itemId.longValue())
        {
            return false;
        }
        return true;
    }
}
