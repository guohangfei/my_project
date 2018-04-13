package service;

import com.github.pagehelper.PageHelper;
import com.nasoft.log.aspect.SystemLog;
import com.nasoft.log.util.LogTab;
import mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;
import util.PageData;

import java.util.List;

public class GeneralService<M extends BaseMapper<T, E>, T, E> extends BaseService{
	
	@Autowired
	protected M mapper;
	
	@SystemLog(type = LogTab.LOG_TYPE_QUERY)
    public int countByExample(E example){
    	return mapper.countByExample(example);
    }
	
	@SystemLog(type = LogTab.LOG_TYPE_DELETE)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteByExample(E example){
    	return mapper.deleteByExample(example);
    }

	@SystemLog(type = LogTab.LOG_TYPE_DELETE)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteByPrimaryKey(Object TId){
    	return mapper.deleteByPrimaryKey(TId);
    }

	@SystemLog(type = LogTab.LOG_TYPE_INSERT)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insert(T record){
    	return mapper.insert(record);
    }

    @SystemLog(type = LogTab.LOG_TYPE_INSERT)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insertSelective(T record){
    	return mapper.insertSelective(record);
    }

    @SystemLog(type = LogTab.LOG_TYPE_QUERY)
    public List<T> selectByExample(E example){
    	return mapper.selectByExample(example);
    }

    @SystemLog(type = LogTab.LOG_TYPE_QUERY)
    public T selectByPrimaryKey(Object TId){
    	return mapper.selectByPrimaryKey(TId);
    }

    @SystemLog(type = LogTab.LOG_TYPE_UPDATE)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByExampleSelective(@Param("record") T record, @Param("example") E example){
    	return mapper.updateByExampleSelective(record, example);
    }

    @SystemLog(type = LogTab.LOG_TYPE_UPDATE)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByExample(@Param("record") T record, @Param("example") E example){
    	return mapper.updateByExample(record, example);
    }
    
    @SystemLog(type = LogTab.LOG_TYPE_UPDATE)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByPrimaryKeySelective(T record){
    	return mapper.updateByPrimaryKeySelective(record);
    }

    @SystemLog(type = LogTab.LOG_TYPE_UPDATE)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByPrimaryKey(T record){
    	return mapper.updateByPrimaryKey(record);
    }
    

    public PageData<T> selectPageByExample(E example, Integer page, Integer size){
    	int total = countByExample(example);
    	List<T> rows = null;
    	if(total > 0){
    		PageHelper.startPage(page, size);
    		rows = selectByExample(example);
    		PageHelper.clearPage();
    	}
    	return new PageData<T>(total, rows);
    }

    
    public T uniqueByExample(E example){
    	PageHelper.startPage(0, 1);
    	List<T> list = mapper.selectByExample(example);
    	PageHelper.clearPage();
    	return list.size() > 0 ? list.get(0) : null;
    }
   
}
