package mapper;

import entity.User;
import entity.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper  extends BaseMapper<User, UserExample> {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * 根据用户注册账号后获得所用用户信息
     * @param  userName 用户注册姓名
     * @return 用户实体User
     */
    User findUserByUserName(String userName);
}