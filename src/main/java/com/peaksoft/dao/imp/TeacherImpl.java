package com.peaksoft.dao.imp;

import com.peaksoft.dao.CourseDao;
import com.peaksoft.dao.TeacherDAO;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class TeacherImpl implements TeacherDAO {
    @PersistenceContext
    private  EntityManager entityManager;

    private final CourseDao courseDao;
    @Autowired
    public TeacherImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Teacher> getAllTeachers() {
      List<Teacher> teachers = entityManager.createQuery("from Teacher", Teacher.class).getResultList();
        Comparator<Teacher>comparator=((o1, o2) -> (int)(o1.getId()-o2.getId()));
        teachers.sort(comparator);
        return teachers;
    }

    @Override
    public void addTeacher(Teacher teacher,Long courseId) {
        Course course = courseDao.getCourseById(courseId);
        teacher.setCourse(course);
        entityManager.persist(teacher);

    }
    @Override
    public Teacher getTeacherById(Long id) {
        Teacher teacher;
        teacher=entityManager.find(Teacher.class,id);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher,Long courseId) {
        Course course = courseDao.getCourseById(courseId);
        teacher.setCourse(course);
        entityManager.merge(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
    entityManager.remove(entityManager.contains(teacher)? teacher:entityManager.merge(teacher));
    }
}
