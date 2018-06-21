package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.bean.Score;

public interface ScoreDao {
    public void addScore(@Param("score") Score score);

    public List<Score> findScore(@Param("sno") String sno);
}
