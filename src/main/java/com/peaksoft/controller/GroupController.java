package com.peaksoft.controller;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Groups;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupsService groupsService;
    private final CourseService courseService;

    @Autowired
    public GroupController(GroupsService groupsService, CourseService courseService) {
        this.groupsService = groupsService;
        this.courseService = courseService;
    }

    @ModelAttribute("coursesList")
    public List<Course> getAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping
    public String getAllGroups(Model model) {
        List<Groups> groups = groupsService.getAllGroups();
        model.addAttribute("group", groups);
        return "group/groups";
    }

    @GetMapping("/addGroup")
    public String addGroup(Model model) {
        model.addAttribute("group", new Groups());
        return "group/addGroup";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Groups groups) {
        groupsService.addGroups(groups, groups.getCourseId());
        return "redirect:/groups";
    }

    @GetMapping("/updateGroup/{id}")
    public String updateGroup(@PathVariable Long id, Model model) {
        Groups groups = groupsService.getGroupById(id);
        model.addAttribute("group", groups);
        return "group/updateGroup";
    }

    @PatchMapping("/saveUpdateGroup")
    public String saveUpdateGroup(@ModelAttribute("group") Groups groups) {
        groupsService.updateGroup(groups,groups.getCourseId()
        );
        return "redirect:/groups";
    }

    @DeleteMapping("/deleteGroup")
    public String deleteGroup(@RequestParam("groupId") Long id) {
        groupsService.deleteGroup(groupsService.getGroupById(id));
        return "redirect:/groups";
    }
}