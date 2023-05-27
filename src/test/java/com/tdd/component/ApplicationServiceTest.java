package com.tdd.component;

import com.tdd.component.dao.ApplicationDao;
import com.tdd.component.models.CollegeStudent;
import com.tdd.component.models.Student;
import com.tdd.component.models.StudentGrades;
import com.tdd.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    Student student;

    @Autowired
    StudentGrades studentGrades;

    @MockBean
    ApplicationDao applicationDao;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        collegeStudent.setFirstname("Eric");
        collegeStudent.setLastname("Roby");
        collegeStudent.setEmailAddress("eric.roby@luv2code_school.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @DisplayName("When & Verify")
    @Test
    public void addGradeResultsForStudentTest() {
        when(applicationDao.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()))
                .thenReturn(100.00);
        assertEquals(100.00, applicationService.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
        verify(applicationDao,times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }
    @DisplayName("find GPA")
    @Test
    public void findPointAvgTest() {
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
                .thenReturn(88.31);
        assertEquals(88.31,applicationService.findGradePointAverage(studentGrades.getMathGradeResults()));
        verify(applicationDao, times(1)).findGradePointAverage(studentGrades.getMathGradeResults());
    }
    @DisplayName("Check Not Null")
    @Test
    public void checkNotNull() {
        when(applicationDao.checkNull(collegeStudent)).thenReturn(true);
        assertNotNull(applicationService.checkNull(collegeStudent));
    }
    @DisplayName("Throw Exception")
    @Test
    public void throwRuntimeError() {
        CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

        doThrow(new RuntimeException()).when(applicationDao).checkNull(nullStudent);

        assertThrows(RuntimeException.class, () -> {
            applicationService.checkNull(nullStudent);
        });
        verify(applicationDao, times(1)).checkNull(nullStudent);
    }

    @DisplayName("Multiple Stubbing")
    @Test
    public void stubbingConsecutiveCalls() {
        CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

        when(applicationDao.checkNull(nullStudent))
                .thenThrow(new RuntimeException())
                .thenReturn("Do not throw exception second time");

        assertThrows(RuntimeException.class, () -> {
            applicationService.checkNull(nullStudent);
        });

        assertEquals("Do not throw exception second time",
                applicationService.checkNull(nullStudent));

        verify(applicationDao, times(2)).checkNull(nullStudent);
    }
}
