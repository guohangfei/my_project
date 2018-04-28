package mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ghf
 */
public interface BaseMapper<T, E>{

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(Object TId);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(Object TId);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
