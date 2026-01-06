package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReturnApp 实体类单元测试
 */
public class ReturnAppTest {

    @Test
    void testGettersAndSetters() {
        ReturnApp returnApp = new ReturnApp();

        returnApp.setApplicationId(1L);
        assertEquals(1L, returnApp.getApplicationId());

        returnApp.setStudentId(100L);
        assertEquals(100L, returnApp.getStudentId());

        Date plannedReturnTime = new Date();
        returnApp.setPlannedReturnTime(plannedReturnTime);
        assertEquals(plannedReturnTime, returnApp.getPlannedReturnTime());

        returnApp.setTransportation("高铁");
        assertEquals("高铁", returnApp.getTransportation());

        returnApp.setHealthStatus("健康");
        assertEquals("健康", returnApp.getHealthStatus());

        returnApp.setTravelCodeAttachment("/uploads/travel.jpg");
        assertEquals("/uploads/travel.jpg", returnApp.getTravelCodeAttachment());

        returnApp.setStatus("0");
        assertEquals("0", returnApp.getStatus());

        returnApp.setApprovalComment("同意返校");
        assertEquals("同意返校", returnApp.getApprovalComment());
    }

    @Test
    void testToString() {
        ReturnApp returnApp = new ReturnApp();
        returnApp.setApplicationId(1L);
        returnApp.setStudentId(100L);
        returnApp.setTransportation("飞机");

        String result = returnApp.toString();

        assertNotNull(result);
        assertTrue(result.contains("applicationId"));
        assertTrue(result.contains("studentId"));
        assertTrue(result.contains("transportation"));
    }
}
