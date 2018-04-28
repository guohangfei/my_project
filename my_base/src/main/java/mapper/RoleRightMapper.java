package mapper;

import entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ghf
 */
public interface RoleRightMapper  extends BaseMapper<RoleRight, RoleRightExample>{

    /**
     *根据用户的角色id 获得用户的对应的权限集合
     * @Author:          郭航飞
     * @CreateDate:   2018/4/26 13:26
     * @param           roleId  角色id
     * @return            权限的List集合
     **/
    List<Right> getRightByRoleId(String roleId);
}