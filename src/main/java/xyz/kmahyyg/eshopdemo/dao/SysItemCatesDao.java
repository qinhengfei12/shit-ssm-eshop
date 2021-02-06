package xyz.kmahyyg.eshopdemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import xyz.kmahyyg.eshopdemo.model.SysItemCates;

/**
 * @Entity xyz.kmahyyg.eshopdemo.model.SysItemCates;
 */
@Mapper
public interface SysItemCatesDao {
    List<SysItemCates> selectAll();
    SysItemCates selectByName(String name);
    SysItemCates selectById(Integer id);
    int insert(SysItemCates record);
    int updateById(Integer id);
    int deleteById(Integer id);
}
