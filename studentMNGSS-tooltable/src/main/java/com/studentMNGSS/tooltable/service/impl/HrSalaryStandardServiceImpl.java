package com.studentMNGSS.tooltable.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.studentMNGSS.tooltable.mapper.HrSalaryStandardMapper;
import com.studentMNGSS.tooltable.domain.HrSalaryStandard;
import com.studentMNGSS.tooltable.domain.HrSalaryStandardDetail;
import com.studentMNGSS.tooltable.service.IHrSalaryStandardService;

/**
 * 薪酬标准Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@Service
public class HrSalaryStandardServiceImpl implements IHrSalaryStandardService
{
    @Autowired
    private HrSalaryStandardMapper hrSalaryStandardMapper;

    /**
     * 查询薪酬标准
     *
     * @param standardId 薪酬标准主键
     * @return 薪酬标准
     */
    @Override
    public HrSalaryStandard selectHrSalaryStandardByStandardId(Long standardId)
    {
        HrSalaryStandard standard = hrSalaryStandardMapper.selectHrSalaryStandardByStandardId(standardId);
        if (standard != null)
        {
            standard.setDetails(hrSalaryStandardMapper.selectHrSalaryStandardDetailByStandardId(standardId));
        }
        return standard;
    }

    /**
     * 查询薪酬标准列表
     *
     * @param hrSalaryStandard 薪酬标准
     * @return 薪酬标准
     */
    @Override
    public List<HrSalaryStandard> selectHrSalaryStandardList(HrSalaryStandard hrSalaryStandard)
    {
        return hrSalaryStandardMapper.selectHrSalaryStandardList(hrSalaryStandard);
    }

    /**
     * 查询所有启用的薪酬标准
     *
     * @return 薪酬标准集合
     */
    @Override
    public List<HrSalaryStandard> selectHrSalaryStandardAll()
    {
        return hrSalaryStandardMapper.selectHrSalaryStandardAll();
    }

    /**
     * 新增薪酬标准
     *
     * @param hrSalaryStandard 薪酬标准
     * @return 结果
     */
    @Override
    @Transactional
    public int insertHrSalaryStandard(HrSalaryStandard hrSalaryStandard)
    {
        // 生成编号
        hrSalaryStandard.setStandardCode(generateStandardCode());

        // 计算总额
        BigDecimal totalAmount = calculateTotalAmount(hrSalaryStandard.getDetails());
        hrSalaryStandard.setTotalAmount(totalAmount);

        hrSalaryStandard.setCreateTime(DateUtils.getNowDate());

        int rows = hrSalaryStandardMapper.insertHrSalaryStandard(hrSalaryStandard);

        // 插入明细
        if (hrSalaryStandard.getDetails() != null && !hrSalaryStandard.getDetails().isEmpty())
        {
            for (HrSalaryStandardDetail detail : hrSalaryStandard.getDetails())
            {
                detail.setStandardId(hrSalaryStandard.getStandardId());
                hrSalaryStandardMapper.insertHrSalaryStandardDetail(detail);
            }
        }

        return rows;
    }

    /**
     * 修改薪酬标准
     *
     * @param hrSalaryStandard 薪酬标准
     * @return 结果
     */
    @Override
    @Transactional
    public int updateHrSalaryStandard(HrSalaryStandard hrSalaryStandard)
    {
        // 计算总额
        BigDecimal totalAmount = calculateTotalAmount(hrSalaryStandard.getDetails());
        hrSalaryStandard.setTotalAmount(totalAmount);

        hrSalaryStandard.setUpdateTime(DateUtils.getNowDate());

        // 删除原有明细
        hrSalaryStandardMapper.deleteHrSalaryStandardDetailByStandardId(hrSalaryStandard.getStandardId());

        // 插入新明细
        if (hrSalaryStandard.getDetails() != null && !hrSalaryStandard.getDetails().isEmpty())
        {
            for (HrSalaryStandardDetail detail : hrSalaryStandard.getDetails())
            {
                detail.setStandardId(hrSalaryStandard.getStandardId());
                hrSalaryStandardMapper.insertHrSalaryStandardDetail(detail);
            }
        }

        return hrSalaryStandardMapper.updateHrSalaryStandard(hrSalaryStandard);
    }

    /**
     * 复核薪酬标准
     *
     * @param hrSalaryStandard 薪酬标准
     * @param username 复核人
     * @return 结果
     */
    @Override
    public int reviewHrSalaryStandard(HrSalaryStandard hrSalaryStandard, String username)
    {
        HrSalaryStandard standard = new HrSalaryStandard();
        standard.setStandardId(hrSalaryStandard.getStandardId());
        standard.setStatus("1"); // 已复核
        standard.setReviewer(username);
        standard.setReviewTime(DateUtils.getNowDate());
        standard.setUpdateBy(username);
        standard.setUpdateTime(DateUtils.getNowDate());
        return hrSalaryStandardMapper.updateHrSalaryStandard(standard);
    }

    /**
     * 批量删除薪酬标准
     *
     * @param standardIds 需要删除的薪酬标准主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHrSalaryStandardByStandardIds(Long[] standardIds)
    {
        for (Long standardId : standardIds)
        {
            hrSalaryStandardMapper.deleteHrSalaryStandardDetailByStandardId(standardId);
        }
        return hrSalaryStandardMapper.deleteHrSalaryStandardByStandardIds(standardIds);
    }

    /**
     * 删除薪酬标准信息
     *
     * @param standardId 薪酬标准主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteHrSalaryStandardByStandardId(Long standardId)
    {
        hrSalaryStandardMapper.deleteHrSalaryStandardDetailByStandardId(standardId);
        return hrSalaryStandardMapper.deleteHrSalaryStandardByStandardId(standardId);
    }

    /**
     * 生成薪酬标准编号
     *
     * @return 编号
     */
    @Override
    public String generateStandardCode()
    {
        String maxCode = hrSalaryStandardMapper.selectMaxStandardCode();
        int serialNumber = 1;
        if (StringUtils.isNotEmpty(maxCode))
        {
            try {
                serialNumber = Integer.parseInt(maxCode.replace("SS", "")) + 1;
            } catch (NumberFormatException e) {
                serialNumber = 1;
            }
        }
        return String.format("SS%04d", serialNumber);
    }

    /**
     * 计算薪酬总额
     *
     * @param details 明细列表
     * @return 总额
     */
    private BigDecimal calculateTotalAmount(List<HrSalaryStandardDetail> details)
    {
        BigDecimal total = BigDecimal.ZERO;
        if (details != null)
        {
            for (HrSalaryStandardDetail detail : details)
            {
                if (detail.getAmount() != null)
                {
                    // 收入类型加，扣除类型减
                    if ("0".equals(detail.getItemType()))
                    {
                        total = total.add(detail.getAmount());
                    }
                    else
                    {
                        total = total.subtract(detail.getAmount());
                    }
                }
            }
        }
        return total;
    }
}
