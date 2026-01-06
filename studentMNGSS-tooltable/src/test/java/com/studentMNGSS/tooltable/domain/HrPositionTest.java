package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrPosition 实体类单元测试
 */
public class HrPositionTest {

    @Test
    void testGettersAndSetters() {
        HrPosition position = new HrPosition();

        position.setPositionId(1L);
        assertEquals(1L, position.getPositionId());

        position.setPositionCode("P001");
        assertEquals("P001", position.getPositionCode());

        position.setPositionName("软件工程师");
        assertEquals("软件工程师", position.getPositionName());

        position.setDeptId(100L);
        assertEquals(100L, position.getDeptId());

        position.setDeptName("研发部");
        assertEquals("研发部", position.getDeptName());

        position.setPositionSort(1);
        assertEquals(1, position.getPositionSort());

        position.setStatus("0");
        assertEquals("0", position.getStatus());

        position.setDelFlag("0");
        assertEquals("0", position.getDelFlag());
    }

    @Test
    void testToString() {
        HrPosition position = new HrPosition();
        position.setPositionId(1L);
        position.setPositionCode("P001");
        position.setPositionName("软件工程师");

        String result = position.toString();

        assertNotNull(result);
        assertTrue(result.contains("positionId"));
        assertTrue(result.contains("positionCode"));
        assertTrue(result.contains("positionName"));
    }

    @Test
    void testStatusValues() {
        HrPosition position = new HrPosition();

        position.setStatus("0");
        assertEquals("0", position.getStatus());

        position.setStatus("1");
        assertEquals("1", position.getStatus());
    }

    @Test
    void testDelFlagValues() {
        HrPosition position = new HrPosition();

        position.setDelFlag("0");
        assertEquals("0", position.getDelFlag());

        position.setDelFlag("2");
        assertEquals("2", position.getDelFlag());
    }

    @Test
    void testPositionSort() {
        HrPosition position = new HrPosition();

        position.setPositionSort(0);
        assertEquals(0, position.getPositionSort());

        position.setPositionSort(100);
        assertEquals(100, position.getPositionSort());

        position.setPositionSort(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, position.getPositionSort());
    }
}
