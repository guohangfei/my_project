package mapper;

import entity.Role;
import entity.UserRole;
import entity.UserRoleExample;

import java.util.List;

/**
 * @author ghf
 */
public interface UserRoleMapper  extends BaseMapper<UserRole,UserRoleExample>{

    /**
     *  根据以后名称活动角色信息
     * @Author:          郭航飞
     * @CreateDate:   2018/4/26 9:33
     * @param            userName 用户名称
     * @return     用户角色实体类
     **/
    List<Role> getRoleByUserName(String userName);
}