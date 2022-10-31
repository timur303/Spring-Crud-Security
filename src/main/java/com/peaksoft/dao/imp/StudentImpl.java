package com.peaksoft.dao.imp;

import com.peaksoft.dao.GroupDAO;
import com.peaksoft.dao.StudentDAO;
import com.peaksoft.entity.Groups;
import com.peaksoft.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
@Repository
@Transactional
public class StudentImpl implements StudentDAO {

    private final GroupDAO groupDAO;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public StudentImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Student> getAllStudents() {
       List<Student> students = entityManager.createQuery("from Student", Student.class).getResultList();
        Comparator<Student>comparator=((o1, o2) ->(int)(o1.getId()-o2.getId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public void addStudent(Student student,Long groupId) {
        Groups groups = groupDAO.getGroupById(groupId);
        student.setGroups(groups);
        entityManager.persist(student);
    }

    @Override
    public Student getStudentById(Long id) {
       Student student = entityManager.find(Student.class,id);
        return student;
    }

    @Override
    public void updateStudent(Student student,Long groupId) {
        Groups groups = groupDAO.getGroupById(groupId);
        student.setGroups(groups);
        entityManager.merge(student);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student)?student:entityManager.merge(student));
    }
}
