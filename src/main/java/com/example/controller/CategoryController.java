package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.entity.Category;
import com.example.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category)
    {
        log.info("category ;{}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //分页构造器
        Page<Category> pageInof = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        lambdaQueryWrapper.orderByAsc(Category::getSort);

        //进行分页查询
        categoryService.page(pageInof, lambdaQueryWrapper);

        return R.success(pageInof);
    }


    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id)
    {
        log.info("删除分类，id为：{}",id);

//        categoryService.removeById(id);
            categoryService.remove(id);

        return R.success("分类信息删除成功");
    }


    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category)
    {
        log.info("修改分类信息：{}",category);
        categoryService.updateById(category);

        return R.success("修改分类信息成功");
    }


    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List> list(Category category){

        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);
    }

}
