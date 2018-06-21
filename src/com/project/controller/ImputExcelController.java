package com.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.bean.Course;
import com.project.bean.Score;
import com.project.bean.Student;
import com.project.bean.Studentinfo;
import com.project.service.CourseService;
import com.project.service.ScoreService;
import com.project.service.StudentService;
import com.project.service.StudentinfoService;
import com.project.util.CommonUtil;


@Controller
@RequestMapping("imputExcel")
public class ImputExcelController {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    StudentinfoService studentinfoService;
    @Autowired
    ScoreService scoreService;

    @RequestMapping("toImportExcel")
    public String toImportExcel() {
        return "import";
    }

    @RequestMapping("uploadExcel")
    @ResponseBody
    public ModelAndView uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, String flag) {
        ModelAndView mav = new ModelAndView();
        String path = CommonUtil.getPath();
        String mkdirs = path + "\\DATA\\" + CommonUtil.getDateYMD();
        path = mkdirs + "\\" + CommonUtil.getDate() + "_" + file.getOriginalFilename();
        //保存到服务器
        if (!file.isEmpty()) {
            //创建文件夹
            File folder = new File(mkdirs);
            folder.mkdirs();
            //重命名并且存储excel表
            FileOutputStream fos = null;
            try {
                byte[] bytes = file.getBytes();
                fos = new FileOutputStream(path);
                fos.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        //把刚保存的文件读取出来，封装至JSON对象
        JSONArray array = new JSONArray();
        Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
        try {
            //构建Workbook对象, 只读Workbook对象
            //直接从本地文件创建Workbook
            InputStream fis = new FileInputStream(path);
            Workbook readwb = Workbook.getWorkbook(fis);
            //获取第一张Sheet表 ，Sheet的下标是从0开始
            Sheet readsheet = readwb.getSheet(0);
            //获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();
            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();
            //获取指定单元格的对象引用
            for (int i = 0; i < rsRows; i++) {
//					 JSONObject obj = new JSONObject();
                ArrayList<Object> list = new ArrayList<Object>();
                for (int j = 0; j < rsColumns; j++) {
                    Cell cell = readsheet.getCell(j, i);
//						System.out.print(cell.getContents()+"\t");
                    list.add(cell.getContents());
                }
//					 System.out.println();
                map.put(i, list);
                array.add(list);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(array.toString());
        mav.addObject("excelInfo", array.toString());
        mav.setViewName("importData");
        addData(map, flag);
        return mav;
    }

    public synchronized void addData(Map<Integer, List<Object>> map, String flag) {
        Set<Integer> set = map.keySet();
        if (flag.equals("student")) {
            for (Integer integer : set) {
                if (integer != 0) {
                    Student temp = new Student();
                    temp.setSno(map.get(integer).get(0).toString());
                    temp.setSname(map.get(integer).get(1).toString());
                    temp.setSpassw(map.get(integer).get(2).toString());
                    if (temp.getSno() != null) {
                        studentService.addStudent(temp);
                    }
                }

            }
        } else if (flag.equals("course")) {
            for (Integer integer : set) {
                if (integer != 0) {
                    Course temp = new Course();
                    temp.setCno(map.get(integer).get(0).toString());
                    temp.setCname(map.get(integer).get(1).toString());
                    if (temp.getCno() != null) {
                        courseService.addCourse(temp);
                    }
                }

            }
        } else if (flag.equals("studentinfo")) {
            for (Integer integer : set) {
                if (integer != 0) {
                    Studentinfo temp = new Studentinfo();
                    temp.setCno(map.get(integer).get(0).toString());
                    temp.setSno(map.get(integer).get(1).toString());
                    if (temp.getCno() != null) {
                        studentinfoService.addStudentInfo(temp);
                    }
                }

            }
        } else if (flag.equals("score")) {
            for (Integer integer : set) {
                if (integer != 0) {
                    Score temp = new Score();
                    temp.setCno(map.get(integer).get(0).toString());
                    temp.setSno(map.get(integer).get(1).toString());
                    temp.setScore(map.get(integer).get(2).toString());
                    if (temp.getCno() != null) {
                        scoreService.addScore(temp);
                    }
                }

            }
        }
    }
}
	

