package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.Course;
import com.project.bean.Floors;
import com.project.bean.TestTemp;
import com.project.bean.Testinfo;
import com.project.service.CourseService;
import com.project.service.FloorsService;
import com.project.service.StudentinfoService;
import com.project.service.TestinfoService;
import com.project.util.Tool;

@Controller
public class GrphicController {
    @Autowired
    StudentinfoService studentinfoService;
    @Autowired
    CourseService courseService;
    @Autowired
    FloorsService floorsService;
    @Autowired
    TestinfoService testinfoService;

    //��ȡ�γ��������е�λ��
    public int getIndex(List<String> allCno, String cno) {
        for (int i = 0; i < allCno.size(); i++) {
            if (cno.equals(allCno.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @RequestMapping("grphic")
    public String grphic(ModelMap map) {
        List<String> sno = studentinfoService.findSno();
        List<String> allCno = studentinfoService.findCno();
        //a[][]����ÿ�е���������Ԫ�طŸö���Ķȣ�ÿ�е����ڶ���Ԫ�طŶ����ţ�ÿ�����һ��Ԫ�ط���ɫ�ı��
        int[][] array = new int[allCno.size() + 3][allCno.size() + 3];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 0;
            }

        }
        for (int i = 0; i < array.length - 3; i++) {
            array[i][allCno.size() + 1] = i;
        }
        for (String s : sno) {
            List<String> cno = studentinfoService.findCnoBySno(s);
            int index = -1;
            if (cno.size() == 1) {
                index = getIndex(allCno, cno.get(0));
                array[index][index] = 1;
            } else {
                for (int i = 0; i < cno.size() - 1; i++) {
                    int x = getIndex(allCno, cno.get(i));
                    for (int j = i + 1; j < cno.size() - 1; j++) {
                        int y = getIndex(allCno, cno.get(j));
                        array[x][y] = 1;
                        array[y][x] = 1;
                    }
                }
            }

        }
//		for (int i = 0; i < array.length; i++) {
//			for (int j = 0; j < array[i].length; j++) {
//				System.out.print(array[i][j]);
//			}
//			System.out.println();
//		}
        List<List<TestTemp>> test = getColor(array, allCno.size(), allCno);
        for (List<TestTemp> list : test) {
            for (TestTemp t : list) {
                Testinfo temp = new Testinfo();
                temp.setCno(t.getCno());
                temp.setAddr(t.getFloor().getFno() + "-" + t.getFloor().getFname());
                temp.setTimes(t.getDate());
                temp.setStartime(t.getStartTime());
                temp.setEndtime(t.getEndTime());
                testinfoService.addTestInfo(temp);
            }
        }
        map.put("test", test);
        return "testInfo";
    }

    public List<List<TestTemp>> getColor(int[][] array, int n, List<String> allCno) {
        int i;
        int j;
        int t = 0;
        //�����
        for (i = 0; i < n; i++) {
            int sum = 0;
            array[i][n] = i + 1;
            for (j = 0; j < n; j++)
                sum = sum + array[i][j];
            array[i][n] = sum;
        }

        //������
        for (i = 0; i < n - 1; i++)
            for (j = 0; j < n - 1 - i; j++)
                if (array[j][n] < array[j + 1][n]) {
                    int k;
                    //������
                    for (k = 0; k <= n + 1; k++)
                        t = array[j][k];
                    array[j][k] = array[j + 1][k];
                    array[j + 1][k] = t;
                    //������
                    for (k = 0; k < n; k++)
                        t = array[k][j];
                    array[k][j] = array[k][j + 1];
                    array[k][j + 1] = t;
                }
        //������ɫ��t����ɫ
        t = 0;
        for (i = 0; i < n; i++) {
            if (array[i][array[0].length - 1] == 0)//array[i][10]�ŵ�i���������ɫ
            {
                int m = 0, k;
                t++;
                array[i][10] = t;
                for (j = i + 1; j < n; j++) {
                    if (array[j][array[0].length - 1] != 0) continue;//����Ѿ���ɫ��������
                    for (k = i; k < j; k++)
                        if (array[j][k] == 1 && array[k][array[0].length - 1] == t) break;
                    if (k == j) array[j][array[0].length - 1] = t;
                }
            }
        }
        Map<Integer, List<String>> result = getCourseAndColor(array, allCno);
        Set<Integer> set = result.keySet();
        List<List<TestTemp>> test = new ArrayList<List<TestTemp>>();
        int index = 1;
        List<Floors> floors = floorsService.findFloors();
        int fsize = floors.size();
        int findex = 0;
        for (Integer x : set) {
            List<TestTemp> temp = new ArrayList<TestTemp>();
            for (String s : result.get(x)) {
                TestTemp testTemp = new TestTemp();
                String start = null;
                String end = null;
                Course course = courseService.findCourse(s);
                testTemp.setCno(s);
                testTemp.setCname(course.getCname());
                testTemp.setDate(Tool.getDate());
                if (index == 1) {
                    start = "08:00";
                    end = "10:00";
                } else if (index == 2) {
                    start = "10:30";
                    end = "12:30";
                } else if (index == 3) {
                    start = "13:30";
                    end = "15:30";
                } else if (index == 4) {
                    start = "16:00";
                    end = "18:30";
                    index = 1;
                }
                testTemp.setStartTime(start);
                testTemp.setEndTime(end);
                if (findex == fsize) {
                    findex = 0;
                }
                testTemp.setFloor(floors.get(findex++));
                temp.add(testTemp);
            }
            test.add(temp);
            index++;
        }
        return test;
        //�������array[][]����ÿ�е���������Ԫ�طŸö���Ķȣ�ÿ�е����ڶ���Ԫ�طŶ����ţ�ÿ�����һ��Ԫ�ط���ɫ�ı��
//	for(i=0;i<n;i++){
//		for(j=0;j<array[i].length;j++){
//			System.out.print(array[i][j]+"  ");
//		}
//		System.out.println();
//	}
    }

    public Map<Integer, List<String>> getCourseAndColor(int[][] array, List<String> allCno) {
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][array[i].length - 1] > max) {
                max = array[i][array[i].length - 1];
            }
        }
        for (int i = 0; i <= max; i++) {
            List<String> temp = new ArrayList<String>();
            for (int j = 0; j < array.length; j++) {
                if (array[j][array[j].length - 1] == i) {
                    System.out.println(array[j][allCno.size() + 1]);
                    temp.add(allCno.get(array[j][allCno.size() + 1]));
                }
            }
            if (temp.size() > 0) {
                map.put(i, temp);
            }
        }
        return map;
    }
}
