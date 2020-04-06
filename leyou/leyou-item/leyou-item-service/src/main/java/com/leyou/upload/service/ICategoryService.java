package com.leyou.upload.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> queryCategoriesByPid(Long pid);

    List<Category> queryByBrandId(Long bid);

    List<String> queryNamesByIds(List<Long> asList);
}
