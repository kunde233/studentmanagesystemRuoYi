package com.studentMNGSS.tooltable.service.impl;

import com.studentMNGSS.tooltable.domain.HrEmployee;
import com.studentMNGSS.tooltable.mapper.HrEmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * HrEmployeeServiceImpl 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class HrEmployeeServiceImplTest {

    @Mock
    private HrEmployeeMapper hrEmployeeMapper;

    @InjectMocks
    private HrEmployeeServiceImpl hrEmployeeService;

    private HrEmployee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new HrEmployee();
        testEmployee.setEmployeeId(1L);
        testEmployee.setEmployeeName("张三");
        testEmployee.setEmployeeCode("202501010101");
        testEmployee.setGender("0");
        testEmployee.setIdCard("110101199001011234");
        testEmployee.setDeptIdFirst(1L);
        testEmployee.setDeptIdSecond(2L);
        testEmployee.setDeptIdThird(3L);
        testEmployee.setStatus("0");
    }

    @Test
    void testSelectHrEmployeeByEmployeeId() {
        when(hrEmployeeMapper.selectHrEmployeeByEmployeeId(1L)).thenReturn(testEmployee);

        HrEmployee result = hrEmployeeService.selectHrEmployeeByEmployeeId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getEmployeeId());
        assertEquals("张三", result.getEmployeeName());
        verify(hrEmployeeMapper, times(1)).selectHrEmployeeByEmployeeId(1L);
    }

    @Test
    void testSelectHrEmployeeByEmployeeId_NotFound() {
        when(hrEmployeeMapper.selectHrEmployeeByEmployeeId(999L)).thenReturn(null);

        HrEmployee result = hrEmployeeService.selectHrEmployeeByEmployeeId(999L);

        assertNull(result);
        verify(hrEmployeeMapper, times(1)).selectHrEmployeeByEmployeeId(999L);
    }

    @Test
    void testSelectHrEmployeeList() {
        List<HrEmployee> employees = new ArrayList<>();
        employees.add(testEmployee);
        HrEmployee employee2 = new HrEmployee();
        employee2.setEmployeeId(2L);
        employee2.setEmployeeName("李四");
        employees.add(employee2);

        when(hrEmployeeMapper.selectHrEmployeeList(any(HrEmployee.class))).thenReturn(employees);

        List<HrEmployee> result = hrEmployeeService.selectHrEmployeeList(new HrEmployee());

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(hrEmployeeMapper, times(1)).selectHrEmployeeList(any(HrEmployee.class));
    }

    @Test
    void testSelectHrEmployeeList_Empty() {
        when(hrEmployeeMapper.selectHrEmployeeList(any(HrEmployee.class))).thenReturn(new ArrayList<>());

        List<HrEmployee> result = hrEmployeeService.selectHrEmployeeList(new HrEmployee());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSelectHrEmployeePendingReviewList() {
        List<HrEmployee> pendingList = new ArrayList<>();
        testEmployee.setStatus("0");
        pendingList.add(testEmployee);

        when(hrEmployeeMapper.selectHrEmployeePendingReviewList(any(HrEmployee.class))).thenReturn(pendingList);

        List<HrEmployee> result = hrEmployeeService.selectHrEmployeePendingReviewList(new HrEmployee());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("0", result.get(0).getStatus());
    }

    @Test
    void testInsertHrEmployee() {
        when(hrEmployeeMapper.selectMaxEmployeeCode(anyString())).thenReturn(null);
        when(hrEmployeeMapper.insertHrEmployee(any(HrEmployee.class))).thenReturn(1);

        HrEmployee newEmployee = new HrEmployee();
        newEmployee.setEmployeeName("王五");
        newEmployee.setDeptIdFirst(1L);
        newEmployee.setDeptIdSecond(2L);
        newEmployee.setDeptIdThird(3L);

        int result = hrEmployeeService.insertHrEmployee(newEmployee, "admin");

        assertEquals(1, result);
        assertEquals("0", newEmployee.getStatus());
        assertEquals("admin", newEmployee.getRegisterBy());
        assertNotNull(newEmployee.getEmployeeCode());
        verify(hrEmployeeMapper, times(1)).insertHrEmployee(any(HrEmployee.class));
    }

    @Test
    void testInsertHrEmployee_WithExistingCode() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String prefix = year + "010203";
        when(hrEmployeeMapper.selectMaxEmployeeCode(prefix)).thenReturn(prefix + "05");
        when(hrEmployeeMapper.insertHrEmployee(any(HrEmployee.class))).thenReturn(1);

        HrEmployee newEmployee = new HrEmployee();
        newEmployee.setEmployeeName("赵六");
        newEmployee.setDeptIdFirst(1L);
        newEmployee.setDeptIdSecond(2L);
        newEmployee.setDeptIdThird(3L);

        int result = hrEmployeeService.insertHrEmployee(newEmployee, "admin");

        assertEquals(1, result);
        assertTrue(newEmployee.getEmployeeCode().endsWith("06"));
    }

    @Test
    void testUpdateHrEmployee() {
        when(hrEmployeeMapper.updateHrEmployee(any(HrEmployee.class))).thenReturn(1);

        testEmployee.setEmployeeName("张三改");
        int result = hrEmployeeService.updateHrEmployee(testEmployee);

        assertEquals(1, result);
        assertNotNull(testEmployee.getUpdateTime());
        verify(hrEmployeeMapper, times(1)).updateHrEmployee(any(HrEmployee.class));
    }

    @Test
    void testReviewHrEmployee() {
        when(hrEmployeeMapper.updateHrEmployee(any(HrEmployee.class))).thenReturn(1);

        int result = hrEmployeeService.reviewHrEmployee(testEmployee, "reviewer_admin");

        assertEquals(1, result);
        assertEquals("1", testEmployee.getStatus());
        assertEquals("reviewer_admin", testEmployee.getReviewer());
        assertNotNull(testEmployee.getReviewTime());
    }

    @Test
    void testDeleteHrEmployeeByEmployeeIds() {
        Long[] ids = {1L, 2L, 3L};
        when(hrEmployeeMapper.deleteHrEmployeeByEmployeeIds(ids)).thenReturn(3);

        int result = hrEmployeeService.deleteHrEmployeeByEmployeeIds(ids);

        assertEquals(3, result);
        verify(hrEmployeeMapper, times(1)).deleteHrEmployeeByEmployeeIds(ids);
    }

    @Test
    void testDeleteHrEmployeeByEmployeeId() {
        when(hrEmployeeMapper.deleteHrEmployeeByEmployeeId(1L)).thenReturn(1);

        int result = hrEmployeeService.deleteHrEmployeeByEmployeeId(1L);

        assertEquals(1, result);
        verify(hrEmployeeMapper, times(1)).deleteHrEmployeeByEmployeeId(1L);
    }

    @Test
    void testRestoreHrEmployee() {
        when(hrEmployeeMapper.updateHrEmployee(any(HrEmployee.class))).thenReturn(1);

        int result = hrEmployeeService.restoreHrEmployee(1L);

        assertEquals(1, result);
        verify(hrEmployeeMapper, times(1)).updateHrEmployee(argThat(emp ->
            emp.getEmployeeId().equals(1L) &&
            "1".equals(emp.getStatus()) &&
            "0".equals(emp.getDelFlag())
        ));
    }

    @Test
    void testCheckIdCardUnique_Unique() {
        when(hrEmployeeMapper.checkIdCardUnique("110101199001011234")).thenReturn(null);

        boolean result = hrEmployeeService.checkIdCardUnique(testEmployee);

        assertTrue(result);
    }

    @Test
    void testCheckIdCardUnique_SameEmployee() {
        HrEmployee existingEmployee = new HrEmployee();
        existingEmployee.setEmployeeId(1L);
        existingEmployee.setIdCard("110101199001011234");

        when(hrEmployeeMapper.checkIdCardUnique("110101199001011234")).thenReturn(existingEmployee);

        boolean result = hrEmployeeService.checkIdCardUnique(testEmployee);

        assertTrue(result);
    }

    @Test
    void testCheckIdCardUnique_NotUnique() {
        HrEmployee existingEmployee = new HrEmployee();
        existingEmployee.setEmployeeId(2L);
        existingEmployee.setIdCard("110101199001011234");

        when(hrEmployeeMapper.checkIdCardUnique("110101199001011234")).thenReturn(existingEmployee);

        boolean result = hrEmployeeService.checkIdCardUnique(testEmployee);

        assertFalse(result);
    }

    @Test
    void testCheckIdCardUnique_NewEmployee() {
        HrEmployee newEmployee = new HrEmployee();
        newEmployee.setIdCard("110101199001015678");

        when(hrEmployeeMapper.checkIdCardUnique("110101199001015678")).thenReturn(null);

        boolean result = hrEmployeeService.checkIdCardUnique(newEmployee);

        assertTrue(result);
    }

    @Test
    void testGenerateEmployeeCode_FirstEmployee() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String prefix = year + "010203";
        when(hrEmployeeMapper.selectMaxEmployeeCode(prefix)).thenReturn(null);

        String code = hrEmployeeService.generateEmployeeCode(1L, 2L, 3L);

        assertEquals(prefix + "01", code);
    }

    @Test
    void testGenerateEmployeeCode_WithExistingEmployees() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String prefix = year + "010203";
        when(hrEmployeeMapper.selectMaxEmployeeCode(prefix)).thenReturn(prefix + "10");

        String code = hrEmployeeService.generateEmployeeCode(1L, 2L, 3L);

        assertEquals(prefix + "11", code);
    }

    @Test
    void testGenerateEmployeeCode_LargeDeptIds() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String prefix = year + "991299";
        when(hrEmployeeMapper.selectMaxEmployeeCode(prefix)).thenReturn(null);

        String code = hrEmployeeService.generateEmployeeCode(199L, 112L, 99L);

        assertEquals(prefix + "01", code);
    }

    @Test
    void testGenerateEmployeeCode_InvalidMaxCode() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String prefix = year + "010203";
        when(hrEmployeeMapper.selectMaxEmployeeCode(prefix)).thenReturn("invalid");

        String code = hrEmployeeService.generateEmployeeCode(1L, 2L, 3L);

        assertEquals(prefix + "01", code);
    }
}
