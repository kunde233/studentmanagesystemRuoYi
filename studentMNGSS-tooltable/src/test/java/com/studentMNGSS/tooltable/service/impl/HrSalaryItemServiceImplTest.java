package com.studentMNGSS.tooltable.service.impl;

import com.studentMNGSS.tooltable.domain.HrSalaryItem;
import com.studentMNGSS.tooltable.mapper.HrSalaryItemMapper;
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
 * HrSalaryItemServiceImpl 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class HrSalaryItemServiceImplTest {

    @Mock
    private HrSalaryItemMapper hrSalaryItemMapper;

    @InjectMocks
    private HrSalaryItemServiceImpl hrSalaryItemService;

    private HrSalaryItem testSalaryItem;

    @BeforeEach
    void setUp() {
        testSalaryItem = new HrSalaryItem();
        testSalaryItem.setItemId(1L);
        testSalaryItem.setItemCode("SI001");
        testSalaryItem.setItemName("基本工资");
        testSalaryItem.setItemType("0");
        testSalaryItem.setItemSort(1);
        testSalaryItem.setStatus("0");
    }

    @Test
    void testSelectHrSalaryItemByItemId() {
        when(hrSalaryItemMapper.selectHrSalaryItemByItemId(1L)).thenReturn(testSalaryItem);

        HrSalaryItem result = hrSalaryItemService.selectHrSalaryItemByItemId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getItemId());
        assertEquals("SI001", result.getItemCode());
        assertEquals("基本工资", result.getItemName());
        verify(hrSalaryItemMapper, times(1)).selectHrSalaryItemByItemId(1L);
    }

    @Test
    void testSelectHrSalaryItemByItemId_NotFound() {
        when(hrSalaryItemMapper.selectHrSalaryItemByItemId(999L)).thenReturn(null);

        HrSalaryItem result = hrSalaryItemService.selectHrSalaryItemByItemId(999L);

        assertNull(result);
    }

    @Test
    void testSelectHrSalaryItemList() {
        List<HrSalaryItem> items = new ArrayList<>();
        items.add(testSalaryItem);
        HrSalaryItem item2 = new HrSalaryItem();
        item2.setItemId(2L);
        item2.setItemName("绩效奖金");
        items.add(item2);

        when(hrSalaryItemMapper.selectHrSalaryItemList(any(HrSalaryItem.class))).thenReturn(items);

        List<HrSalaryItem> result = hrSalaryItemService.selectHrSalaryItemList(new HrSalaryItem());

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSelectHrSalaryItemList_Empty() {
        when(hrSalaryItemMapper.selectHrSalaryItemList(any(HrSalaryItem.class))).thenReturn(new ArrayList<>());

        List<HrSalaryItem> result = hrSalaryItemService.selectHrSalaryItemList(new HrSalaryItem());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSelectHrSalaryItemAll() {
        List<HrSalaryItem> items = new ArrayList<>();
        items.add(testSalaryItem);
        HrSalaryItem item2 = new HrSalaryItem();
        item2.setItemId(2L);
        item2.setItemName("社保扣除");
        item2.setItemType("1");
        items.add(item2);

        when(hrSalaryItemMapper.selectHrSalaryItemAll()).thenReturn(items);

        List<HrSalaryItem> result = hrSalaryItemService.selectHrSalaryItemAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testInsertHrSalaryItem() {
        when(hrSalaryItemMapper.insertHrSalaryItem(any(HrSalaryItem.class))).thenReturn(1);

        HrSalaryItem newItem = new HrSalaryItem();
        newItem.setItemCode("SI002");
        newItem.setItemName("岗位津贴");

        int result = hrSalaryItemService.insertHrSalaryItem(newItem);

        assertEquals(1, result);
        assertNotNull(newItem.getCreateTime());
        verify(hrSalaryItemMapper, times(1)).insertHrSalaryItem(any(HrSalaryItem.class));
    }

    @Test
    void testUpdateHrSalaryItem() {
        when(hrSalaryItemMapper.updateHrSalaryItem(any(HrSalaryItem.class))).thenReturn(1);

        testSalaryItem.setItemName("基本薪资");
        int result = hrSalaryItemService.updateHrSalaryItem(testSalaryItem);

        assertEquals(1, result);
        assertNotNull(testSalaryItem.getUpdateTime());
        verify(hrSalaryItemMapper, times(1)).updateHrSalaryItem(any(HrSalaryItem.class));
    }

    @Test
    void testDeleteHrSalaryItemByItemIds() {
        Long[] ids = {1L, 2L, 3L};
        when(hrSalaryItemMapper.deleteHrSalaryItemByItemIds(ids)).thenReturn(3);

        int result = hrSalaryItemService.deleteHrSalaryItemByItemIds(ids);

        assertEquals(3, result);
        verify(hrSalaryItemMapper, times(1)).deleteHrSalaryItemByItemIds(ids);
    }

    @Test
    void testDeleteHrSalaryItemByItemId() {
        when(hrSalaryItemMapper.deleteHrSalaryItemByItemId(1L)).thenReturn(1);

        int result = hrSalaryItemService.deleteHrSalaryItemByItemId(1L);

        assertEquals(1, result);
        verify(hrSalaryItemMapper, times(1)).deleteHrSalaryItemByItemId(1L);
    }

    @Test
    void testCheckItemCodeUnique_Unique() {
        when(hrSalaryItemMapper.checkItemCodeUnique("SI001")).thenReturn(null);

        boolean result = hrSalaryItemService.checkItemCodeUnique(testSalaryItem);

        assertTrue(result);
    }

    @Test
    void testCheckItemCodeUnique_SameItem() {
        HrSalaryItem existingItem = new HrSalaryItem();
        existingItem.setItemId(1L);
        existingItem.setItemCode("SI001");

        when(hrSalaryItemMapper.checkItemCodeUnique("SI001")).thenReturn(existingItem);

        boolean result = hrSalaryItemService.checkItemCodeUnique(testSalaryItem);

        assertTrue(result);
    }

    @Test
    void testCheckItemCodeUnique_NotUnique() {
        HrSalaryItem existingItem = new HrSalaryItem();
        existingItem.setItemId(2L);
        existingItem.setItemCode("SI001");

        when(hrSalaryItemMapper.checkItemCodeUnique("SI001")).thenReturn(existingItem);

        boolean result = hrSalaryItemService.checkItemCodeUnique(testSalaryItem);

        assertFalse(result);
    }

    @Test
    void testCheckItemCodeUnique_NewItem() {
        HrSalaryItem newItem = new HrSalaryItem();
        newItem.setItemCode("SI003");

        when(hrSalaryItemMapper.checkItemCodeUnique("SI003")).thenReturn(null);

        boolean result = hrSalaryItemService.checkItemCodeUnique(newItem);

        assertTrue(result);
    }

    @Test
    void testSelectHrSalaryItemList_ByItemType() {
        List<HrSalaryItem> incomeItems = new ArrayList<>();
        testSalaryItem.setItemType("0");
        incomeItems.add(testSalaryItem);

        HrSalaryItem queryItem = new HrSalaryItem();
        queryItem.setItemType("0");

        when(hrSalaryItemMapper.selectHrSalaryItemList(any(HrSalaryItem.class))).thenReturn(incomeItems);

        List<HrSalaryItem> result = hrSalaryItemService.selectHrSalaryItemList(queryItem);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("0", result.get(0).getItemType());
    }

    @Test
    void testSelectHrSalaryItemList_DeductionType() {
        List<HrSalaryItem> deductionItems = new ArrayList<>();
        HrSalaryItem deduction = new HrSalaryItem();
        deduction.setItemId(3L);
        deduction.setItemCode("SI003");
        deduction.setItemName("个人所得税");
        deduction.setItemType("1");
        deductionItems.add(deduction);

        HrSalaryItem queryItem = new HrSalaryItem();
        queryItem.setItemType("1");

        when(hrSalaryItemMapper.selectHrSalaryItemList(any(HrSalaryItem.class))).thenReturn(deductionItems);

        List<HrSalaryItem> result = hrSalaryItemService.selectHrSalaryItemList(queryItem);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getItemType());
    }
}
