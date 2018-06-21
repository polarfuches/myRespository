package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Student;
import com.project.dao.StudentDao;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * ����ѧ�Ų���ѧ��
     *
     * @param sno
     * @return
     */
    public Student findStudentBySno(String sno) {
        return studentDao.findStudentByNo(sno);
    }

    /**
     * ���ѧ��
     *
     * @param student
     */
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }
}
