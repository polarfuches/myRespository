package com.project.dao;

import org.apache.ibatis.annotations.Param;

import com.project.bean.Student;

public interface StudentDao {
    /**
     * ����ѧ�Ų���ѧ��
     *
     * @param sno
     * @return
     */
    public Student findStudentByNo(@Param("sno") String sno);

    /**
     * ���ѧ��
     *
     * @param student
     */
    public void addStudent(@Param("student") Student student);
}
