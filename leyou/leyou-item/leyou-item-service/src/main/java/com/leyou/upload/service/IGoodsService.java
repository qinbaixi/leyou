package com.leyou.upload.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;

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

    /**
     * 除了要对SPU新增以外，还要对SpuDetail、Sku、Stock进行保存
     * @param spuBo
     */
    void saveGoods(SpuBo spuBo);
}
