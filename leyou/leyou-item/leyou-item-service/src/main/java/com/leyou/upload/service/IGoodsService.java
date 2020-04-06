package com.leyou.upload.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.SpuBo;

public interface IGoodsService {
    /**
     * 根据条件查询商品分页结果
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows);
}
