package com.studentMNGSS.tooltable.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.studentMNGSS.common.annotation.Log;
import com.studentMNGSS.common.core.controller.BaseController;
import com.studentMNGSS.common.core.domain.AjaxResult;
import com.studentMNGSS.common.enums.BusinessType;
import com.studentMNGSS.tooltable.domain.HrSalaryItem;
import com.studentMNGSS.tooltable.service.IHrSalaryItemService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 薪酬项目Controller
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@RestController
@RequestMapping("/hr/salaryItem")
public class HrSalaryItemController extends BaseController
{
    @Autowired
    private IHrSalaryItemService hrSalaryItemService;

    /**
     * 查询薪酬项目列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryItem:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrSalaryItem hrSalaryItem)
    {
        startPage();
        List<HrSalaryItem> list = hrSalaryItemService.selectHrSalaryItemList(hrSalaryItem);
        return getDataTable(list);
    }

    /**
     * 查询所有启用的薪酬项目
     */
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<HrSalaryItem> list = hrSalaryItemService.selectHrSalaryItemAll();
        return success(list);
    }

    /**
     * 导出薪酬项目列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryItem:export')")
    @Log(title = "薪酬项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrSalaryItem hrSalaryItem)
    {
        List<HrSalaryItem> list = hrSalaryItemService.selectHrSalaryItemList(hrSalaryItem);
        ExcelUtil<HrSalaryItem> util = new ExcelUtil<HrSalaryItem>(HrSalaryItem.class);
        util.exportExcel(response, list, "薪酬项目数据");
    }

    /**
     * 获取薪酬项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryItem:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return success(hrSalaryItemService.selectHrSalaryItemByItemId(itemId));
    }

    /**
     * 新增薪酬项目
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryItem:add')")
    @Log(title = "薪酬项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrSalaryItem hrSalaryItem)
    {
        if (!hrSalaryItemService.checkItemCodeUnique(hrSalaryItem))
        {
            return error("新增薪酬项目'" + hrSalaryItem.getItemName() + "'失败，项目编码已存在");
        }
        hrSalaryItem.setCreateBy(getUsername());
        return toAjax(hrSalaryItemService.insertHrSalaryItem(hrSalaryItem));
    }

    /**
     * 修改薪酬项目
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryItem:edit')")
    @Log(title = "薪酬项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrSalaryItem hrSalaryItem)
    {
        if (!hrSalaryItemService.checkItemCodeUnique(hrSalaryItem))
        {
            return error("修改薪酬项目'" + hrSalaryItem.getItemName() + "'失败，项目编码已存在");
        }
        hrSalaryItem.setUpdateBy(getUsername());
        return toAjax(hrSalaryItemService.updateHrSalaryItem(hrSalaryItem));
    }

    /**
     * 删除薪酬项目
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryItem:remove')")
    @Log(title = "薪酬项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds)
    {
        return toAjax(hrSalaryItemService.deleteHrSalaryItemByItemIds(itemIds));
    }
}
