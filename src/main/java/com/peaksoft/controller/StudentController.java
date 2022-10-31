package com.peaksoft.controller;

import com.peaksoft.entity.Groups;
import com.peaksoft.entity.Student;
import com.peaksoft.service.GroupsService;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GroupsService groupsService;
    @Autowired
    public StudentController(StudentService studentService, GroupsService groupsService) {
        this.studentService = studentService;
        this.groupsService = groupsService;
    }
    @ModelAttribute("groupList")
    public List<Groups> getAllGroups(){
        return groupsService.getAllGroups();
    }

    @GetMapping
    public String getAllStudent(Model model){
       List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "stud/students";
    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "stud/addStudent";
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.addStudent(student,student.getGroupsId());
        return "redirect:/students";
    }
    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "stud/updateStudent";
    }
    @PatchMapping("/saveUpdateStudent")
    public String saveUpdateStudent(@ModelAttribute("student")Student student){
        studentService.updateStudent(student,student.getGroupsId());
        return "redirect:/students";
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") Long id){
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/students";
    }
}
