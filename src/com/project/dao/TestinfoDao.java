package com.project.dao;

import org.apache.ibatis.annotations.Param;

import com.project.bean.Testinfo;

public interface TestinfoDao {
    public void addTestInfo(@Param("testInfo") Testinfo testInfo);

    public Testinfo findTestInfo(@Param("cno") String cno);
}
