package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguli.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Api("讲师管理")
public class EduTeacherController {
    //访问地址 localhost:8001/eduservice/teacher/findall
    @Autowired
    private EduTeacherService eduTeacherService;

    //1 查询讲师表所有的数据
    //rest风格
    @GetMapping("findAll")
    @ApiOperation("查询所有的讲师信息")
    public R findAllTeaacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //2逻辑删除讲师方法  id需要通过路径传递
    @DeleteMapping("{id}")
    @ApiOperation("假删除讲师")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true ) @PathVariable String id){
        boolean falg = eduTeacherService.removeById(id);
        if (falg) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @title pageListTeacher
     * @description 分页查询
     * @author admin
     * @updateTime 2022/1/23 15:08
     * @throws
     */
    @ApiOperation("分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public  R pageListTeacher(@PathVariable long current,@PathVariable long limit) {

        //创建page对象
        Page<EduTeacher> pagerTeacher = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法的时候会把分页所有的数据封装到pageTeacher里面
        eduTeacherService.page(pagerTeacher, null);

        long total = pagerTeacher.getTotal();//总页数
        List<EduTeacher> records = pagerTeacher.getRecords();
//        Map map = new HashMap();
//        map.put("total", total);
//        map.put("rows", records);
//        return R.ok().data(map);

        //也可以用链接写法
        return R.ok().data("total",total).data("rows",records);
    }
}

