package com.studentMNGSS.tooltable.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.tooltable.domain.LeaveApro;
import com.studentMNGSS.tooltable.service.LleaveAproService;
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
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 请假审批Controller
 * 
 * @author ruoyi
 * @date 2025-10-29
 */
@RestController
@RequestMapping("/tooltable/leaveApro")
public class LeaveAproController extends BaseController
{
    @Autowired
    private LleaveAproService leaveAproService;

    /**
     * 查询请假审批列表
     */
    @PreAuthorize("@ss.hasPermi('tooltable:leaveApro:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaveApro leaveApro)
    {
        startPage();
        List<LeaveApro> list = leaveAproService.selectleaveAproList(leaveApro);
        return getDataTable(list);
    }

    /**
     * 导出请假审批列表
     */
    @PreAuthorize("@ss.hasPermi('tooltable:leaveApro:export')")
    @Log(title = "请假审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LeaveApro leaveApro)
    {
        List<LeaveApro> list = leaveAproService.selectleaveAproList(leaveApro);
        ExcelUtil<LeaveApro> util = new ExcelUtil<LeaveApro>(LeaveApro.class);
        util.exportExcel(response, list, "请假审批数据");
    }

    /**
     * 获取请假审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('tooltable:leaveApro:query')")
    @GetMapping(value = "/{applicationId}")
    public AjaxResult getInfo(@PathVariable("applicationId") Long applicationId)
    {
        return success(leaveAproService.selectleaveAproByApplicationId(applicationId));
    }

    /**
     * 新增请假审批
     */
    @PreAuthorize("@ss.hasPermi('tooltable:leaveApro:add')")
    @Log(title = "请假审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaveApro leaveApro)
    {
        return toAjax(leaveAproService.insertleaveApro(leaveApro));
    }

    /**
     * 修改请假审批
     */
    @PreAuthorize("@ss.hasPermi('tooltable:leaveApro:edit')")
    @Log(title = "请假审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaveApro leaveApro)
    {
        return toAjax(leaveAproService.updateleaveApro(leaveApro));
    }

    /**
     * 删除请假审批
     */
    @PreAuthorize("@ss.hasPermi('tooltable:leaveApro:remove')")
    @Log(title = "请假审批", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applicationIds}")
    public AjaxResult remove(@PathVariable Long[] applicationIds)
    {
        return toAjax(leaveAproService.deleteleaveAproByApplicationIds(applicationIds));
    }
}
