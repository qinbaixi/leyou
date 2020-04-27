package com.leyou.cart.service.impl;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.cart.client.GoodsClient;
import com.leyou.cart.interceptor.LoginInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.ICartService;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.Sku;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX ="user:cart:";

    static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private GoodsClient goodsClient;


    /**
     * 添加购物车
     *
     * @param cart
     */
    @Override
    public void addCart(Cart cart) {
        //获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        //查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

        String key = null;
        Integer num = null;
        try {
            key = cart.getSkuId().toString();
            num = cart.getNum();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        //判断当前的商品是否在购物车中
        if (hashOperations.hasKey(key)) {
            //更新数量
            String cartJson = hashOperations.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum()+num);
        } else {
            //不在，添加到购物车中
            Sku sku = this.goodsClient.querySkuBuSkuId(cart.getSkuId());

            cart.setUserId(userInfo.getId());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
        }
        hashOperations.put(key,JsonUtils.serialize(cart));
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @Override
    public List<Cart> queryCarts() {
        //获取用户
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        //判断是否有这个购物车
        if (!this.redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return null;
        }
        BoundHashOperations<String, Object, Object> boundHashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

        List<Object> cartsJson = boundHashOps.values();

        if (CollectionUtils.isEmpty(cartsJson)) {
            return null;
        }
        return cartsJson.stream().map(cartJson -> JsonUtils.parse(cartJson.toString(), Cart.class)).collect(Collectors.toList());

    }

    /**
     * 更新数量
     *
     * @param cart
     */
    @Override
    public void upstateNum(Cart cart) {
        //获取用户
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        //判断是否有这个购物车
        if (!this.redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return ;
        }
        Integer num = cart.getNum();
        BoundHashOperations<String, Object, Object> boundHashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        String cartJson = boundHashOps.get(cart.getSkuId().toString()).toString();

        cart = JsonUtils.parse(cartJson, Cart.class);
        cart.setNum(num);

        boundHashOps.put(cart.getSkuId().toString(), JsonUtils.serialize(cart));
    }

    /**
     * 删除购物车
     *
     * @param skuId
     */
    @Override
    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getUserInfo();
        String key = KEY_PREFIX + user.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        hashOps.delete(skuId);
    }

    /**
     * 将未登录时购物车与登录后购物车合并
     *
     * @param carts
     */
    @Override
    public void mergeCart(List<Cart> carts) {

        //获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        //查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

       carts.forEach(cart -> {
           String key = cart.getSkuId().toString();
           Integer num = cart.getNum();

           //判断当前的商品是否在购物车中
           if (hashOperations.hasKey(key)) {
               //更新数量
               String cartJson = hashOperations.get(key).toString();
               cart = JsonUtils.parse(cartJson, Cart.class);
               cart.setNum(cart.getNum()+num);
           } else {
               //不在，添加到购物车中
               Sku sku = this.goodsClient.querySkuBuSkuId(cart.getSkuId());

               cart.setUserId(userInfo.getId());
               cart.setTitle(sku.getTitle());
               cart.setOwnSpec(sku.getOwnSpec());
               cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
               cart.setPrice(sku.getPrice());
           }
           hashOperations.put(key,JsonUtils.serialize(cart));

       });

    }
}
