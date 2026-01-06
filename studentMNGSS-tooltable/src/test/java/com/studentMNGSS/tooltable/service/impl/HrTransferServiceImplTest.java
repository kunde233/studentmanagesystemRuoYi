package com.studentMNGSS.tooltable.service.impl;

import com.studentMNGSS.tooltable.domain.HrTransfer;
import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.mapper.HrTransferMapper;
import com.studentMNGSS.tooltable.mapper.HrEmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * HrTransferServiceImpl 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class HrTransferServiceImplTest {

    @Mock
    private HrTransferMapper hrTransferMapper;

    @Mock
    private HrEmployeeMapper hrEmployeeMapper;

    @InjectMocks
    private HrTransferServiceImpl hrTransferService;

    private HrTransfer testTransfer;

    @BeforeEach
    void setUp() {
        testTransfer = new HrTransfer();
        testTransfer.setTransferId(1L);
        testTransfer.setTransferCode("DD2025010100001");
        testTransfer.setEmployeeId(100L);
        testTransfer.setOldDeptId(1L);
        testTransfer.setOldPositionId(10L);
        testTransfer.setNewDeptIdFirst(2L);
        testTransfer.setNewDeptIdSecond(3L);
        testTransfer.setNewDeptIdThird(4L);
        testTransfer.setNewPositionId(20L);
        testTransfer.setStatus("0");
    }

    @Test
    void testSelectHrTransferByTransferId() {
        when(hrTransferMapper.selectHrTransferByTransferId(1L)).thenReturn(testTransfer);

        HrTransfer result = hrTransferService.selectHrTransferByTransferId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getTransferId());
        assertEquals(100L, result.getEmployeeId());
    }

    @Test
    void testSelectHrTransferByTransferId_NotFound() {
        when(hrTransferMapper.selectHrTransferByTransferId(999L)).thenReturn(null);

        HrTransfer result = hrTransferService.selectHrTransferByTransferId(999L);

        assertNull(result);
    }

    @Test
    void testSelectHrTransferList() {
        List<HrTransfer> transfers = new ArrayList<>();
        transfers.add(testTransfer);

        when(hrTransferMapper.selectHrTransferList(any(HrTransfer.class))).thenReturn(transfers);

        List<HrTransfer> result = hrTransferService.selectHrTransferList(new HrTransfer());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testInsertHrTransfer_DeptChange() {
        when(hrTransferMapper.selectTodayTransferCount()).thenReturn(0);
        when(hrTransferMapper.insertHrTransfer(any(HrTransfer.class))).thenReturn(1);

        HrTransfer newTransfer = new HrTransfer();
        newTransfer.setEmployeeId(100L);
        newTransfer.setOldDeptId(1L);
        newTransfer.setOldPositionId(10L);
        newTransfer.setNewDeptIdFirst(2L);
        newTransfer.setNewDeptIdSecond(3L);
        newTransfer.setNewDeptIdThird(5L);
        newTransfer.setNewPositionId(10L);

        int result = hrTransferService.insertHrTransfer(newTransfer, "admin");

        assertEquals(1, result);
        assertEquals("0", newTransfer.getTransferType());
        assertEquals("0", newTransfer.getStatus());
        assertNotNull(newTransfer.getTransferCode());
    }

    @Test
    void testInsertHrTransfer_PositionChange() {
        when(hrTransferMapper.selectTodayTransferCount()).thenReturn(5);
        when(hrTransferMapper.insertHrTransfer(any(HrTransfer.class))).thenReturn(1);

        HrTransfer newTransfer = new HrTransfer();
        newTransfer.setEmployeeId(100L);
        newTransfer.setOldDeptId(1L);
        newTransfer.setOldPositionId(10L);
        newTransfer.setNewDeptIdFirst(2L);
        newTransfer.setNewDeptIdSecond(3L);
        newTransfer.setNewDeptIdThird(1L);
        newTransfer.setNewPositionId(20L);

        int result = hrTransferService.insertHrTransfer(newTransfer, "admin");

        assertEquals(1, result);
        assertEquals("1", newTransfer.getTransferType());
    }

    @Test
    void testInsertHrTransfer_BothChange() {
        when(hrTransferMapper.selectTodayTransferCount()).thenReturn(0);
        when(hrTransferMapper.insertHrTransfer(any(HrTransfer.class))).thenReturn(1);

        HrTransfer newTransfer = new HrTransfer();
        newTransfer.setEmployeeId(100L);
        newTransfer.setOldDeptId(1L);
        newTransfer.setOldPositionId(10L);
        newTransfer.setNewDeptIdFirst(2L);
        newTransfer.setNewDeptIdSecond(3L);
        newTransfer.setNewDeptIdThird(5L);
        newTransfer.setNewPositionId(20L);

        int result = hrTransferService.insertHrTransfer(newTransfer, "admin");

        assertEquals(1, result);
        assertEquals("2", newTransfer.getTransferType());
    }

    @Test
    void testUpdateHrTransfer() {
        when(hrTransferMapper.updateHrTransfer(any(HrTransfer.class))).thenReturn(1);

        testTransfer.setRemark("更新备注");
        int result = hrTransferService.updateHrTransfer(testTransfer);

        assertEquals(1, result);
        assertNotNull(testTransfer.getUpdateTime());
    }

    @Test
    void testReviewHrTransfer_Success() {
        testTransfer.setStatus("0");
        when(hrTransferMapper.selectHrTransferByTransferId(1L)).thenReturn(testTransfer);
        when(hrTransferMapper.updateHrTransfer(any(HrTransfer.class))).thenReturn(1);
        when(hrEmployeeMapper.updateHrEmployee(any(HrEmployee.class))).thenReturn(1);

        int result = hrTransferService.reviewHrTransfer(testTransfer, "reviewer");

        assertEquals(1, result);
        assertEquals("1", testTransfer.getStatus());
        assertEquals("reviewer", testTransfer.getReviewer());
    }

    @Test
    void testReviewHrTransfer_NotFound() {
        when(hrTransferMapper.selectHrTransferByTransferId(999L)).thenReturn(null);

        HrTransfer transfer = new HrTransfer();
        transfer.setTransferId(999L);
        int result = hrTransferService.reviewHrTransfer(transfer, "reviewer");

        assertEquals(0, result);
    }

    @Test
    void testReviewHrTransfer_AlreadyReviewed() {
        testTransfer.setStatus("1");
        when(hrTransferMapper.selectHrTransferByTransferId(1L)).thenReturn(testTransfer);

        int result = hrTransferService.reviewHrTransfer(testTransfer, "reviewer");

        assertEquals(0, result);
    }

    @Test
    void testDeleteHrTransferByTransferIds() {
        Long[] ids = {1L, 2L, 3L};
        when(hrTransferMapper.deleteHrTransferByTransferIds(ids)).thenReturn(3);

        int result = hrTransferService.deleteHrTransferByTransferIds(ids);

        assertEquals(3, result);
    }

    @Test
    void testDeleteHrTransferByTransferId() {
        when(hrTransferMapper.deleteHrTransferByTransferId(1L)).thenReturn(1);

        int result = hrTransferService.deleteHrTransferByTransferId(1L);

        assertEquals(1, result);
    }
}
