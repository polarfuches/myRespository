package com.project.bean;

import java.util.ArrayList;

public class MyAdjGraphic {
    static final int maxWeight = -1; //����������֮��û�бߣ�ȨֵΪ-1��
    ArrayList vertices = new ArrayList();//��Ž��ļ���
    int[][] edges; //�ڽӾ���Ķ�ά����
    int numOfEdges; //�ߵ�����

    public MyAdjGraphic(int n) {
        edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) //�Խ����ϵ�Ԫ��Ϊ0
                {
                    edges[i][j] = 0;
                } else {
                    edges[i][j] = maxWeight;
                }
            }
        }
        numOfEdges = 0;
    }

    //���رߵ�����
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    //���ؽ�������
    public int getNumOfVertice() {
        return this.vertices.size();
    }

    //���ؽ���ֵ
    public Object getValueOfVertice(int index) {
        return this.vertices.get(index);
    }

    //���ĳ���ߵ�Ȩֵ
    public int getWeightOfEdges(int v1, int v2) throws Exception {
        if ((v1 < 0 || v1 >= vertices.size()) || (v2 < 0 || v2 >= vertices.size())) {
            throw new Exception("v1����v2����Խ�����");
        }
        return this.edges[v1][v2];

    }

    //������
    public void insertVertice(Object obj) {
        this.vertices.add(obj);
    }

    //�����Ȩֵ�ı�
    public void insertEdges(int v1, int v2, int weight) throws Exception {
        if ((v1 < 0 || v1 >= vertices.size()) || (v2 < 0 || v2 >= vertices.size())) {
            throw new Exception("v1����v2����Խ�����");
        }

        this.edges[v1][v2] = weight;
        this.numOfEdges++;
    }

    //ɾ��ĳ����
    public void deleteEdges(int v1, int v2) throws Exception {
        if ((v1 < 0 || v1 >= vertices.size()) || (v2 < 0 || v2 >= vertices.size())) {
            throw new Exception("v1����v2����Խ�����");
        }
        if (v1 == v2 || this.edges[v1][v2] == maxWeight)//�Լ����Լ��ı߻��߲߱���������ɾ����
        {
            throw new Exception("�߲����ڣ�");
        }

        this.edges[v1][v2] = maxWeight;
        this.numOfEdges--;
    }

    //��ӡ�ڽӾ���
    public void print() {
        for (int i = 0; i < this.edges.length; i++) {
            for (int j = 0; j < this.edges[i].length; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
    }
}

