package com.peaksoft.service;

import com.peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course>getAllCourse();
    void addCourse(Course course,Long companyId);
    Course getCourseById(Long id);
    void updateCourse(Course course, Long companyId );
    void deleteCourse(Course course);
}