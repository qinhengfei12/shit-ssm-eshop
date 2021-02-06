package xyz.kmahyyg.eshopdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MbtsAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDService userDService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserInfo userInfo = (UserInfo) userDService.loadUserByUsername(username);
        if (userInfo == null){
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())){
            throw new BadCredentialsException("用户名或密码不正确");
        }
        if (!userInfo.getStatus()){
            throw new LockedException("用户状态异常！");
        }
        return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
