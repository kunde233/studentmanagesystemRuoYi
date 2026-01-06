package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrSalaryStandard 实体类单元测试
 */
public class HrSalaryStandardTest {

    @Test
    void testGettersAndSetters() {
        HrSalaryStandard standard = new HrSalaryStandard();

        standard.setStandardId(1L);
        assertEquals(1L, standard.getStandardId());

        standard.setStandardCode("SS0001");
        assertEquals("SS0001", standard.getStandardCode());

        standard.setStandardName("标准薪酬A");
        assertEquals("标准薪酬A", standard.getStandardName());

        standard.setPositionId(100L);
        assertEquals(100L, standard.getPositionId());

        standard.setPositionName("软件工程师");
        assertEquals("软件工程师", standard.getPositionName());

        standard.setTitle("高级");
        assertEquals("高级", standard.getTitle());

        BigDecimal totalAmount = new BigDecimal("15000.00");
        standard.setTotalAmount(totalAmount);
        assertEquals(totalAmount, standard.getTotalAmount());

        standard.setMaker("maker");
        assertEquals("maker", standard.getMaker());

        standard.setChanger("changer");
        assertEquals("changer", standard.getChanger());

        Date changeTime = new Date();
        standard.setChangeTime(changeTime);
        assertEquals(changeTime, standard.getChangeTime());

        standard.setReviewer("reviewer");
        assertEquals("reviewer", standard.getReviewer());

        Date reviewTime = new Date();
        standard.setReviewTime(reviewTime);
        assertEquals(reviewTime, standard.getReviewTime());

        standard.setStatus("0");
        assertEquals("0", standard.getStatus());

        standard.setDelFlag("0");
        assertEquals("0", standard.getDelFlag());
    }

    @Test
    void testDetails() {
        HrSalaryStandard standard = new HrSalaryStandard();

        List<HrSalaryStandardDetail> details = new ArrayList<>();
        HrSalaryStandardDetail detail = new HrSalaryStandardDetail();
        detail.setItemId(1L);
        details.add(detail);

        standard.setDetails(details);

        assertNotNull(standard.getDetails());
        assertEquals(1, standard.getDetails().size());
    }

    @Test
    void testToString() {
        HrSalaryStandard standard = new HrSalaryStandard();
        standard.setStandardId(1L);
        standard.setStandardCode("SS0001");
        standard.setStandardName("测试标准");

        String result = standard.toString();

        assertNotNull(result);
        assertTrue(result.contains("standardId"));
        assertTrue(result.contains("standardCode"));
        assertTrue(result.contains("standardName"));
    }
}
