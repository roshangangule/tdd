package com.tdd.springmvc.service;

import com.tdd.springmvc.models.CollegeStudent;
import com.tdd.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAndGradeService {

    @Autowired
    StudentDao studentDao;
    public void createStudent(String firstName, String lastName, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstName, lastName, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }
}
