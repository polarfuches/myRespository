package com.project.dao;

import java.util.List;

import com.project.bean.Floors;

public interface FloorsDao {
    /**
     * ��ѯ����
     *
     * @return
     */
    public List<Floors> findFloors();
}
