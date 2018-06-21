package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Testinfo;
import com.project.dao.TestinfoDao;

@Service
public class TestinfoService {
    @Autowired
    TestinfoDao testinfoDao;

    public void addTestInfo(Testinfo testInfo) {
        testinfoDao.addTestInfo(testInfo);
    }

    public Testinfo findTestInfo(String cno) {
        return testinfoDao.findTestInfo(cno);
    }
}
