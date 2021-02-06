package xyz.kmahyyg.eshopdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.kmahyyg.eshopdemo.dao.SysUsersDao;
import xyz.kmahyyg.eshopdemo.model.SysUsers;


@Service
public class UserDService<T extends SysUsers> implements UserDetailsService {

    @Autowired
    private SysUsersDao userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsers user = userMapper.selectByUserName(username);
        if (user == null) {
            return null;
        }
        String role = user.getRole();
        if (role == null || role.isEmpty()) {
            return null;
        }

        return new UserInfo(user.getUsername(), user.getPassword(), user.getRole(), user.getAccountNonExpired(), user.getAccountNonLocked(), user.getCredsNonExpired(), user.getEnabled());
    }
}
