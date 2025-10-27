package com.ruoyi.application.controller;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.application.domain.AppLeaveApplication;
import com.ruoyi.application.service.IAppLeaveApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 请假申请Controller
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
@RestController
@RequestMapping("/application/application")
public class AppLeaveApplicationController extends BaseController
{
    @Autowired
    private IAppLeaveApplicationService appLeaveApplicationService;

    /**
     * 查询请假申请列表
     */
    @PreAuthorize("@ss.hasPermi('application:application:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppLeaveApplication appLeaveApplication)
    {
        startPage();
        List<AppLeaveApplication> list = appLeaveApplicationService.selectAppLeaveApplicationList(appLeaveApplication);
        return getDataTable(list);
    }

    /**
     * 导出请假申请列表
     */
    @PreAuthorize("@ss.hasPermi('application:application:export')")
    @Log(title = "请假申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppLeaveApplication appLeaveApplication)
    {
        List<AppLeaveApplication> list = appLeaveApplicationService.selectAppLeaveApplicationList(appLeaveApplication);
        ExcelUtil<AppLeaveApplication> util = new ExcelUtil<AppLeaveApplication>(AppLeaveApplication.class);
        util.exportExcel(response, list, "请假申请数据");
    }

    /**
     * 获取请假申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('application:application:query')")
    @GetMapping(value = "/{leaveId}")
    public AjaxResult getInfo(@PathVariable("leaveId") Long leaveId)
    {
        return success(appLeaveApplicationService.selectAppLeaveApplicationByLeaveId(leaveId));
    }

    /**
     * 新增请假申请
     */
    @PreAuthorize("@ss.hasPermi('application:application:add')")
    @Log(title = "请假申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppLeaveApplication appLeaveApplication)
    {
        return toAjax(appLeaveApplicationService.insertAppLeaveApplication(appLeaveApplication));
    }

    /**
     * 修改请假申请
     */
    @PreAuthorize("@ss.hasPermi('application:application:edit')")
    @Log(title = "请假申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppLeaveApplication appLeaveApplication)
    {
        return toAjax(appLeaveApplicationService.updateAppLeaveApplication(appLeaveApplication));
    }

    /**
     * 删除请假申请
     */
    @PreAuthorize("@ss.hasPermi('application:application:remove')")
    @Log(title = "请假申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{leaveIds}")
    public AjaxResult remove(@PathVariable Long[] leaveIds)
    {
        return toAjax(appLeaveApplicationService.deleteAppLeaveApplicationByLeaveIds(leaveIds));
    }
}
