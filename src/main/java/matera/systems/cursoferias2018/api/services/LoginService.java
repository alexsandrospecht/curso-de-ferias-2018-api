package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioResponse> usuarioResponseOptional = service.findUsuarioByLogin(username);

        UsuarioResponse usuarioResponse = usuarioResponseOptional.orElseThrow(()-> new UsernameNotFoundException("Usuario ou senha errados."));
        Set<SimpleGrantedAuthority> authority = new HashSet<SimpleGrantedAuthority>();

        return new User(username, "admin", authority);
    }
}
