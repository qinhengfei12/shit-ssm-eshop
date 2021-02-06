package xyz.kmahyyg.eshopdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysUsers;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService<T extends SysUsers> implements UserDetailsService {

    @Autowired
    private SysUsersDao userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsers user = userMapper.selectByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        String role = user.getRole();
        if (role != null && !role.isEmpty()){
            authorityList.add(new SimpleGrantedAuthority(role.trim()));
        }
        return new User(user.getUsername(),user.getPassword(), authorityList);
    }
}
