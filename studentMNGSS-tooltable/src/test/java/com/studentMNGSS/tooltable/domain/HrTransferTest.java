package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrTransfer 实体类单元测试
 */
public class HrTransferTest {

    @Test
    void testGettersAndSetters() {
        HrTransfer transfer = new HrTransfer();

        transfer.setTransferId(1L);
        assertEquals(1L, transfer.getTransferId());

        transfer.setTransferCode("DD202501010001");
        assertEquals("DD202501010001", transfer.getTransferCode());

        transfer.setEmployeeId(100L);
        assertEquals(100L, transfer.getEmployeeId());

        transfer.setEmployeeName("张三");
        assertEquals("张三", transfer.getEmployeeName());

        transfer.setTransferType("0");
        assertEquals("0", transfer.getTransferType());
    }

    @Test
    void testOldInfo() {
        HrTransfer transfer = new HrTransfer();

        transfer.setOldDeptId(1L);
        assertEquals(1L, transfer.getOldDeptId());

        transfer.setOldDeptName("原部门");
        assertEquals("原部门", transfer.getOldDeptName());

        transfer.setOldPositionId(10L);
        assertEquals(10L, transfer.getOldPositionId());

        transfer.setOldPositionName("原职位");
        assertEquals("原职位", transfer.getOldPositionName());
    }

    @Test
    void testNewInfo() {
        HrTransfer transfer = new HrTransfer();

        transfer.setNewDeptIdFirst(2L);
        assertEquals(2L, transfer.getNewDeptIdFirst());

        transfer.setNewDeptIdSecond(3L);
        assertEquals(3L, transfer.getNewDeptIdSecond());

        transfer.setNewDeptIdThird(4L);
        assertEquals(4L, transfer.getNewDeptIdThird());

        transfer.setNewDeptName("新部门");
        assertEquals("新部门", transfer.getNewDeptName());

        transfer.setNewPositionId(20L);
        assertEquals(20L, transfer.getNewPositionId());

        transfer.setNewPositionName("新职位");
        assertEquals("新职位", transfer.getNewPositionName());
    }

    @Test
    void testTransferInfo() {
        HrTransfer transfer = new HrTransfer();

        Date transferDate = new Date();
        transfer.setTransferDate(transferDate);
        assertEquals(transferDate, transfer.getTransferDate());

        transfer.setTransferReason("工作需要");
        assertEquals("工作需要", transfer.getTransferReason());
    }

    @Test
    void testStatusAndReview() {
        HrTransfer transfer = new HrTransfer();

        transfer.setStatus("0");
        assertEquals("0", transfer.getStatus());

        transfer.setReviewer("reviewer");
        assertEquals("reviewer", transfer.getReviewer());

        Date reviewTime = new Date();
        transfer.setReviewTime(reviewTime);
        assertEquals(reviewTime, transfer.getReviewTime());

        transfer.setDelFlag("0");
        assertEquals("0", transfer.getDelFlag());
    }

    @Test
    void testToString() {
        HrTransfer transfer = new HrTransfer();
        transfer.setTransferId(1L);
        transfer.setTransferCode("DD001");
        transfer.setEmployeeName("测试");

        String result = transfer.toString();

        assertNotNull(result);
        assertTrue(result.contains("transferId"));
        assertTrue(result.contains("transferCode"));
    }
}
