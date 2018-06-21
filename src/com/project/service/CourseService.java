package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Course;
import com.project.dao.CourseDao;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    /**
     * ���γ̺Ų��ҿγ�
     *
     * @param cno
     * @return
     */
    public Course findCourse(String cno) {
        return courseDao.findCourse(cno);
    }

    /**
     * ��ӿγ�
     *
     * @param course
     */
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }
}
