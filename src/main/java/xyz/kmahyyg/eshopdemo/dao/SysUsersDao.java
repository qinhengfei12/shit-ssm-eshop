package xyz.kmahyyg.eshopdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.kmahyyg.eshopdemo.model.SysUsers;

/**
 * @Entity xyz.kmahyyg.eshopdemo.model.SysUsers
 */
@Mapper
public interface SysUsersDao {
    int deleteByUserId(String uid);

    int deleteByUserName(String username);

    int insert(SysUsers record);

    int updateByUserIdSelective(SysUsers record);

    int updateByUserNameSelective(SysUsers record);

    SysUsers selectByUserId(String uid);

    SysUsers selectByUserName(String username);
}
