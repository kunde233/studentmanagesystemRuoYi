package com.studentMNGSS.tooltable.service.impl;

import com.studentMNGSS.tooltable.domain.HrPosition;
import com.studentMNGSS.tooltable.mapper.HrPositionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * HrPositionServiceImpl 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class HrPositionServiceImplTest {

    @Mock
    private HrPositionMapper hrPositionMapper;

    @InjectMocks
    private HrPositionServiceImpl hrPositionService;

    private HrPosition testPosition;

    @BeforeEach
    void setUp() {
        testPosition = new HrPosition();
        testPosition.setPositionId(1L);
        testPosition.setPositionCode("P001");
        testPosition.setPositionName("软件工程师");
        testPosition.setDeptId(100L);
        testPosition.setDeptName("研发部");
        testPosition.setPositionSort(1);
        testPosition.setStatus("0");
    }

    @Test
    void testSelectHrPositionByPositionId() {
        when(hrPositionMapper.selectHrPositionByPositionId(1L)).thenReturn(testPosition);

        HrPosition result = hrPositionService.selectHrPositionByPositionId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getPositionId());
        assertEquals("P001", result.getPositionCode());
        assertEquals("软件工程师", result.getPositionName());
        verify(hrPositionMapper, times(1)).selectHrPositionByPositionId(1L);
    }

    @Test
    void testSelectHrPositionByPositionId_NotFound() {
        when(hrPositionMapper.selectHrPositionByPositionId(999L)).thenReturn(null);

        HrPosition result = hrPositionService.selectHrPositionByPositionId(999L);

        assertNull(result);
    }

    @Test
    void testSelectHrPositionList() {
        List<HrPosition> positions = new ArrayList<>();
        positions.add(testPosition);
        HrPosition position2 = new HrPosition();
        position2.setPositionId(2L);
        position2.setPositionName("产品经理");
        positions.add(position2);

        when(hrPositionMapper.selectHrPositionList(any(HrPosition.class))).thenReturn(positions);

        List<HrPosition> result = hrPositionService.selectHrPositionList(new HrPosition());

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSelectHrPositionList_Empty() {
        when(hrPositionMapper.selectHrPositionList(any(HrPosition.class))).thenReturn(new ArrayList<>());

        List<HrPosition> result = hrPositionService.selectHrPositionList(new HrPosition());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSelectHrPositionByDeptId() {
        List<HrPosition> positions = new ArrayList<>();
        positions.add(testPosition);

        when(hrPositionMapper.selectHrPositionByDeptId(100L)).thenReturn(positions);

        List<HrPosition> result = hrPositionService.selectHrPositionByDeptId(100L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).getDeptId());
    }

    @Test
    void testSelectHrPositionAll() {
        List<HrPosition> positions = new ArrayList<>();
        positions.add(testPosition);
        HrPosition position2 = new HrPosition();
        position2.setPositionId(2L);
        positions.add(position2);

        when(hrPositionMapper.selectHrPositionAll()).thenReturn(positions);

        List<HrPosition> result = hrPositionService.selectHrPositionAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testInsertHrPosition() {
        when(hrPositionMapper.insertHrPosition(any(HrPosition.class))).thenReturn(1);

        HrPosition newPosition = new HrPosition();
        newPosition.setPositionCode("P002");
        newPosition.setPositionName("测试工程师");

        int result = hrPositionService.insertHrPosition(newPosition);

        assertEquals(1, result);
        assertNotNull(newPosition.getCreateTime());
        verify(hrPositionMapper, times(1)).insertHrPosition(any(HrPosition.class));
    }

    @Test
    void testUpdateHrPosition() {
        when(hrPositionMapper.updateHrPosition(any(HrPosition.class))).thenReturn(1);

        testPosition.setPositionName("高级软件工程师");
        int result = hrPositionService.updateHrPosition(testPosition);

        assertEquals(1, result);
        assertNotNull(testPosition.getUpdateTime());
        verify(hrPositionMapper, times(1)).updateHrPosition(any(HrPosition.class));
    }

    @Test
    void testDeleteHrPositionByPositionIds() {
        Long[] ids = {1L, 2L, 3L};
        when(hrPositionMapper.deleteHrPositionByPositionIds(ids)).thenReturn(3);

        int result = hrPositionService.deleteHrPositionByPositionIds(ids);

        assertEquals(3, result);
        verify(hrPositionMapper, times(1)).deleteHrPositionByPositionIds(ids);
    }

    @Test
    void testDeleteHrPositionByPositionId() {
        when(hrPositionMapper.deleteHrPositionByPositionId(1L)).thenReturn(1);

        int result = hrPositionService.deleteHrPositionByPositionId(1L);

        assertEquals(1, result);
        verify(hrPositionMapper, times(1)).deleteHrPositionByPositionId(1L);
    }

    @Test
    void testCheckPositionCodeUnique_Unique() {
        when(hrPositionMapper.checkPositionCodeUnique("P001")).thenReturn(null);

        boolean result = hrPositionService.checkPositionCodeUnique(testPosition);

        assertTrue(result);
    }

    @Test
    void testCheckPositionCodeUnique_SamePosition() {
        HrPosition existingPosition = new HrPosition();
        existingPosition.setPositionId(1L);
        existingPosition.setPositionCode("P001");

        when(hrPositionMapper.checkPositionCodeUnique("P001")).thenReturn(existingPosition);

        boolean result = hrPositionService.checkPositionCodeUnique(testPosition);

        assertTrue(result);
    }

    @Test
    void testCheckPositionCodeUnique_NotUnique() {
        HrPosition existingPosition = new HrPosition();
        existingPosition.setPositionId(2L);
        existingPosition.setPositionCode("P001");

        when(hrPositionMapper.checkPositionCodeUnique("P001")).thenReturn(existingPosition);

        boolean result = hrPositionService.checkPositionCodeUnique(testPosition);

        assertFalse(result);
    }

    @Test
    void testCheckPositionCodeUnique_NewPosition() {
        HrPosition newPosition = new HrPosition();
        newPosition.setPositionCode("P003");

        when(hrPositionMapper.checkPositionCodeUnique("P003")).thenReturn(null);

        boolean result = hrPositionService.checkPositionCodeUnique(newPosition);

        assertTrue(result);
    }

    @Test
    void testCheckPositionNameUnique_Unique() {
        when(hrPositionMapper.checkPositionNameUnique(any(HrPosition.class))).thenReturn(null);

        boolean result = hrPositionService.checkPositionNameUnique(testPosition);

        assertTrue(result);
    }

    @Test
    void testCheckPositionNameUnique_SamePosition() {
        HrPosition existingPosition = new HrPosition();
        existingPosition.setPositionId(1L);
        existingPosition.setPositionName("软件工程师");

        when(hrPositionMapper.checkPositionNameUnique(any(HrPosition.class))).thenReturn(existingPosition);

        boolean result = hrPositionService.checkPositionNameUnique(testPosition);

        assertTrue(result);
    }

    @Test
    void testCheckPositionNameUnique_NotUnique() {
        HrPosition existingPosition = new HrPosition();
        existingPosition.setPositionId(2L);
        existingPosition.setPositionName("软件工程师");

        when(hrPositionMapper.checkPositionNameUnique(any(HrPosition.class))).thenReturn(existingPosition);

        boolean result = hrPositionService.checkPositionNameUnique(testPosition);

        assertFalse(result);
    }
}
