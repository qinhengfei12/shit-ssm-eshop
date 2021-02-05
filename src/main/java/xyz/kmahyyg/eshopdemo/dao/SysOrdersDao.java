package xyz.kmahyyg.eshopdemo.dao;

import xyz.kmahyyg.eshopdemo.model.SysOrders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity model.SysOrders
 */
@Mapper
public interface SysOrdersDao {
    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insert(SysOrders record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insertSelective(SysOrders record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    SysOrders selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKeySelective(SysOrders record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKey(SysOrders record);
}