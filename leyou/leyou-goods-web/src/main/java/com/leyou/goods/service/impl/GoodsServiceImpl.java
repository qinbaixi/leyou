package com.leyou.goods.service.impl;

import com.leyou.goods.client.BrandClient;
import com.leyou.goods.client.CategoryClient;
import com.leyou.goods.client.GoodsClient;
import com.leyou.goods.client.SpecificationClient;
import com.leyou.goods.service.IGoodsService;
import com.leyou.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
@Service
public class  GoodsServiceImpl implements IGoodsService {
    @Autowired
    private BrandClient brandClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specificationClient;


    @Override
    public Map<String, Object> loadData(Long id) {
        Map<String, Object> model = new HashMap<>();

        //获取spu
        Spu spu = this.goodsClient.querySpuById(id);

        //获取spuDetail
        SpuDetail spuDetail = this.goodsClient.querySpuDetailById(id);

        //获取categories
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> names = this.categoryClient.queryNameByIds(cids);
        //初始化Map
        List<Map<String, Object>> categories = new ArrayList<>();
        for (int i = 0; i < cids.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cids.get(i));
            map.put("name", names.get(i));
            categories.add(map);
        }

        //查询品牌
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());

        //查询skus
        List<Sku> skus = this.goodsClient.querySkuBySpuId(id);

        //查询规格参数组
        List<SpecGroup> groups = this.specificationClient.queryGroupsWithParam(spu.getCid3());

        //查询特殊规格参数
        List<SpecParam> params = this.specificationClient.queryParams(null, spu.getCid3(), null, null);
        //初始化map
        Map<Long, Object> paramMap = new HashMap<>();
        params.forEach(param ->{
            paramMap.put(param.getId(), param.getName());
        });


        model.put("spu", spu);
        model.put("spuDetail", spuDetail);
        model.put("categories", categories);
        model.put("brand", brand);
        model.put("skus", skus);
        model.put("groups", groups);
        model.put("paramMap", paramMap);

        return model;
    }
}
