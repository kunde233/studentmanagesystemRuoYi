package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeaveApro 实体类单元测试
 */
public class LeaveAproTest {

    @Test
    void testGettersAndSetters() {
        LeaveApro leaveApro = new LeaveApro();

        leaveApro.setApplicationId(1L);
        assertEquals(1L, leaveApro.getApplicationId());

        leaveApro.setStudentId(100L);
        assertEquals(100L, leaveApro.getStudentId());

        leaveApro.setLeaveType("1");
        assertEquals("1", leaveApro.getLeaveType());

        Date startTime = new Date();
        leaveApro.setStartTime(startTime);
        assertEquals(startTime, leaveApro.getStartTime());

        Date endTime = new Date();
        leaveApro.setEndTime(endTime);
        assertEquals(endTime, leaveApro.getEndTime());

        leaveApro.setReason("家中有事");
        assertEquals("家中有事", leaveApro.getReason());

        leaveApro.setAttachment("/uploads/attachment.pdf");
        assertEquals("/uploads/attachment.pdf", leaveApro.getAttachment());

        leaveApro.setStatus("0");
        assertEquals("0", leaveApro.getStatus());

        leaveApro.setApproverId(200L);
        assertEquals(200L, leaveApro.getApproverId());

        Date approveTime = new Date();
        leaveApro.setApproveTime(approveTime);
        assertEquals(approveTime, leaveApro.getApproveTime());

        leaveApro.setRejectReason("材料不全");
        assertEquals("材料不全", leaveApro.getRejectReason());
    }

    @Test
    void testToString() {
        LeaveApro leaveApro = new LeaveApro();
        leaveApro.setApplicationId(1L);
        leaveApro.setStudentId(100L);
        leaveApro.setReason("测试请假");

        String result = leaveApro.toString();

        assertNotNull(result);
        assertTrue(result.contains("applicationId"));
        assertTrue(result.contains("studentId"));
        assertTrue(result.contains("reason"));
    }
}
