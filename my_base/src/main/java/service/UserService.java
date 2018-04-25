package service;

import com.github.pagehelper.util.StringUtil;
import entity.User;
import entity.UserExample;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 郭航飞
 * @Description: java类作用描述
 * @Date: created in      2018/4/1216:37
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class UserService extends GeneralService<UserMapper, User,UserExample>{

    @Autowired
    private UserMapper userMapper;
    /**
     * @Author:          郭航飞
     * @Description:：   跟用户姓名返回Userl类
     * @CreateDate:   2018/4/25 13:31
     * @param:  userName 用户姓名
     * @return        用户的实体类
    **/
    public User queryUserByName(String userName) {
        return  userMapper.queryUserByName(userName);
    }
}
