package com.peaksoft.dao.imp;

import com.peaksoft.dao.CompanyDao;
import com.peaksoft.dao.CourseDao;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
@Transactional
@Repository
public class CourseDaoImpl implements CourseDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final CompanyDao companyDao;
    @Autowired
    public CourseDaoImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }
    @Override
    public List<Course> getAllCourse() {
        List<Course>courses=entityManager.createQuery("from Course",Course.class).getResultList();
        Comparator<Course> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        courses.sort(comparator);
        return courses;
    }

    @Override
    public void addCourse(Course course,Long companyId) {
        Company company= companyDao.getCompanyById(companyId);
        course.setCompany(company);
        entityManager.persist(course);
    }

    @Override
    public Course getCourseById(Long id) {
        Course course;
        course=entityManager.find(Course.class,id);
        return course;
    }

    @Override
    public void updateCourse(Course course,Long companyId) {
        Company company = companyDao.getCompanyById(companyId);
        course.setCompany(company);
        entityManager.merge(course);

    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.remove(entityManager.contains(course)?course:entityManager.merge(course));
        }
}