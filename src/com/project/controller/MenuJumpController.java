package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.Course;
import com.project.bean.Score;
import com.project.bean.TestTemp;
import com.project.bean.Testinfo;
import com.project.service.CourseService;
import com.project.service.ScoreService;
import com.project.service.StudentinfoService;
import com.project.service.TestinfoService;

@Controller
public class MenuJumpController {
    @Autowired
    StudentinfoService studentinfoService;
    @Autowired
    CourseService courseService;
    @Autowired
    TestinfoService testinfoService;
    @Autowired
    ScoreService scoreService;

    public List<Testinfo> toScore(String sno) {
        List<Score> score = scoreService.findScore(sno);
        List<Testinfo> list = new ArrayList<Testinfo>();
        if (score != null) {
            for (Score s : score) {
                Course temp = courseService.findCourse(s.getCno());
                Testinfo test = testinfoService.findTestInfo(s.getCno());
                test.setCname(temp.getCname());
                test.setScore(s.getScore());
                list.add(test);
            }
        }
        return list;
    }

    public List<Testinfo> toSerch(String sno) {
        List<String> cno = studentinfoService.findCnoBySno(sno);
        List<Testinfo> list = new ArrayList<Testinfo>();
        if (cno != null) {
            for (String s : cno) {
                System.out.println(s);
                Course temp = courseService.findCourse(s);
                Testinfo test = testinfoService.findTestInfo(s);
                test.setCname(temp.getCname());
                list.add(test);
            }
        }
        return list;
    }

    @RequestMapping("menuJump")
    public String menuJump(String flag, String sno, ModelMap map) {
        if (flag.equals("score")) {
            List<Testinfo> list = toScore(sno);
            map.put("test", list);
            return "serch";
        } else if (flag.equals("serch")) {
            List<Testinfo> list = toSerch(sno);
            map.put("test", list);
            return "serch";
        } else if (flag.equals("import")) {
            return "import";
        } else if (flag.equals("request")) {
            return "request";
        }
        return "error";
    }
}
