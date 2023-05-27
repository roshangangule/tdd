package com.tdd.component;

import com.tdd.component.dao.ApplicationDao;
import com.tdd.component.models.CollegeStudent;
import com.tdd.component.models.Student;
import com.tdd.component.models.StudentGrades;
import com.tdd.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReflectionUtilsTest {
    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @MockBean
    ApplicationDao applicationDao;

    @Autowired
    ApplicationService applicationService;

    @BeforeEach
    public void beforeEach() {
        collegeStudent.setFirstname("Eric");
        collegeStudent.setLastname("Roby");
        collegeStudent.setEmailAddress("eric.roby@luv2code_school.com");
        collegeStudent.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(collegeStudent, "id", 1);
        ReflectionTestUtils.setField(collegeStudent, "studentGrades",
                new StudentGrades(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75))));
    }

    @Test
    public void getPrivateField() {
        assertEquals(1, ReflectionTestUtils.getField(collegeStudent, "id"));
    }

    @Test
    public void invokePrivateMethod() {
        assertEquals("Eric 1", ReflectionTestUtils.invokeMethod(collegeStudent, "getFirstNameAndId"));
    }

}
