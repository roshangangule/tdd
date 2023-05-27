package com.tdd.springmvc;

import com.tdd.springmvc.models.CollegeStudent;
import com.tdd.springmvc.repository.StudentDao;
import com.tdd.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGardeServiceTest {

    @Autowired
    StudentAndGradeService studentAndGradeService;

    @Autowired
    StudentDao studentDao;
    @DisplayName("Find By Email Address")
    @Test
    public void createStudentService() {
        studentAndGradeService.createStudent("Roshan", "Gangule", "roshan.gangule@tdd.com");

        CollegeStudent student = studentDao.findByEmailAddress("roshan.gangule@tdd.com");

        assertEquals("roshan.gangule@tdd.com", student.getEmailAddress(), "Find by email");
    }
}
