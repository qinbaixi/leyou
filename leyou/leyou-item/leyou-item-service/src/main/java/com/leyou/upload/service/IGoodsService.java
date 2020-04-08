package com.leyou.upload.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

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

    /**
     * 根据spuId查询spuDetail
     * @param spuId
     * @return
     */
    SpuDetail querySpuDetailBySpuId(Long spuId);

    /**
     * 根据spuId查询sku的集合,为了回显方便,把库存也查找出来
     * @param spuId
     * @return
     */
    List<Sku> querySkusBySpuId(Long spuId);

    /**
     * 更新商品
     * @param spuBo
     */
    void updateGoods(SpuBo spuBo);

    /**
     * 下架商品
     * @param id
     */
    void unSalebaleGoods(Long id);

    /**
     * 上架商品
     * @param id
     */
    void onSalebaleGoods(Long id);

    /**
     * 删除商品
     * @param id
     */
    void delSpuBySpuId(Long id);
}
