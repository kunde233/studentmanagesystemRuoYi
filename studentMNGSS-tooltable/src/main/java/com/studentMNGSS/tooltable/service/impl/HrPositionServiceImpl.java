package com.studentMNGSS.tooltable.service.impl;

import java.util.List;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentMNGSS.tooltable.mapper.HrPositionMapper;
import com.studentMNGSS.tooltable.domain.HrPosition;
import com.studentMNGSS.tooltable.service.IHrPositionService;

/**
 * 职位Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@Service
public class HrPositionServiceImpl implements IHrPositionService
{
    @Autowired
    private HrPositionMapper hrPositionMapper;

    /**
     * 查询职位
     *
     * @param positionId 职位主键
     * @return 职位
     */
    @Override
    public HrPosition selectHrPositionByPositionId(Long positionId)
    {
        return hrPositionMapper.selectHrPositionByPositionId(positionId);
    }

    /**
     * 查询职位列表
     *
     * @param hrPosition 职位
     * @return 职位
     */
    @Override
    public List<HrPosition> selectHrPositionList(HrPosition hrPosition)
    {
        return hrPositionMapper.selectHrPositionList(hrPosition);
    }

    /**
     * 根据部门ID查询职位列表
     *
     * @param deptId 部门ID
     * @return 职位集合
     */
    @Override
    public List<HrPosition> selectHrPositionByDeptId(Long deptId)
    {
        return hrPositionMapper.selectHrPositionByDeptId(deptId);
    }

    /**
     * 查询所有职位列表
     *
     * @return 职位集合
     */
    @Override
    public List<HrPosition> selectHrPositionAll()
    {
        return hrPositionMapper.selectHrPositionAll();
    }

    /**
     * 新增职位
     *
     * @param hrPosition 职位
     * @return 结果
     */
    @Override
    public int insertHrPosition(HrPosition hrPosition)
    {
        hrPosition.setCreateTime(DateUtils.getNowDate());
        return hrPositionMapper.insertHrPosition(hrPosition);
    }

    /**
     * 修改职位
     *
     * @param hrPosition 职位
     * @return 结果
     */
    @Override
    public int updateHrPosition(HrPosition hrPosition)
    {
        hrPosition.setUpdateTime(DateUtils.getNowDate());
        return hrPositionMapper.updateHrPosition(hrPosition);
    }

    /**
     * 批量删除职位
     *
     * @param positionIds 需要删除的职位主键
     * @return 结果
     */
    @Override
    public int deleteHrPositionByPositionIds(Long[] positionIds)
    {
        return hrPositionMapper.deleteHrPositionByPositionIds(positionIds);
    }

    /**
     * 删除职位信息
     *
     * @param positionId 职位主键
     * @return 结果
     */
    @Override
    public int deleteHrPositionByPositionId(Long positionId)
    {
        return hrPositionMapper.deleteHrPositionByPositionId(positionId);
    }

    /**
     * 校验职位编码是否唯一
     *
     * @param hrPosition 职位
     * @return 结果
     */
    @Override
    public boolean checkPositionCodeUnique(HrPosition hrPosition)
    {
        Long positionId = StringUtils.isNull(hrPosition.getPositionId()) ? -1L : hrPosition.getPositionId();
        HrPosition info = hrPositionMapper.checkPositionCodeUnique(hrPosition.getPositionCode());
        if (StringUtils.isNotNull(info) && info.getPositionId().longValue() != positionId.longValue())
        {
            return false;
        }
        return true;
    }

    /**
     * 校验职位名称是否唯一
     *
     * @param hrPosition 职位
     * @return 结果
     */
    @Override
    public boolean checkPositionNameUnique(HrPosition hrPosition)
    {
        Long positionId = StringUtils.isNull(hrPosition.getPositionId()) ? -1L : hrPosition.getPositionId();
        HrPosition info = hrPositionMapper.checkPositionNameUnique(hrPosition);
        if (StringUtils.isNotNull(info) && info.getPositionId().longValue() != positionId.longValue())
        {
            return false;
        }
        return true;
    }
}
