package xyz.kmahyyg.eshopdemo.dao;

import xyz.kmahyyg.eshopdemo.model.SysUsers;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity model.SysUsers
 */
@Mapper
public interface SysUsersDao {
    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insert(SysUsers record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int insertSelective(SysUsers record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    SysUsers selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKeySelective(SysUsers record);

    /**
     *
     * @mbg.generated 2021-02-06 00:30:33
     */
    int updateByPrimaryKey(SysUsers record);
}