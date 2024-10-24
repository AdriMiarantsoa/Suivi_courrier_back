package adri.suivi_courrier.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.repository.UtilisateurRepository;
import adri.suivi_courrier.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UtilisateurRepository userRepository;
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                System.out.println("findbyid " + username);
                return userRepository.findById(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}