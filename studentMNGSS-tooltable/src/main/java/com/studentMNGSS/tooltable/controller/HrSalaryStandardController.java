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
import com.studentMNGSS.tooltable.domain.HrSalaryStandard;
import com.studentMNGSS.tooltable.service.IHrSalaryStandardService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 薪酬标准Controller
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@RestController
@RequestMapping("/hr/salaryStandard")
public class HrSalaryStandardController extends BaseController
{
    @Autowired
    private IHrSalaryStandardService hrSalaryStandardService;

    /**
     * 查询薪酬标准列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrSalaryStandard hrSalaryStandard)
    {
        startPage();
        List<HrSalaryStandard> list = hrSalaryStandardService.selectHrSalaryStandardList(hrSalaryStandard);
        return getDataTable(list);
    }

    /**
     * 查询所有启用的薪酬标准（用于下拉选择）
     */
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<HrSalaryStandard> list = hrSalaryStandardService.selectHrSalaryStandardAll();
        return success(list);
    }

    /**
     * 查询待复核的薪酬标准列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:review')")
    @GetMapping("/pendingReview")
    public TableDataInfo pendingReviewList(HrSalaryStandard hrSalaryStandard)
    {
        startPage();
        hrSalaryStandard.setStatus("0"); // 待复核状态
        List<HrSalaryStandard> list = hrSalaryStandardService.selectHrSalaryStandardList(hrSalaryStandard);
        return getDataTable(list);
    }

    /**
     * 复核薪酬标准
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:review')")
    @Log(title = "薪酬标准复核", businessType = BusinessType.UPDATE)
    @PutMapping("/review")
    public AjaxResult review(@RequestBody HrSalaryStandard hrSalaryStandard)
    {
        return toAjax(hrSalaryStandardService.reviewHrSalaryStandard(hrSalaryStandard, getUsername()));
    }

    /**
     * 导出薪酬标准列表
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:export')")
    @Log(title = "薪酬标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrSalaryStandard hrSalaryStandard)
    {
        List<HrSalaryStandard> list = hrSalaryStandardService.selectHrSalaryStandardList(hrSalaryStandard);
        ExcelUtil<HrSalaryStandard> util = new ExcelUtil<HrSalaryStandard>(HrSalaryStandard.class);
        util.exportExcel(response, list, "薪酬标准数据");
    }

    /**
     * 获取薪酬标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:query')")
    @GetMapping(value = "/{standardId}")
    public AjaxResult getInfo(@PathVariable("standardId") Long standardId)
    {
        return success(hrSalaryStandardService.selectHrSalaryStandardByStandardId(standardId));
    }

    /**
     * 新增薪酬标准
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:add')")
    @Log(title = "薪酬标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrSalaryStandard hrSalaryStandard)
    {
        hrSalaryStandard.setCreateBy(getUsername());
        return toAjax(hrSalaryStandardService.insertHrSalaryStandard(hrSalaryStandard));
    }

    /**
     * 修改薪酬标准
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:edit')")
    @Log(title = "薪酬标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrSalaryStandard hrSalaryStandard)
    {
        hrSalaryStandard.setUpdateBy(getUsername());
        return toAjax(hrSalaryStandardService.updateHrSalaryStandard(hrSalaryStandard));
    }

    /**
     * 删除薪酬标准
     */
    @PreAuthorize("@ss.hasPermi('hr:salaryStandard:remove')")
    @Log(title = "薪酬标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{standardIds}")
    public AjaxResult remove(@PathVariable Long[] standardIds)
    {
        return toAjax(hrSalaryStandardService.deleteHrSalaryStandardByStandardIds(standardIds));
    }
}
