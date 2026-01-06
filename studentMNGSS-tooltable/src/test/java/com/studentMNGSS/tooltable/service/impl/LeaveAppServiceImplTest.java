package com.studentMNGSS.tooltable.service.impl;

import com.studentMNGSS.tooltable.domain.LeaveApp;
import com.studentMNGSS.tooltable.mapper.LeaveAppMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * LeaveAppServiceImpl 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class LeaveAppServiceImplTest {

    @Mock
    private LeaveAppMapper leaveAppMapper;

    @InjectMocks
    private LeaveAppServiceImpl leaveAppService;

    private LeaveApp testLeaveApp;

    @BeforeEach
    void setUp() {
        testLeaveApp = new LeaveApp();
        testLeaveApp.setApplicationId(1L);
        testLeaveApp.setStudentId(100L);
        testLeaveApp.setLeaveType("1");
        testLeaveApp.setStartTime(new Date());
        testLeaveApp.setEndTime(new Date());
        testLeaveApp.setReason("家中有事");
        testLeaveApp.setStatus("0");
    }

    @Test
    void testSelectLeaveAppByApplicationId() {
        when(leaveAppMapper.selectLeaveAppByApplicationId(1L)).thenReturn(testLeaveApp);

        LeaveApp result = leaveAppService.selectLeaveAppByApplicationId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getApplicationId());
        assertEquals(100L, result.getStudentId());
        assertEquals("家中有事", result.getReason());
        verify(leaveAppMapper, times(1)).selectLeaveAppByApplicationId(1L);
    }

    @Test
    void testSelectLeaveAppByApplicationId_NotFound() {
        when(leaveAppMapper.selectLeaveAppByApplicationId(999L)).thenReturn(null);

        LeaveApp result = leaveAppService.selectLeaveAppByApplicationId(999L);

        assertNull(result);
    }

    @Test
    void testSelectLeaveAppList() {
        List<LeaveApp> apps = new ArrayList<>();
        apps.add(testLeaveApp);
        LeaveApp app2 = new LeaveApp();
        app2.setApplicationId(2L);
        app2.setReason("身体不适");
        apps.add(app2);

        when(leaveAppMapper.selectLeaveAppList(any(LeaveApp.class))).thenReturn(apps);

        List<LeaveApp> result = leaveAppService.selectLeaveAppList(new LeaveApp());

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSelectLeaveAppList_Empty() {
        when(leaveAppMapper.selectLeaveAppList(any(LeaveApp.class))).thenReturn(new ArrayList<>());

        List<LeaveApp> result = leaveAppService.selectLeaveAppList(new LeaveApp());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateLeaveApp() {
        when(leaveAppMapper.updateLeaveApp(any(LeaveApp.class))).thenReturn(1);

        testLeaveApp.setReason("家中有急事");
        int result = leaveAppService.updateLeaveApp(testLeaveApp);

        assertEquals(1, result);
        assertNotNull(testLeaveApp.getUpdateTime());
        verify(leaveAppMapper, times(1)).updateLeaveApp(any(LeaveApp.class));
    }

    @Test
    void testDeleteLeaveAppByApplicationIds() {
        Long[] ids = {1L, 2L, 3L};
        when(leaveAppMapper.deleteLeaveAppByApplicationIds(ids)).thenReturn(3);

        int result = leaveAppService.deleteLeaveAppByApplicationIds(ids);

        assertEquals(3, result);
        verify(leaveAppMapper, times(1)).deleteLeaveAppByApplicationIds(ids);
    }

    @Test
    void testDeleteLeaveAppByApplicationId() {
        when(leaveAppMapper.deleteLeaveAppByApplicationId(1L)).thenReturn(1);

        int result = leaveAppService.deleteLeaveAppByApplicationId(1L);

        assertEquals(1, result);
        verify(leaveAppMapper, times(1)).deleteLeaveAppByApplicationId(1L);
    }

    @Test
    void testSelectLeaveAppList_ByLeaveType() {
        List<LeaveApp> sickLeaves = new ArrayList<>();
        LeaveApp sickLeave = new LeaveApp();
        sickLeave.setApplicationId(3L);
        sickLeave.setLeaveType("1");
        sickLeave.setReason("发烧");
        sickLeaves.add(sickLeave);

        LeaveApp queryApp = new LeaveApp();
        queryApp.setLeaveType("1");

        when(leaveAppMapper.selectLeaveAppList(any(LeaveApp.class))).thenReturn(sickLeaves);

        List<LeaveApp> result = leaveAppService.selectLeaveAppList(queryApp);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getLeaveType());
    }

    @Test
    void testSelectLeaveAppList_ByStudentId() {
        List<LeaveApp> studentApps = new ArrayList<>();
        studentApps.add(testLeaveApp);

        LeaveApp queryApp = new LeaveApp();
        queryApp.setStudentId(100L);

        when(leaveAppMapper.selectLeaveAppList(any(LeaveApp.class))).thenReturn(studentApps);

        List<LeaveApp> result = leaveAppService.selectLeaveAppList(queryApp);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).getStudentId());
    }

    @Test
    void testSelectLeaveAppList_ByStatus() {
        List<LeaveApp> pendingApps = new ArrayList<>();
        testLeaveApp.setStatus("0");
        pendingApps.add(testLeaveApp);

        LeaveApp queryApp = new LeaveApp();
        queryApp.setStatus("0");

        when(leaveAppMapper.selectLeaveAppList(any(LeaveApp.class))).thenReturn(pendingApps);

        List<LeaveApp> result = leaveAppService.selectLeaveAppList(queryApp);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("0", result.get(0).getStatus());
    }
}
