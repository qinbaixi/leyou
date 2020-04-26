package com.leyou.cart.service;

import com.leyou.cart.pojo.Cart;

public interface ICartService {
    /**
     * 添加购物车
     * @param cart
     */
    void addCart(Cart cart);
}
