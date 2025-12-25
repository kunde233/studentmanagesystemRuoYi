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
import com.studentMNGSS.tooltable.domain.HrPosition;
import com.studentMNGSS.tooltable.service.IHrPositionService;
import com.studentMNGSS.common.utils.poi.ExcelUtil;
import com.studentMNGSS.common.core.page.TableDataInfo;

/**
 * 职位管理Controller
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@RestController
@RequestMapping("/hr/position")
public class HrPositionController extends BaseController
{
    @Autowired
    private IHrPositionService hrPositionService;

    /**
     * 查询职位列表
     */
    @PreAuthorize("@ss.hasPermi('hr:position:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrPosition hrPosition)
    {
        startPage();
        List<HrPosition> list = hrPositionService.selectHrPositionList(hrPosition);
        return getDataTable(list);
    }

    /**
     * 查询所有职位列表（不分页）
     */
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<HrPosition> list = hrPositionService.selectHrPositionAll();
        return success(list);
    }

    /**
     * 根据部门ID查询职位列表
     */
    @GetMapping("/listByDept/{deptId}")
    public AjaxResult listByDept(@PathVariable("deptId") Long deptId)
    {
        List<HrPosition> list = hrPositionService.selectHrPositionByDeptId(deptId);
        return success(list);
    }

    /**
     * 导出职位列表
     */
    @PreAuthorize("@ss.hasPermi('hr:position:export')")
    @Log(title = "职位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrPosition hrPosition)
    {
        List<HrPosition> list = hrPositionService.selectHrPositionList(hrPosition);
        ExcelUtil<HrPosition> util = new ExcelUtil<HrPosition>(HrPosition.class);
        util.exportExcel(response, list, "职位数据");
    }

    /**
     * 获取职位详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:position:query')")
    @GetMapping(value = "/{positionId}")
    public AjaxResult getInfo(@PathVariable("positionId") Long positionId)
    {
        return success(hrPositionService.selectHrPositionByPositionId(positionId));
    }

    /**
     * 新增职位
     */
    @PreAuthorize("@ss.hasPermi('hr:position:add')")
    @Log(title = "职位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrPosition hrPosition)
    {
        if (!hrPositionService.checkPositionCodeUnique(hrPosition))
        {
            return error("新增职位'" + hrPosition.getPositionName() + "'失败，职位编码已存在");
        }
        if (!hrPositionService.checkPositionNameUnique(hrPosition))
        {
            return error("新增职位'" + hrPosition.getPositionName() + "'失败，职位名称已存在");
        }
        hrPosition.setCreateBy(getUsername());
        return toAjax(hrPositionService.insertHrPosition(hrPosition));
    }

    /**
     * 修改职位
     */
    @PreAuthorize("@ss.hasPermi('hr:position:edit')")
    @Log(title = "职位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrPosition hrPosition)
    {
        if (!hrPositionService.checkPositionCodeUnique(hrPosition))
        {
            return error("修改职位'" + hrPosition.getPositionName() + "'失败，职位编码已存在");
        }
        if (!hrPositionService.checkPositionNameUnique(hrPosition))
        {
            return error("修改职位'" + hrPosition.getPositionName() + "'失败，职位名称已存在");
        }
        hrPosition.setUpdateBy(getUsername());
        return toAjax(hrPositionService.updateHrPosition(hrPosition));
    }

    /**
     * 删除职位
     */
    @PreAuthorize("@ss.hasPermi('hr:position:remove')")
    @Log(title = "职位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{positionIds}")
    public AjaxResult remove(@PathVariable Long[] positionIds)
    {
        return toAjax(hrPositionService.deleteHrPositionByPositionIds(positionIds));
    }
}
