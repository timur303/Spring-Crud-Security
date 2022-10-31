package com.peaksoft.service;

import com.peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    void addTeacher(Teacher teacher, Long courseId);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher,Long courseId);

    void deleteTeacher(Teacher teacher);
}
