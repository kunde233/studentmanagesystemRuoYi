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
import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.service.IHrEmployeeService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 员工档案Controller
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@RestController
@RequestMapping("/hr/employee")
public class HrEmployeeController extends BaseController
{
    @Autowired
    private IHrEmployeeService hrEmployeeService;

    /**
     * 查询员工档案列表
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrEmployee hrEmployee)
    {
        startPage();
        List<HrEmployee> list = hrEmployeeService.selectHrEmployeeList(hrEmployee);
        return getDataTable(list);
    }

    /**
     * 查询待复核的员工档案列表
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:review')")
    @GetMapping("/pendingReview")
    public TableDataInfo pendingReviewList(HrEmployee hrEmployee)
    {
        startPage();
        List<HrEmployee> list = hrEmployeeService.selectHrEmployeePendingReviewList(hrEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工档案列表
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:export')")
    @Log(title = "员工档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrEmployee hrEmployee)
    {
        List<HrEmployee> list = hrEmployeeService.selectHrEmployeeList(hrEmployee);
        ExcelUtil<HrEmployee> util = new ExcelUtil<HrEmployee>(HrEmployee.class);
        util.exportExcel(response, list, "员工档案数据");
    }

    /**
     * 获取员工档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:query')")
    @GetMapping(value = "/{employeeId}")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        return success(hrEmployeeService.selectHrEmployeeByEmployeeId(employeeId));
    }

    /**
     * 新增员工档案（登记）
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:add')")
    @Log(title = "员工档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrEmployee hrEmployee)
    {
        if (!hrEmployeeService.checkIdCardUnique(hrEmployee))
        {
            return error("新增员工'" + hrEmployee.getEmployeeName() + "'失败，身份证号已存在");
        }
        return toAjax(hrEmployeeService.insertHrEmployee(hrEmployee, getUsername()));
    }

    /**
     * 修改员工档案
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:edit')")
    @Log(title = "员工档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrEmployee hrEmployee)
    {
        if (!hrEmployeeService.checkIdCardUnique(hrEmployee))
        {
            return error("修改员工'" + hrEmployee.getEmployeeName() + "'失败，身份证号已存在");
        }
        hrEmployee.setUpdateBy(getUsername());
        return toAjax(hrEmployeeService.updateHrEmployee(hrEmployee));
    }

    /**
     * 复核员工档案
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:review')")
    @Log(title = "员工档案复核", businessType = BusinessType.UPDATE)
    @PutMapping("/review")
    public AjaxResult review(@RequestBody HrEmployee hrEmployee)
    {
        return toAjax(hrEmployeeService.reviewHrEmployee(hrEmployee, getUsername()));
    }

    /**
     * 删除员工档案（软删除，设置状态为已删除）
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:remove')")
    @Log(title = "员工档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(hrEmployeeService.deleteHrEmployeeByEmployeeIds(employeeIds));
    }

    /**
     * 恢复已删除的员工档案
     */
    @PreAuthorize("@ss.hasPermi('hr:employee:edit')")
    @Log(title = "员工档案恢复", businessType = BusinessType.UPDATE)
    @PutMapping("/restore/{employeeId}")
    public AjaxResult restore(@PathVariable Long employeeId)
    {
        return toAjax(hrEmployeeService.restoreHrEmployee(employeeId));
    }
}
