package com.medinar.springsecurityjpa.service;

import com.medinar.springsecurityjpa.model.MyUserDetails;
import com.medinar.springsecurityjpa.model.User;
import com.medinar.springsecurityjpa.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author rommelmedina
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName));
        return user.map(MyUserDetails::new).get();
    }
    
}
