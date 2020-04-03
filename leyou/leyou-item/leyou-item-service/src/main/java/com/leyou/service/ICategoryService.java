package com.leyou.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> queryCategoriesByPid(Long pid);
}
