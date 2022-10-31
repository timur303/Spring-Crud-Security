package com.peaksoft.controller;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Teacher;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }
    @ModelAttribute("coursesList")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping
    private String getAllTeachers(Model model){
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teacher",teachers);
        return "instructor/teachers";
    }

    @GetMapping("/addTeacher")
    public String addTeacher(Model model){
        model.addAttribute("teacher",new Teacher());
        return "instructor/addTeacher";
    }
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher")Teacher teacher){
        teacherService.addTeacher(teacher,teacher.getCourseId());
        return "redirect:/teachers";
    }
    @GetMapping("/updateTeacher/{id}")
    public String updateTeacher(@PathVariable Long id, Model model){
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "instructor/updateTeacher";
    }
    @PatchMapping("/saveUpdateTeacher")
    public String saveUpdateTeacher(@ModelAttribute("teacher")Teacher teacher){
        teacherService.updateTeacher(teacher,teacher.getCourseId());
            return "redirect:/teachers";
    }
    @DeleteMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("teacherId") Long id){
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/teachers";
    }
}
