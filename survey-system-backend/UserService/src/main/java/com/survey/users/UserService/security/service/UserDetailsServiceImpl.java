package com.survey.users.UserService.security.service;

import com.survey.users.UserService.domain.user.User;
import com.survey.users.UserService.mapper.UserMapper;
import com.survey.users.UserService.repository.user.UserRepository;
import com.survey.users.UserService.security.dto.UserAuthDetails;
import com.survey.users.UserService.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        UserAuthDetails details = userMapper.mapToUserAuthDetails(user);

        return new SecurityUser(details);
    }

}
