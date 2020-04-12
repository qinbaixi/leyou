package com.leyou.search.service;


import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;

import java.io.IOException;

public interface ISearchService {
    /**
     * 转化数据模型
     * @param spu
     * @return
     * @throws IOException
     */
    Goods buildGoods(Spu spu) throws IOException;

    /**
     * 查询elasticSearch分页结果集
     * @param request
     * @return
     */
    PageResult<Goods> search(SearchRequest request);
}
