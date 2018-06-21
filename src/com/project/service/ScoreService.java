package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Score;
import com.project.dao.ScoreDao;

@Service
public class ScoreService {
    @Autowired
    ScoreDao scoreDao;

    public void addScore(Score score) {
        scoreDao.addScore(score);
    }

    public List<Score> findScore(String sno) {
        return scoreDao.findScore(sno);
    }
}
