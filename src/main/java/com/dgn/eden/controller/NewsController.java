package com.dgn.eden.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dgn.eden.mapper.NewsMapper;
import com.dgn.eden.common.Result;
import com.dgn.eden.entity.News;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    NewsMapper newsMapper;

//    添加用户的
    @PostMapping
    public Result<?> save(@RequestBody News news){
        news.setTime(new Date());//服务端设置时间

        newsMapper.insert(news);
        return Result.success();
    }
//    更新
    @PutMapping
    public Result<?> update(@RequestBody News news){
        newsMapper.updateById(news);
        return Result.success();
    }
    //    s删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        newsMapper.deleteById(id);
        return Result.success();
    }
//    查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(News::getTitle,search);
        }
        Page<News> NewsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(NewsPage);
    }
}
