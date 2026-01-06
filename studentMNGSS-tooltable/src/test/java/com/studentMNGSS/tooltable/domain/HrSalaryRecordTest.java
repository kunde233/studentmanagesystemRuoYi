package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrSalaryRecord 实体类单元测试
 */
public class HrSalaryRecordTest {

    @Test
    void testGettersAndSetters() {
        HrSalaryRecord record = new HrSalaryRecord();

        record.setRecordId(1L);
        assertEquals(1L, record.getRecordId());

        record.setRecordCode("SR202501010001");
        assertEquals("SR202501010001", record.getRecordCode());

        record.setEmployeeId(100L);
        assertEquals(100L, record.getEmployeeId());

        record.setEmployeeName("张三");
        assertEquals("张三", record.getEmployeeName());

        record.setEmployeeCode("E001");
        assertEquals("E001", record.getEmployeeCode());

        record.setStandardId(200L);
        assertEquals(200L, record.getStandardId());

        record.setStandardName("标准薪酬A");
        assertEquals("标准薪酬A", record.getStandardName());

        record.setSalaryMonth("2025-01");
        assertEquals("2025-01", record.getSalaryMonth());
    }

    @Test
    void testAmounts() {
        HrSalaryRecord record = new HrSalaryRecord();

        BigDecimal baseAmount = new BigDecimal("10000.00");
        record.setBaseAmount(baseAmount);
        assertEquals(baseAmount, record.getBaseAmount());

        BigDecimal bonusAmount = new BigDecimal("2000.00");
        record.setBonusAmount(bonusAmount);
        assertEquals(bonusAmount, record.getBonusAmount());

        BigDecimal deductAmount = new BigDecimal("500.00");
        record.setDeductAmount(deductAmount);
        assertEquals(deductAmount, record.getDeductAmount());

        BigDecimal actualAmount = new BigDecimal("11500.00");
        record.setActualAmount(actualAmount);
        assertEquals(actualAmount, record.getActualAmount());
    }

    @Test
    void testStatusAndReview() {
        HrSalaryRecord record = new HrSalaryRecord();

        record.setStatus("0");
        assertEquals("0", record.getStatus());

        record.setReviewer("reviewer");
        assertEquals("reviewer", record.getReviewer());

        Date reviewTime = new Date();
        record.setReviewTime(reviewTime);
        assertEquals(reviewTime, record.getReviewTime());

        record.setDelFlag("0");
        assertEquals("0", record.getDelFlag());
    }

    @Test
    void testDeptIds() {
        HrSalaryRecord record = new HrSalaryRecord();

        record.setDeptIdFirst(1L);
        assertEquals(1L, record.getDeptIdFirst());

        record.setDeptIdSecond(2L);
        assertEquals(2L, record.getDeptIdSecond());

        record.setDeptIdThird(3L);
        assertEquals(3L, record.getDeptIdThird());
    }

    @Test
    void testDetails() {
        HrSalaryRecord record = new HrSalaryRecord();

        List<HrSalaryRecordDetail> details = new ArrayList<>();
        HrSalaryRecordDetail detail = new HrSalaryRecordDetail();
        detail.setItemId(1L);
        details.add(detail);

        record.setDetails(details);

        assertNotNull(record.getDetails());
        assertEquals(1, record.getDetails().size());
    }

    @Test
    void testToString() {
        HrSalaryRecord record = new HrSalaryRecord();
        record.setRecordId(1L);
        record.setRecordCode("SR001");
        record.setEmployeeName("测试");

        String result = record.toString();

        assertNotNull(result);
        assertTrue(result.contains("recordId"));
        assertTrue(result.contains("recordCode"));
    }
}
