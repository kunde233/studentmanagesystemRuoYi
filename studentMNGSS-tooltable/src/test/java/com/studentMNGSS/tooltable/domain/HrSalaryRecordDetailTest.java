package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrSalaryRecordDetail 实体类单元测试
 */
public class HrSalaryRecordDetailTest {

    @Test
    void testGettersAndSetters() {
        HrSalaryRecordDetail detail = new HrSalaryRecordDetail();

        detail.setDetailId(1L);
        assertEquals(1L, detail.getDetailId());

        detail.setRecordId(100L);
        assertEquals(100L, detail.getRecordId());

        detail.setItemId(200L);
        assertEquals(200L, detail.getItemId());

        detail.setItemName("基本工资");
        assertEquals("基本工资", detail.getItemName());

        detail.setItemType("0");
        assertEquals("0", detail.getItemType());

        BigDecimal amount = new BigDecimal("5000.00");
        detail.setAmount(amount);
        assertEquals(amount, detail.getAmount());
    }

    @Test
    void testToString() {
        HrSalaryRecordDetail detail = new HrSalaryRecordDetail();
        detail.setDetailId(1L);
        detail.setItemName("测试项目");

        String result = detail.toString();

        assertNotNull(result);
        assertTrue(result.contains("detailId"));
        assertTrue(result.contains("itemName"));
    }
}
