package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.bean.Studentinfo;

public interface StudentinfoDao {
    /**
     * ��Ӳ�������
     *
     * @param studentInfo
     */
    public void addStudentInfo(@Param("studentInfo") Studentinfo studentInfo);

    /**
     * ���Ҳ���ѧ��ѧ��
     *
     * @return
     */
    public List<String> findSno();

    /**
     * ���Ҳ����γ�
     *
     * @return
     */
    public List<String> findCno();

    /**
     * ����ѧ�Ų��Ҳ����Ŀγ�
     *
     * @param sno
     * @return
     */
    public List<String> findCnoBySno(@Param("sno") String sno);

}
