package com.studentMNGSS.tooltable.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HrEmployee 实体类单元测试
 */
public class HrEmployeeTest {

    @Test
    void testGettersAndSetters() {
        HrEmployee employee = new HrEmployee();

        employee.setEmployeeId(1L);
        assertEquals(1L, employee.getEmployeeId());

        employee.setEmployeeCode("202501010101");
        assertEquals("202501010101", employee.getEmployeeCode());

        employee.setEmployeeName("张三");
        assertEquals("张三", employee.getEmployeeName());

        employee.setGender("0");
        assertEquals("0", employee.getGender());

        employee.setDeptIdFirst(1L);
        assertEquals(1L, employee.getDeptIdFirst());

        employee.setDeptNameFirst("总公司");
        assertEquals("总公司", employee.getDeptNameFirst());

        employee.setDeptIdSecond(2L);
        assertEquals(2L, employee.getDeptIdSecond());

        employee.setDeptNameSecond("研发中心");
        assertEquals("研发中心", employee.getDeptNameSecond());

        employee.setDeptIdThird(3L);
        assertEquals(3L, employee.getDeptIdThird());

        employee.setDeptNameThird("开发一部");
        assertEquals("开发一部", employee.getDeptNameThird());

        employee.setPositionId(100L);
        assertEquals(100L, employee.getPositionId());

        employee.setPositionName("软件工程师");
        assertEquals("软件工程师", employee.getPositionName());

        employee.setTitle("高级工程师");
        assertEquals("高级工程师", employee.getTitle());

        employee.setSalaryStandardId(200L);
        assertEquals(200L, employee.getSalaryStandardId());

        employee.setSalaryStandardName("标准薪酬A");
        assertEquals("标准薪酬A", employee.getSalaryStandardName());
    }

    @Test
    void testContactInfo() {
        HrEmployee employee = new HrEmployee();

        employee.setEmail("zhangsan@example.com");
        assertEquals("zhangsan@example.com", employee.getEmail());

        employee.setPhone("010-12345678");
        assertEquals("010-12345678", employee.getPhone());

        employee.setQq("123456789");
        assertEquals("123456789", employee.getQq());

        employee.setMobile("13800138000");
        assertEquals("13800138000", employee.getMobile());

        employee.setAddress("北京市朝阳区xxx");
        assertEquals("北京市朝阳区xxx", employee.getAddress());

        employee.setPostCode("100000");
        assertEquals("100000", employee.getPostCode());
    }

    @Test
    void testPersonalInfo() {
        HrEmployee employee = new HrEmployee();

        employee.setNationality("中国");
        assertEquals("中国", employee.getNationality());

        employee.setBirthplace("北京");
        assertEquals("北京", employee.getBirthplace());

        Date birthday = new Date();
        employee.setBirthday(birthday);
        assertEquals(birthday, employee.getBirthday());

        employee.setNation("汉族");
        assertEquals("汉族", employee.getNation());

        employee.setReligion("无");
        assertEquals("无", employee.getReligion());

        employee.setPoliticalStatus("群众");
        assertEquals("群众", employee.getPoliticalStatus());

        employee.setIdCard("110101199001011234");
        assertEquals("110101199001011234", employee.getIdCard());

        employee.setEducation("本科");
        assertEquals("本科", employee.getEducation());
    }

    @Test
    void testAttachments() {
        HrEmployee employee = new HrEmployee();

        employee.setPhoto("/uploads/photos/1.jpg");
        assertEquals("/uploads/photos/1.jpg", employee.getPhoto());

        employee.setResume("工作经历...");
        assertEquals("工作经历...", employee.getResume());

        employee.setFamilyInfo("家庭信息...");
        assertEquals("家庭信息...", employee.getFamilyInfo());
    }

    @Test
    void testStatusAndAudit() {
        HrEmployee employee = new HrEmployee();

        employee.setStatus("0");
        assertEquals("0", employee.getStatus());

        employee.setRegisterBy("admin");
        assertEquals("admin", employee.getRegisterBy());

        Date registerTime = new Date();
        employee.setRegisterTime(registerTime);
        assertEquals(registerTime, employee.getRegisterTime());

        employee.setReviewer("reviewer");
        assertEquals("reviewer", employee.getReviewer());

        Date reviewTime = new Date();
        employee.setReviewTime(reviewTime);
        assertEquals(reviewTime, employee.getReviewTime());

        employee.setDelFlag("0");
        assertEquals("0", employee.getDelFlag());
    }

    @Test
    void testToString() {
        HrEmployee employee = new HrEmployee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("张三");

        String result = employee.toString();

        assertNotNull(result);
        assertTrue(result.contains("employeeId"));
        assertTrue(result.contains("employeeName"));
    }

    @Test
    void testStatusValues() {
        HrEmployee employee = new HrEmployee();

        employee.setStatus("0");
        assertEquals("0", employee.getStatus());

        employee.setStatus("1");
        assertEquals("1", employee.getStatus());

        employee.setStatus("2");
        assertEquals("2", employee.getStatus());
    }

    @Test
    void testGenderValues() {
        HrEmployee employee = new HrEmployee();

        employee.setGender("0");
        assertEquals("0", employee.getGender());

        employee.setGender("1");
        assertEquals("1", employee.getGender());

        employee.setGender("2");
        assertEquals("2", employee.getGender());
    }
}
