package ar.com.dccsoft.induccion.service.security;

import ar.com.dccsoft.induccion.entity.User;
import ar.com.dccsoft.induccion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User.withUsername(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }


}
