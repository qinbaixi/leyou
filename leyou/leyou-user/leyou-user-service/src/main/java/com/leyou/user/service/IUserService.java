package com.leyou.user.service;

import com.leyou.user.pojo.User;

public interface IUserService {
    /**
     * 检查用户
     * @param data
     * @param type
     * @return
     */
    Boolean checkUser(String data, Integer type);

    /**
     * 发送验证码
     * @param phone
     */
    void sendVerifyCode(String phone);

    /**
     *用户注册
     * @param user
     * @param code
     */
    void register(User user, String code);
}
