package com.leyou.cart.service;

import com.leyou.cart.pojo.Cart;

import java.util.List;

public interface ICartService {
    /**
     * 添加购物车
     * @param cart
     */
    void addCart(Cart cart);

    /**
     * 查询购物车
     * @return
     */
    List<Cart> queryCarts();

    /**
     * 更新数量
     * @param cart
     */
    void upstateNum(Cart cart);

    /**
     * 删除购物车
     * @param skuId
     */
    void deleteCart(String skuId);

    /**
     * 将未登录时购物车与登录后购物车合并
     * @param carts
     */
    void mergeCart(List<Cart> carts);
}
