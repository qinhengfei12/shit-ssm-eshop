package xyz.kmahyyg.eshopdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.kmahyyg.eshopdemo.model.SysItemCates;

import java.util.List;

/**
 * @Entity xyz.kmahyyg.eshopdemo.model.SysItemCates;
 */
@Mapper
public interface SysItemCatesDao {
    List<SysItemCates> selectAll();

    SysItemCates selectByName(String name);

    SysItemCates selectById(Integer id);

    int insert(SysItemCates record);

    int updateById(SysItemCates record);

    int deleteById(Integer id);
}
