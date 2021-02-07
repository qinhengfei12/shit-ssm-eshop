package xyz.kmahyyg.eshopdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.kmahyyg.eshopdemo.model.SysOrders;

import java.util.List;

/**
 * @Entity xyz.kmahyyg.eshopdemo.model.SysOrders
 */
@Mapper
public interface SysOrdersDao {
    int deleteOrderByOid(String oid);

    SysOrders selectByOid(String oid);

    List<SysOrders> selectByUserId(String uid);

    int updateByOidSelective(SysOrders record);

    int insert(SysOrders record);
}
