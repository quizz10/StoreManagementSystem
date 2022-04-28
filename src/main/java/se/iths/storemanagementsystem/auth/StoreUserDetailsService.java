package se.iths.storemanagementsystem.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.repository.UserRepository;

@Service
public class StoreUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public StoreUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Can't find user with username: " + username);
        }

        return new StorePrincipal(user);

    }
}

