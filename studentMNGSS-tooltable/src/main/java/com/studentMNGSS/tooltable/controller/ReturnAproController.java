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
import com.studentMNGSS.tooltable.domain.ReturnApro;
import com.studentMNGSS.tooltable.service.IReturnAproService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 回校审批Controller
 * 
 * @author studentMNGSS
 * @date 2025-11-03
 */
@RestController
@RequestMapping("/tooltable/returnschoolApro")
public class ReturnAproController extends BaseController
{
    @Autowired
    private IReturnAproService returnAproService;

    /**
     * 查询回校审批列表
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschoolApro:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReturnApro returnApro)
    {
        startPage();
        List<ReturnApro> list = returnAproService.selectReturnAproList(returnApro);
        return getDataTable(list);
    }

    /**
     * 导出回校审批列表
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschoolApro:export')")
    @Log(title = "回校审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReturnApro returnApro)
    {
        List<ReturnApro> list = returnAproService.selectReturnAproList(returnApro);
        ExcelUtil<ReturnApro> util = new ExcelUtil<ReturnApro>(ReturnApro.class);
        util.exportExcel(response, list, "回校审批数据");
    }

    /**
     * 获取回校审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschoolApro:query')")
    @GetMapping(value = "/{applicationId}")
    public AjaxResult getInfo(@PathVariable("applicationId") Long applicationId)
    {
        return success(returnAproService.selectReturnAproByApplicationId(applicationId));
    }

    /**
     * 新增回校审批
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschoolApro:add')")
    @Log(title = "回校审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReturnApro returnApro)
    {
        return toAjax(returnAproService.insertReturnApro(returnApro));
    }

    /**
     * 修改回校审批
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschoolApro:edit')")
    @Log(title = "回校审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReturnApro returnApro)
    {
        return toAjax(returnAproService.updateReturnApro(returnApro));
    }

    /**
     * 删除回校审批
     */
    @PreAuthorize("@ss.hasPermi('tooltable:returnschoolApro:remove')")
    @Log(title = "回校审批", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applicationIds}")
    public AjaxResult remove(@PathVariable Long[] applicationIds)
    {
        return toAjax(returnAproService.deleteReturnAproByApplicationIds(applicationIds));
    }
}
