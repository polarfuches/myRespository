package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.bean.Student;
import com.project.bean.Teacher;
import com.project.service.StudentService;

@Controller
@SessionAttributes("user")
public class InitContorller {
    @Autowired
    private StudentService studentService;

    @RequestMapping("loginJump")
    public String jumpLogin() {
        return "login";
    }

    @RequestMapping("exit")
    public String exit(ModelMap map) {
        map.remove("user");
        return "login";
    }

    @RequestMapping("login")
    public String login(String userId, String password, int mark, ModelMap map) {
        if (mark == 1) {
            if (!userId.equals("000000000")) {
                map.put("error", "���Ŵ���!");
                return "login";
            } else {
                if (password.equals("admin")) {
                    Student student = studentService.findStudentBySno(userId);
                    student.setMark(1);
                    map.put("user", student);
                    map.put("flag", "1");
                    return "main";
                } else {
                    map.put("error", "�������!");
                    return "login";
                }
            }
        } else if (mark == 0) {
            if (userId.equals("000000000")) {
                map.put("error", "ѧ�Ŵ���!");
            } else {
                Student student = studentService.findStudentBySno(userId);
                if (student == null) {
                    map.put("error", "ѧ�Ŵ���!");
                    return "login";
                } else {
                    if (password.equals(student.getSpassw())) {
                        map.put("user", student);
                        student.setMark(0);
                        map.put("flag", 0);
                        return "main";
                    } else {
                        map.put("error", "�������!");
                        return "login";
                    }
                }
            }
        }
        return "login";
    }
}
