package service;

import com.github.pagehelper.util.StringUtil;
import entity.*;
import mapper.RoleRightMapper;
import mapper.UserMapper;
import mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import result.KeyValudBean;

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
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleRightMapper roleRightMapper;

    /**
     * @Author:          郭航飞
     * @Description:：   跟用户姓名返回Userl类
     * @CreateDate:   2018/4/25 13:31
     * @param:  userName 用户姓名
     * @return        用户的实体类
    **/
    public User queryUserByName(String userName) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtil.isNotEmpty(userName)){
            criteria.andUserNameEqualTo(userName);
        }
        List<User> listUser = userMapper.selectByExample(userExample);
        User resultUser=listUser.isEmpty()?new User():listUser.get(0);
        return  resultUser;
    }

    /**
     * @Author:          郭航飞
     * @Description:：  根据以后名称活动角色信息
     * @CreateDate:   2018/4/26 9:33
     * @param            userId 用户id
     * @return     用户角色实
    **/
    public List<KeyValudBean> getRoleByUserId(String userId) {
       return userRoleMapper.getRoleByUserId(userId);
    }

    /**
     *根据用户的角色id 获得用户的对应的权限集合
     * @Author:          郭航飞
     * @CreateDate:   2018/4/26 13:26
     * @param           roleId  角色id
     * @return            权限的List集合
    **/
    public List<KeyValudBean> getRightByRoleId(String roleId) {
       return roleRightMapper.getRightByRoleId(roleId);
    }
}
