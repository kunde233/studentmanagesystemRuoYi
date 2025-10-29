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
import com.studentMNGSS.tooltable.domain.LeaveApp;
import com.studentMNGSS.tooltable.service.ILeaveAppService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 请假申请Controller
 * 
 * @author ruoyi
 * @date 2025-10-27
 */
@RestController
@RequestMapping("/leaveApp/leaveApp")
public class LeaveAppController extends BaseController
{
    @Autowired
    private ILeaveAppService leaveAppService;

    /**
     * 查询请假申请列表
     */
    @PreAuthorize("@ss.hasPermi('leaveApp:leaveApp:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaveApp leaveApp)
    {
        startPage();
        List<LeaveApp> list = leaveAppService.selectLeaveAppList(leaveApp);
        return getDataTable(list);
    }

    /**
     * 导出请假申请列表
     */
    @PreAuthorize("@ss.hasPermi('leaveApp:leaveApp:export')")
    @Log(title = "请假申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LeaveApp leaveApp)
    {
        List<LeaveApp> list = leaveAppService.selectLeaveAppList(leaveApp);
        ExcelUtil<LeaveApp> util = new ExcelUtil<LeaveApp>(LeaveApp.class);
        util.exportExcel(response, list, "请假申请数据");
    }

    /**
     * 获取请假申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('leaveApp:leaveApp:query')")
    @GetMapping(value = "/{applicationId}")
    public AjaxResult getInfo(@PathVariable("applicationId") Long applicationId)
    {
        return success(leaveAppService.selectLeaveAppByApplicationId(applicationId));
    }

    /**
     * 新增请假申请
     */
    @PreAuthorize("@ss.hasPermi('leaveApp:leaveApp:add')")
    @Log(title = "请假申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaveApp leaveApp)
    {
        return toAjax(leaveAppService.insertLeaveApp(leaveApp));
    }

    /**
     * 修改请假申请
     */
    @PreAuthorize("@ss.hasPermi('leaveApp:leaveApp:edit')")
    @Log(title = "请假申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaveApp leaveApp)
    {
        return toAjax(leaveAppService.updateLeaveApp(leaveApp));
    }

    /**
     * 删除请假申请
     */
    @PreAuthorize("@ss.hasPermi('leaveApp:leaveApp:remove')")
    @Log(title = "请假申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applicationIds}")
    public AjaxResult remove(@PathVariable Long[] applicationIds)
    {
        return toAjax(leaveAppService.deleteLeaveAppByApplicationIds(applicationIds));
    }
}
