package com.ddm.flyem.service.impl;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.domain.FlyemUserDetails;
import com.ddm.flyem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(userLogin);

        if (user == null) {
            user = userRepository.findFirstByEmail(userLogin);
            if (user == null) {
                throw new UsernameNotFoundException("User not found.");
            }
        }

        return new FlyemUserDetails(user);
    }

}
