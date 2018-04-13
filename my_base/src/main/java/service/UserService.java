package service;

import entity.User;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1216:37
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByUserName(String userName) {
        User user;
        user = userMapper.findUserByUserName(userName);
    }
}
