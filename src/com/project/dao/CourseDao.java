package com.project.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.project.bean.Course;

public interface CourseDao {
    /**
     * ���ҿγ�
     *
     * @return
     */
    public Course findCourse(@Param("cno") String cno);

    /**
     * ���ҿγ�����
     *
     * @return
     */
    public int findCourseCount();

    /**
     * ��ӿγ�
     *
     * @param course
     */
    public void addCourse(@Param("course") Course course);
}
