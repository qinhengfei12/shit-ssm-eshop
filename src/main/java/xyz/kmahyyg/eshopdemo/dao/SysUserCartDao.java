package xyz.kmahyyg.eshopdemo.dao;

import xyz.kmahyyg.eshopdemo.model.SysUserCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity model.SysUserCart
 */
@Mapper
public interface SysUserCartDao {
    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insert(SysUserCart record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insertSelective(SysUserCart record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    SysUserCart selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKeySelective(SysUserCart record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKey(SysUserCart record);
}