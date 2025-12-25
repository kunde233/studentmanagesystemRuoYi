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
import com.studentMNGSS.tooltable.domain.HrTransfer;
import com.studentMNGSS.tooltable.service.IHrTransferService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 人员调动Controller
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@RestController
@RequestMapping("/hr/transfer")
public class HrTransferController extends BaseController
{
    @Autowired
    private IHrTransferService hrTransferService;

    /**
     * 查询人员调动列表
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrTransfer hrTransfer)
    {
        startPage();
        List<HrTransfer> list = hrTransferService.selectHrTransferList(hrTransfer);
        return getDataTable(list);
    }

    /**
     * 导出人员调动列表
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:export')")
    @Log(title = "人员调动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrTransfer hrTransfer)
    {
        List<HrTransfer> list = hrTransferService.selectHrTransferList(hrTransfer);
        ExcelUtil<HrTransfer> util = new ExcelUtil<HrTransfer>(HrTransfer.class);
        util.exportExcel(response, list, "人员调动数据");
    }

    /**
     * 获取人员调动详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:query')")
    @GetMapping(value = "/{transferId}")
    public AjaxResult getInfo(@PathVariable("transferId") Long transferId)
    {
        return success(hrTransferService.selectHrTransferByTransferId(transferId));
    }

    /**
     * 新增人员调动
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:add')")
    @Log(title = "人员调动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrTransfer hrTransfer)
    {
        return toAjax(hrTransferService.insertHrTransfer(hrTransfer, getUsername()));
    }

    /**
     * 修改人员调动
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:edit')")
    @Log(title = "人员调动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrTransfer hrTransfer)
    {
        hrTransfer.setUpdateBy(getUsername());
        return toAjax(hrTransferService.updateHrTransfer(hrTransfer));
    }

    /**
     * 复核人员调动
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:review')")
    @Log(title = "人员调动复核", businessType = BusinessType.UPDATE)
    @PutMapping("/review")
    public AjaxResult review(@RequestBody HrTransfer hrTransfer)
    {
        return toAjax(hrTransferService.reviewHrTransfer(hrTransfer, getUsername()));
    }

    /**
     * 删除人员调动
     */
    @PreAuthorize("@ss.hasPermi('hr:transfer:remove')")
    @Log(title = "人员调动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{transferIds}")
    public AjaxResult remove(@PathVariable Long[] transferIds)
    {
        return toAjax(hrTransferService.deleteHrTransferByTransferIds(transferIds));
    }
}
