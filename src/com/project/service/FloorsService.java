package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Floors;
import com.project.dao.FloorsDao;

@Service
public class FloorsService {
    @Autowired
    FloorsDao floorsDao;

    public List<Floors> findFloors() {
        return floorsDao.findFloors();
    }
}
