package org.digevil.sboot3user.service;

import jakarta.annotation.Resource;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public User findByUuid(String uuid) {
        return userMapper.findByUuid(uuid);
    }

    /**
     * @param user insert 成功的话 id 会刷新成真正的插入记录的 id
     */
    public void insert(User user) {
        userMapper.insert(user);
    }
}
