package xyz.kmahyyg.eshopdemo.dao;

import xyz.kmahyyg.eshopdemo.model.SysItemCates;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity model.SysItemCates
 */
@Mapper
public interface SysItemCatesDao {
    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insert(SysItemCates record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insertSelective(SysItemCates record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    SysItemCates selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKeySelective(SysItemCates record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKey(SysItemCates record);
}