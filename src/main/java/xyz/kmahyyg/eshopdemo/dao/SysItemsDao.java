package xyz.kmahyyg.eshopdemo.dao;

import xyz.kmahyyg.eshopdemo.model.SysItems;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity model.SysItems
 */
@Mapper
public interface SysItemsDao {
    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insert(SysItems record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insertSelective(SysItems record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    SysItems selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKeySelective(SysItems record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKey(SysItems record);
}