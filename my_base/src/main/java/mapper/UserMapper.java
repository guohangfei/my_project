package mapper;

import entity.Role;
import entity.User;
import entity.UserExample;

public interface UserMapper  extends BaseMapper<User, UserExample> {

    /**
     * 根据用户姓名活动整改实体类
     * @Author:          郭航飞
     * @CreateDate:   2018/4/25 13:41
     * @param            userName
     * @return           用户实体类 User
    **/
    User queryUserByName(String userName);

    /**
     *根据用户名称获得用户角色
     * @Author:          郭航飞
     * @CreateDate:   2018/4/26 9:38
     * @param           userName  用户名称
     * @return            Role   角色实体
    **/
    Role getRoleByUserName(String userName);
}