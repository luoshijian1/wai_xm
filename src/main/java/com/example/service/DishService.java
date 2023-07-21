package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.DishDto;
import com.example.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时保存口味数据
     * @param dishDto
     */
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，和口味信息
    public void updateWithFlavor(DishDto dishDto);

    //删除菜品信息和口味信息
    public void deleteWithFlavor(List<Long> id);


}
