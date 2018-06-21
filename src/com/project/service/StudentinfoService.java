package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Studentinfo;
import com.project.dao.StudentinfoDao;

@Service
public class StudentinfoService {
    @Autowired
    StudentinfoDao studentinfoDao;

    /**
     * ��Ӳ�������
     *
     * @param studentInfo
     */
    public void addStudentInfo(Studentinfo studentInfo) {
        studentinfoDao.addStudentInfo(studentInfo);
    }

    /**
     * ��ѯ����ѧ��ѧ��
     *
     * @return
     */
    public List<String> findSno() {
        return studentinfoDao.findSno();
    }

    public List<String> findCno() {
        return studentinfoDao.findCno();
    }

    public List<String> findCnoBySno(String sno) {
        return studentinfoDao.findCnoBySno(sno);
    }

}
