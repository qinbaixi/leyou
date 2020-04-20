package com.leyou.user.service.impl;

import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import com.leyou.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 校验数据是否可用
     * @param data
     * @param type
     * @return
     */
    @Override
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if (type == 1) {
            record.setUsername(data);
        } else if (type == 2) {
            record.setPhone(data);
        }else{
            //badRequest
            return null;
        }
        return this.userMapper.selectCount(record) == 0;

    }
}
