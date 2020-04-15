package com.leyou.goods.service;

import java.util.Map;

public interface IGoodsService {
    /**
     * 组装item前台数据
     * @param id
     * @return
     */
    Map<String, Object> loadData(Long id);
}
