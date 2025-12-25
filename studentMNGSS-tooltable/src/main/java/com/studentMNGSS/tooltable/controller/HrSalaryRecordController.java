package com.studentMNGSS.tooltable.controller;

import java.util.List;
import java.util.Map;
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
import com.studentMNGSS.tooltable.domain.HrSalaryRecord;
import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.service.IHrSalaryRecordService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 薪酬发放记录Controller
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@RestController
@RequestMapping("/hr/salaryRecord")
public class HrSalaryRecordController extends BaseController
{
    @Autowired
    private IHrSalaryRecordService hrSalaryRecordService;

    /**
     * 查询薪酬发放记录列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrSalaryRecord hrSalaryRecord)
    {
        startPage();
        List<HrSalaryRecord> list = hrSalaryRecordService.selectHrSalaryRecordList(hrSalaryRecord);
        return getDataTable(list);
    }

    /**
     * 查询待复核的薪酬发放记录列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:review')")
    @GetMapping("/pendingReview")
    public TableDataInfo pendingReviewList(HrSalaryRecord hrSalaryRecord)
    {
        startPage();
        List<HrSalaryRecord> list = hrSalaryRecordService.selectHrSalaryRecordPendingReviewList(hrSalaryRecord);
        return getDataTable(list);
    }

    /**
     * 导出薪酬发放记录列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:export')")
    @Log(title = "薪酬发放记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrSalaryRecord hrSalaryRecord)
    {
        List<HrSalaryRecord> list = hrSalaryRecordService.selectHrSalaryRecordList(hrSalaryRecord);
        ExcelUtil<HrSalaryRecord> util = new ExcelUtil<HrSalaryRecord>(HrSalaryRecord.class);
        util.exportExcel(response, list, "薪酬发放记录数据");
    }

    /**
     * 获取薪酬发放记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(hrSalaryRecordService.selectHrSalaryRecordByRecordId(recordId));
    }

    /**
     * 新增薪酬发放记录
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:add')")
    @Log(title = "薪酬发放记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrSalaryRecord hrSalaryRecord)
    {
        if (hrSalaryRecordService.checkEmployeeMonthRecord(hrSalaryRecord.getEmployeeId(), hrSalaryRecord.getSalaryMonth()))
        {
            return error("该员工本月已有发放记录，请勿重复添加");
        }
        hrSalaryRecord.setCreateBy(getUsername());
        return toAjax(hrSalaryRecordService.insertHrSalaryRecord(hrSalaryRecord));
    }

    /**
     * 修改薪酬发放记录
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:edit')")
    @Log(title = "薪酬发放记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrSalaryRecord hrSalaryRecord)
    {
        hrSalaryRecord.setUpdateBy(getUsername());
        return toAjax(hrSalaryRecordService.updateHrSalaryRecord(hrSalaryRecord));
    }

    /**
     * 复核薪酬发放记录
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:review')")
    @Log(title = "薪酬发放复核", businessType = BusinessType.UPDATE)
    @PutMapping("/review")
    public AjaxResult review(@RequestBody HrSalaryRecord hrSalaryRecord)
    {
        return toAjax(hrSalaryRecordService.reviewHrSalaryRecord(hrSalaryRecord, getUsername()));
    }

    /**
     * 删除薪酬发放记录
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:remove')")
    @Log(title = "薪酬发放记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(hrSalaryRecordService.deleteHrSalaryRecordByRecordIds(recordIds));
    }

    /**
     * 查询有薪酬标准的员工列表（用于批量发放，排除当月已登记的员工）
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:add')")
    @GetMapping("/employeeSalary")
    public AjaxResult listEmployeeSalary(HrEmployee hrEmployee, String salaryMonth)
    {
        List<Map<String, Object>> list = hrSalaryRecordService.selectEmployeeWithSalaryInfo(hrEmployee, salaryMonth);
        return success(list);
    }

    /**
     * 批量登记薪酬发放记录
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryRecord:add')")
    @Log(title = "薪酬发放批量登记", businessType = BusinessType.INSERT)
    @PostMapping("/batch")
    public AjaxResult batchAdd(@RequestBody Map<String, List<HrSalaryRecord>> requestBody)
    {
        List<HrSalaryRecord> records = requestBody.get("records");
        if (records == null || records.isEmpty())
        {
            return error("没有可提交的记录");
        }
        int count = hrSalaryRecordService.batchInsertHrSalaryRecords(records, getUsername());
        return success("成功登记 " + count + " 条记录");
    }
}
