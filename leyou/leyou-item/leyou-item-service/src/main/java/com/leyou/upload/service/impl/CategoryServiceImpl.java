package com.leyou.upload.service.impl;

import com.leyou.item.pojo.Category;
import com.leyou.mapper.CategoryMapper;
import com.leyou.upload.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl  implements ICategoryService  {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据parentId查询子类目
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    @Override
    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }

    @Override
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> list = this.categoryMapper.selectByIdList(ids);
        List<String> names = new ArrayList<>();
//        for (Category category : list) {
//            names.add(category.getName());
//        }
//        return names;
        return list.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}
