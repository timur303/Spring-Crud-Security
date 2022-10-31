package com.peaksoft.dao;

import com.peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherDAO {

    List<Teacher> getAllTeachers();

    void addTeacher(Teacher teacher, Long courseId);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher,Long courseId);

    void deleteTeacher(Teacher teacher);
}
