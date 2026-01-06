package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrSalaryItem 实体类单元测试
 */
public class HrSalaryItemTest {

    @Test
    void testGettersAndSetters() {
        HrSalaryItem item = new HrSalaryItem();

        item.setItemId(1L);
        assertEquals(1L, item.getItemId());

        item.setItemCode("SI001");
        assertEquals("SI001", item.getItemCode());

        item.setItemName("基本工资");
        assertEquals("基本工资", item.getItemName());

        item.setItemType("0");
        assertEquals("0", item.getItemType());

        item.setItemSort(1);
        assertEquals(1, item.getItemSort());

        item.setStatus("0");
        assertEquals("0", item.getStatus());

        item.setDelFlag("0");
        assertEquals("0", item.getDelFlag());
    }

    @Test
    void testToString() {
        HrSalaryItem item = new HrSalaryItem();
        item.setItemId(1L);
        item.setItemCode("SI001");
        item.setItemName("基本工资");

        String result = item.toString();

        assertNotNull(result);
        assertTrue(result.contains("itemId"));
        assertTrue(result.contains("itemCode"));
        assertTrue(result.contains("itemName"));
    }

    @Test
    void testItemTypeValues() {
        HrSalaryItem item = new HrSalaryItem();

        item.setItemType("0");
        assertEquals("0", item.getItemType());

        item.setItemType("1");
        assertEquals("1", item.getItemType());
    }

    @Test
    void testStatusValues() {
        HrSalaryItem item = new HrSalaryItem();

        item.setStatus("0");
        assertEquals("0", item.getStatus());

        item.setStatus("1");
        assertEquals("1", item.getStatus());
    }

    @Test
    void testItemSort() {
        HrSalaryItem item = new HrSalaryItem();

        item.setItemSort(0);
        assertEquals(0, item.getItemSort());

        item.setItemSort(100);
        assertEquals(100, item.getItemSort());
    }
}
