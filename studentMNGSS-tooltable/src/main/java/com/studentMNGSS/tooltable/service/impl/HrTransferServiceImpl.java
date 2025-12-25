package com.studentMNGSS.tooltable.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.studentMNGSS.common.utils.DateUtils;
import com.studentMNGSS.tooltable.mapper.HrTransferMapper;
import com.studentMNGSS.tooltable.mapper.HrEmployeeMapper;
import com.studentMNGSS.tooltable.domain.HrTransfer;
import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.service.IHrTransferService;

/**
 * 人员调动Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-25
 */
@Service
public class HrTransferServiceImpl implements IHrTransferService
{
    @Autowired
    private HrTransferMapper hrTransferMapper;

    @Autowired
    private HrEmployeeMapper hrEmployeeMapper;

    /**
     * 查询人员调动
     *
     * @param transferId 人员调动主键
     * @return 人员调动
     */
    @Override
    public HrTransfer selectHrTransferByTransferId(Long transferId)
    {
        return hrTransferMapper.selectHrTransferByTransferId(transferId);
    }

    /**
     * 查询人员调动列表
     *
     * @param hrTransfer 人员调动
     * @return 人员调动
     */
    @Override
    public List<HrTransfer> selectHrTransferList(HrTransfer hrTransfer)
    {
        return hrTransferMapper.selectHrTransferList(hrTransfer);
    }

    /**
     * 新增人员调动
     *
     * @param hrTransfer 人员调动
     * @param username 创建人
     * @return 结果
     */
    @Override
    public int insertHrTransfer(HrTransfer hrTransfer, String username)
    {
        // 生成调动编号
        hrTransfer.setTransferCode(generateTransferCode());
        // 判断调动类型
        hrTransfer.setTransferType(determineTransferType(hrTransfer));
        hrTransfer.setStatus("0"); // 待复核
        hrTransfer.setCreateBy(username);
        hrTransfer.setCreateTime(DateUtils.getNowDate());
        return hrTransferMapper.insertHrTransfer(hrTransfer);
    }

    /**
     * 修改人员调动
     *
     * @param hrTransfer 人员调动
     * @return 结果
     */
    @Override
    public int updateHrTransfer(HrTransfer hrTransfer)
    {
        // 重新判断调动类型
        hrTransfer.setTransferType(determineTransferType(hrTransfer));
        hrTransfer.setUpdateTime(DateUtils.getNowDate());
        return hrTransferMapper.updateHrTransfer(hrTransfer);
    }

    /**
     * 复核人员调动
     *
     * @param hrTransfer 人员调动
     * @param username 复核人
     * @return 结果
     */
    @Override
    @Transactional
    public int reviewHrTransfer(HrTransfer hrTransfer, String username)
    {
        // 获取完整调动信息
        HrTransfer transfer = hrTransferMapper.selectHrTransferByTransferId(hrTransfer.getTransferId());
        if (transfer == null || !"0".equals(transfer.getStatus()))
        {
            return 0;
        }

        // 更新调动状态
        transfer.setStatus("1"); // 已复核
        transfer.setReviewer(username);
        transfer.setReviewTime(DateUtils.getNowDate());
        int result = hrTransferMapper.updateHrTransfer(transfer);

        // 更新员工的机构和职位信息
        if (result > 0)
        {
            HrEmployee employee = new HrEmployee();
            employee.setEmployeeId(transfer.getEmployeeId());
            employee.setDeptIdFirst(transfer.getNewDeptIdFirst());
            employee.setDeptIdSecond(transfer.getNewDeptIdSecond());
            employee.setDeptIdThird(transfer.getNewDeptIdThird());
            employee.setPositionId(transfer.getNewPositionId());
            employee.setUpdateBy(username);
            employee.setUpdateTime(DateUtils.getNowDate());
            hrEmployeeMapper.updateHrEmployee(employee);
        }

        return result;
    }

    /**
     * 批量删除人员调动
     *
     * @param transferIds 需要删除的人员调动主键
     * @return 结果
     */
    @Override
    public int deleteHrTransferByTransferIds(Long[] transferIds)
    {
        return hrTransferMapper.deleteHrTransferByTransferIds(transferIds);
    }

    /**
     * 删除人员调动信息
     *
     * @param transferId 人员调动主键
     * @return 结果
     */
    @Override
    public int deleteHrTransferByTransferId(Long transferId)
    {
        return hrTransferMapper.deleteHrTransferByTransferId(transferId);
    }

    /**
     * 生成调动编号
     * 格式：DD + 日期(yyyyMMdd) + 4位流水号
     */
    private String generateTransferCode()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        int count = hrTransferMapper.selectTodayTransferCount();
        return String.format("DD%s%04d", dateStr, count + 1);
    }

    /**
     * 判断调动类型
     * 0-机构调动 1-职位调动 2-机构和职位调动
     */
    private String determineTransferType(HrTransfer transfer)
    {
        boolean deptChanged = !transfer.getNewDeptIdThird().equals(transfer.getOldDeptId());
        boolean positionChanged = !transfer.getNewPositionId().equals(transfer.getOldPositionId());

        if (deptChanged && positionChanged)
        {
            return "2"; // 机构和职位都调动
        }
        else if (deptChanged)
        {
            return "0"; // 仅机构调动
        }
        else if (positionChanged)
        {
            return "1"; // 仅职位调动
        }
        return "2"; // 默认
    }
}
