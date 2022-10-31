package com.peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/teacher_info")
    public String getStudents(){
        return "/stud/students";
    }
    @GetMapping("/director_info")
    public String getDirector(){
        return "/hello";
    }
    @GetMapping("/admin_info")
    public String getAdmin(){
        return "/course/courses";
    }
    @GetMapping("techer_info")
    public String getTeacher(){
        return "/instructor/teachers";
    }
}




