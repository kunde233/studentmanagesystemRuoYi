package com.studentMNGSS.tooltable.service.impl;

import com.studentMNGSS.tooltable.domain.HrSalaryStandard;
import com.studentMNGSS.tooltable.domain.HrSalaryStandardDetail;
import com.studentMNGSS.tooltable.mapper.HrSalaryStandardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * HrSalaryStandardServiceImpl 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class HrSalaryStandardServiceImplTest {

    @Mock
    private HrSalaryStandardMapper hrSalaryStandardMapper;

    @InjectMocks
    private HrSalaryStandardServiceImpl hrSalaryStandardService;

    private HrSalaryStandard testStandard;

    @BeforeEach
    void setUp() {
        testStandard = new HrSalaryStandard();
        testStandard.setStandardId(1L);
        testStandard.setStandardCode("SS0001");
        testStandard.setStandardName("标准薪酬A");
        testStandard.setStatus("0");
        testStandard.setTotalAmount(new BigDecimal("10000"));
    }

    @Test
    void testSelectHrSalaryStandardByStandardId() {
        List<HrSalaryStandardDetail> details = new ArrayList<>();
        HrSalaryStandardDetail detail = new HrSalaryStandardDetail();
        detail.setItemId(1L);
        detail.setAmount(new BigDecimal("5000"));
        details.add(detail);

        when(hrSalaryStandardMapper.selectHrSalaryStandardByStandardId(1L)).thenReturn(testStandard);
        when(hrSalaryStandardMapper.selectHrSalaryStandardDetailByStandardId(1L)).thenReturn(details);

        HrSalaryStandard result = hrSalaryStandardService.selectHrSalaryStandardByStandardId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getStandardId());
        assertNotNull(result.getDetails());
        assertEquals(1, result.getDetails().size());
    }

    @Test
    void testSelectHrSalaryStandardByStandardId_NotFound() {
        when(hrSalaryStandardMapper.selectHrSalaryStandardByStandardId(999L)).thenReturn(null);

        HrSalaryStandard result = hrSalaryStandardService.selectHrSalaryStandardByStandardId(999L);

        assertNull(result);
    }

    @Test
    void testSelectHrSalaryStandardList() {
        List<HrSalaryStandard> standards = new ArrayList<>();
        standards.add(testStandard);

        when(hrSalaryStandardMapper.selectHrSalaryStandardList(any(HrSalaryStandard.class))).thenReturn(standards);

        List<HrSalaryStandard> result = hrSalaryStandardService.selectHrSalaryStandardList(new HrSalaryStandard());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testSelectHrSalaryStandardAll() {
        List<HrSalaryStandard> standards = new ArrayList<>();
        standards.add(testStandard);

        when(hrSalaryStandardMapper.selectHrSalaryStandardAll()).thenReturn(standards);

        List<HrSalaryStandard> result = hrSalaryStandardService.selectHrSalaryStandardAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testInsertHrSalaryStandard() {
        when(hrSalaryStandardMapper.selectMaxStandardCode()).thenReturn(null);
        when(hrSalaryStandardMapper.insertHrSalaryStandard(any(HrSalaryStandard.class))).thenReturn(1);

        HrSalaryStandard newStandard = new HrSalaryStandard();
        newStandard.setStandardName("新标准");

        List<HrSalaryStandardDetail> details = new ArrayList<>();
        HrSalaryStandardDetail detail = new HrSalaryStandardDetail();
        detail.setItemId(1L);
        detail.setItemType("0");
        detail.setAmount(new BigDecimal("5000"));
        details.add(detail);
        newStandard.setDetails(details);

        int result = hrSalaryStandardService.insertHrSalaryStandard(newStandard);

        assertEquals(1, result);
        assertEquals("SS0001", newStandard.getStandardCode());
        assertEquals(new BigDecimal("5000"), newStandard.getTotalAmount());
    }

    @Test
    void testInsertHrSalaryStandard_WithExistingCode() {
        when(hrSalaryStandardMapper.selectMaxStandardCode()).thenReturn("SS0010");
        when(hrSalaryStandardMapper.insertHrSalaryStandard(any(HrSalaryStandard.class))).thenReturn(1);

        HrSalaryStandard newStandard = new HrSalaryStandard();
        newStandard.setStandardName("新标准");
        newStandard.setDetails(new ArrayList<>());

        int result = hrSalaryStandardService.insertHrSalaryStandard(newStandard);

        assertEquals(1, result);
        assertEquals("SS0011", newStandard.getStandardCode());
    }

    @Test
    void testUpdateHrSalaryStandard() {
        when(hrSalaryStandardMapper.updateHrSalaryStandard(any(HrSalaryStandard.class))).thenReturn(1);
        when(hrSalaryStandardMapper.deleteHrSalaryStandardDetailByStandardId(anyLong())).thenReturn(1);

        testStandard.setStandardName("更新后的标准");
        testStandard.setDetails(new ArrayList<>());

        int result = hrSalaryStandardService.updateHrSalaryStandard(testStandard);

        assertEquals(1, result);
        assertNotNull(testStandard.getUpdateTime());
    }

    @Test
    void testReviewHrSalaryStandard() {
        when(hrSalaryStandardMapper.updateHrSalaryStandard(any(HrSalaryStandard.class))).thenReturn(1);

        int result = hrSalaryStandardService.reviewHrSalaryStandard(testStandard, "reviewer");

        assertEquals(1, result);
        verify(hrSalaryStandardMapper, times(1)).updateHrSalaryStandard(argThat(std ->
            "1".equals(std.getStatus()) && "reviewer".equals(std.getReviewer())
        ));
    }

    @Test
    void testDeleteHrSalaryStandardByStandardIds() {
        Long[] ids = {1L, 2L};
        when(hrSalaryStandardMapper.deleteHrSalaryStandardDetailByStandardId(anyLong())).thenReturn(1);
        when(hrSalaryStandardMapper.deleteHrSalaryStandardByStandardIds(ids)).thenReturn(2);

        int result = hrSalaryStandardService.deleteHrSalaryStandardByStandardIds(ids);

        assertEquals(2, result);
        verify(hrSalaryStandardMapper, times(2)).deleteHrSalaryStandardDetailByStandardId(anyLong());
    }

    @Test
    void testDeleteHrSalaryStandardByStandardId() {
        when(hrSalaryStandardMapper.deleteHrSalaryStandardDetailByStandardId(1L)).thenReturn(1);
        when(hrSalaryStandardMapper.deleteHrSalaryStandardByStandardId(1L)).thenReturn(1);

        int result = hrSalaryStandardService.deleteHrSalaryStandardByStandardId(1L);

        assertEquals(1, result);
    }

    @Test
    void testGenerateStandardCode() {
        when(hrSalaryStandardMapper.selectMaxStandardCode()).thenReturn(null);

        String code = hrSalaryStandardService.generateStandardCode();

        assertEquals("SS0001", code);
    }

    @Test
    void testGenerateStandardCode_WithExisting() {
        when(hrSalaryStandardMapper.selectMaxStandardCode()).thenReturn("SS0099");

        String code = hrSalaryStandardService.generateStandardCode();

        assertEquals("SS0100", code);
    }

    @Test
    void testGenerateStandardCode_InvalidFormat() {
        when(hrSalaryStandardMapper.selectMaxStandardCode()).thenReturn("invalid");

        String code = hrSalaryStandardService.generateStandardCode();

        assertEquals("SS0001", code);
    }

    @Test
    void testCalculateTotalAmount_MixedTypes() {
        when(hrSalaryStandardMapper.selectMaxStandardCode()).thenReturn(null);
        when(hrSalaryStandardMapper.insertHrSalaryStandard(any(HrSalaryStandard.class))).thenReturn(1);

        HrSalaryStandard newStandard = new HrSalaryStandard();
        List<HrSalaryStandardDetail> details = new ArrayList<>();

        HrSalaryStandardDetail income = new HrSalaryStandardDetail();
        income.setItemId(1L);
        income.setItemType("0");
        income.setAmount(new BigDecimal("10000"));
        details.add(income);

        HrSalaryStandardDetail deduction = new HrSalaryStandardDetail();
        deduction.setItemId(2L);
        deduction.setItemType("1");
        deduction.setAmount(new BigDecimal("2000"));
        details.add(deduction);

        newStandard.setDetails(details);

        hrSalaryStandardService.insertHrSalaryStandard(newStandard);

        assertEquals(new BigDecimal("8000"), newStandard.getTotalAmount());
    }
}
