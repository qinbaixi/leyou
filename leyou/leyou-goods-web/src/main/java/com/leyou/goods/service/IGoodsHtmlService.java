package com.leyou.goods.service;

public interface IGoodsHtmlService {
   /**
    * 静态化方法
    * @param spuId
    */
    void createHtml(Long spuId);

   /**
    * 线程安全实现页面静态化
    */
   void asyncExcute(Long spuId);
}
