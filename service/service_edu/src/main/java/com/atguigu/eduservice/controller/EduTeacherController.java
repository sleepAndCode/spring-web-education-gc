package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-29
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //访问地址 localhost:8001/eduservice/teacher/findall
    @Autowired
    private EduTeacherService eduTeacherService;

    //1 查询讲师表所有的数据
    //rest风格
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeaacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    //2逻辑删除讲师方法  id需要通过路径传递
    @DeleteMapping("{id}")
    public Boolean removeTeacher(@PathVariable String id){
        boolean falg = eduTeacherService.removeById(id);
        return falg;
    }

}

