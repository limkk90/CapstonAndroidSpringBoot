package com.example.android.Service;


import com.example.android.Dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String u_id) throws UsernameNotFoundException {
        com.example.android.Dto.User user = userService.getUser(u_id)
                .orElseThrow(() -> new UsernameNotFoundException(u_id));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
//        if (u_id.equals("sup2is@gmail.com")) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
//        }

        return new User(user.getU_id(), user.getU_pwd(), grantedAuthorities);
    }

    public com.example.android.Dto.User authenticateByEmailAndPassword(String u_id, String password) {
        com.example.android.Dto.User user = userService.getUser(u_id)
                .orElseThrow(() -> new UsernameNotFoundException(u_id));

        if(!passwordEncoder.matches(password, user.getU_pwd())) {
            throw new BadCredentialsException("Password not matched");
        }

        return user;
    }

}
