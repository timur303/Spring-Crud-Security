package com.peaksoft.dao.imp;

import com.peaksoft.dao.CourseDao;
import com.peaksoft.dao.GroupDAO;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Repository
@Transactional
public class GroupImpl implements GroupDAO {
   @PersistenceContext
   private EntityManager entityManager;

   private final CourseDao courseDao;

   @Autowired
    public GroupImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Groups> getAllGroups() {
        List<Groups> groups = entityManager.createQuery("from Groups", Groups.class).getResultList();
        Comparator<Groups> comparator =((o1, o2) -> (int)(o1.getId()-o2.getId()));
        groups.sort(comparator);
        return groups;
    }

    @Override
    public void addGroups(Groups group,Long courseId) {
        Course course = courseDao.getCourseById(courseId);
        group.setCourse(Collections.singletonList(course));
        entityManager.persist(group);
    }

    @Override
    public Groups getGroupById(Long id) {
        return entityManager.find(Groups.class,id);
    }

    @Override
    public void updateGroup(Groups group, Long courseId) {
       Course course = courseDao.getCourseById(courseId);
       group.setCourse(Collections.singletonList(((course))));
        entityManager.merge(group);
    }

    @Override
    public void deleteGroup(Groups group) {
    entityManager.remove(entityManager.contains(group)?group:entityManager.merge(group));
    }
}
