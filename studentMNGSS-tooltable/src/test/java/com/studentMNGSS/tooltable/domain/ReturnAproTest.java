package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReturnApro 实体类单元测试
 */
public class ReturnAproTest {

    @Test
    void testGettersAndSetters() {
        ReturnApro returnApro = new ReturnApro();

        returnApro.setApplicationId(1L);
        assertEquals(1L, returnApro.getApplicationId());

        returnApro.setStudentId(100L);
        assertEquals(100L, returnApro.getStudentId());

        Date plannedReturnTime = new Date();
        returnApro.setPlannedReturnTime(plannedReturnTime);
        assertEquals(plannedReturnTime, returnApro.getPlannedReturnTime());

        returnApro.setTransportation("高铁");
        assertEquals("高铁", returnApro.getTransportation());

        returnApro.setHealthStatus("健康");
        assertEquals("健康", returnApro.getHealthStatus());

        returnApro.setTravelCodeAttachment("/uploads/travel.jpg");
        assertEquals("/uploads/travel.jpg", returnApro.getTravelCodeAttachment());

        returnApro.setStatus("1");
        assertEquals("1", returnApro.getStatus());

        returnApro.setApprovalComment("已审批通过");
        assertEquals("已审批通过", returnApro.getApprovalComment());
    }

    @Test
    void testToString() {
        ReturnApro returnApro = new ReturnApro();
        returnApro.setApplicationId(1L);
        returnApro.setStudentId(100L);
        returnApro.setApprovalComment("测试审批");

        String result = returnApro.toString();

        assertNotNull(result);
        assertTrue(result.contains("applicationId"));
        assertTrue(result.contains("studentId"));
    }
}
