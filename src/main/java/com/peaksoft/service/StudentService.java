package com.peaksoft.service;

import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    void addStudent(Student student, Long groupId);

    Student getStudentById(Long id);

    void updateStudent(Student student,Long groupId);

    void deleteStudent(Student student);
}
