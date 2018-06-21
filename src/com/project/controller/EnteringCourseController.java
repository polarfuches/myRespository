package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("enteringCourse")
public class EnteringCourseController {

    @RequestMapping("toEnteringCourse")
    public String toEnteringCourse() {
        System.out.println("--------------");

        return "infoManage/enteringCourse";
    }
}
