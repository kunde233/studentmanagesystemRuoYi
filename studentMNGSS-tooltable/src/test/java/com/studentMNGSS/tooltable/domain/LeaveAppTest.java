package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeaveApp 实体类单元测试
 */
public class LeaveAppTest {

    @Test
    void testGettersAndSetters() {
        LeaveApp leaveApp = new LeaveApp();

        leaveApp.setApplicationId(1L);
        assertEquals(1L, leaveApp.getApplicationId());

        leaveApp.setStudentId(100L);
        assertEquals(100L, leaveApp.getStudentId());

        leaveApp.setLeaveType("1");
        assertEquals("1", leaveApp.getLeaveType());

        Date startTime = new Date();
        leaveApp.setStartTime(startTime);
        assertEquals(startTime, leaveApp.getStartTime());

        Date endTime = new Date();
        leaveApp.setEndTime(endTime);
        assertEquals(endTime, leaveApp.getEndTime());

        leaveApp.setReason("家中有事");
        assertEquals("家中有事", leaveApp.getReason());

        leaveApp.setAttachment("/uploads/attachment.pdf");
        assertEquals("/uploads/attachment.pdf", leaveApp.getAttachment());

        leaveApp.setStatus("0");
        assertEquals("0", leaveApp.getStatus());

        leaveApp.setApproverId(200L);
        assertEquals(200L, leaveApp.getApproverId());

        Date approveTime = new Date();
        leaveApp.setApproveTime(approveTime);
        assertEquals(approveTime, leaveApp.getApproveTime());

        leaveApp.setRejectReason("材料不全");
        assertEquals("材料不全", leaveApp.getRejectReason());
    }

    @Test
    void testToString() {
        LeaveApp leaveApp = new LeaveApp();
        leaveApp.setApplicationId(1L);
        leaveApp.setStudentId(100L);
        leaveApp.setReason("测试请假");

        String result = leaveApp.toString();

        assertNotNull(result);
        assertTrue(result.contains("applicationId"));
        assertTrue(result.contains("studentId"));
        assertTrue(result.contains("reason"));
    }

    @Test
    void testStatusValues() {
        LeaveApp leaveApp = new LeaveApp();

        leaveApp.setStatus("0");
        assertEquals("0", leaveApp.getStatus());

        leaveApp.setStatus("1");
        assertEquals("1", leaveApp.getStatus());

        leaveApp.setStatus("2");
        assertEquals("2", leaveApp.getStatus());
    }

    @Test
    void testLeaveTypeValues() {
        LeaveApp leaveApp = new LeaveApp();

        leaveApp.setLeaveType("1");
        assertEquals("1", leaveApp.getLeaveType());

        leaveApp.setLeaveType("2");
        assertEquals("2", leaveApp.getLeaveType());

        leaveApp.setLeaveType("3");
        assertEquals("3", leaveApp.getLeaveType());
    }

    @Test
    void testDateFields() {
        LeaveApp leaveApp = new LeaveApp();

        Date now = new Date();
        leaveApp.setStartTime(now);
        leaveApp.setEndTime(now);
        leaveApp.setApproveTime(now);

        assertEquals(now, leaveApp.getStartTime());
        assertEquals(now, leaveApp.getEndTime());
        assertEquals(now, leaveApp.getApproveTime());
    }

    @Test
    void testNullValues() {
        LeaveApp leaveApp = new LeaveApp();

        assertNull(leaveApp.getApplicationId());
        assertNull(leaveApp.getStudentId());
        assertNull(leaveApp.getLeaveType());
        assertNull(leaveApp.getStartTime());
        assertNull(leaveApp.getEndTime());
        assertNull(leaveApp.getReason());
        assertNull(leaveApp.getAttachment());
        assertNull(leaveApp.getStatus());
        assertNull(leaveApp.getApproverId());
        assertNull(leaveApp.getApproveTime());
        assertNull(leaveApp.getRejectReason());
    }
}
