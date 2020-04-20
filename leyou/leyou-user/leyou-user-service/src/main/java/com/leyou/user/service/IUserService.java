package com.leyou.user.service;

public interface IUserService {
    /**
     * 检查用户
     * @param data
     * @param type
     * @return
     */
    Boolean checkUser(String data, Integer type);
}
