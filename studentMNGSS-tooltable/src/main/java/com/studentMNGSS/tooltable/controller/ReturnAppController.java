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
import com.studentMNGSS.tooltable.domain.ReturnApp;
import com.studentMNGSS.tooltable.service.IReturnAppService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 回校申请Controller
 * 
 * @author studentMNGSS
 * @date 2025-10-29
 */
@RestController
@RequestMapping("/tooltable/returnschool")
public class ReturnAppController extends BaseController
{
    @Autowired
    private IReturnAppService returnAppService;

    /**
     * 查询回校申请列表
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschool:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReturnApp returnApp)
    {
        startPage();
        List<ReturnApp> list = returnAppService.selectReturnAppList(returnApp);
        return getDataTable(list);
    }

    /**
     * 导出回校申请列表
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschool:export')")
    @Log(title = "回校申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReturnApp returnApp)
    {
        List<ReturnApp> list = returnAppService.selectReturnAppList(returnApp);
        ExcelUtil<ReturnApp> util = new ExcelUtil<ReturnApp>(ReturnApp.class);
        util.exportExcel(response, list, "回校申请数据");
    }

    /**
     * 获取回校申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschool:query')")
    @GetMapping(value = "/{applicationId}")
    public AjaxResult getInfo(@PathVariable("applicationId") Long applicationId)
    {
        return success(returnAppService.selectReturnAppByApplicationId(applicationId));
    }

    /**
     * 新增回校申请
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschool:add')")
    @Log(title = "回校申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReturnApp returnApp)
    {
        return toAjax(returnAppService.insertReturnApp(returnApp));
    }

    /**
     * 修改回校申请
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschool:edit')")
    @Log(title = "回校申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReturnApp returnApp)
    {
        return toAjax(returnAppService.updateReturnApp(returnApp));
    }

    /**
     * 删除回校申请
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschool:remove')")
    @Log(title = "回校申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applicationIds}")
    public AjaxResult remove(@PathVariable Long[] applicationIds)
    {
        return toAjax(returnAppService.deleteReturnAppByApplicationIds(applicationIds));
    }
}
