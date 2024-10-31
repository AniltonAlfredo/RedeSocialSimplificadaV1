package com.TS.TwiterSimplicado.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.TS.TwiterSimplicado.entities.Role;
import com.TS.TwiterSimplicado.entities.User;
import com.TS.TwiterSimplicado.repository.RoleRepository;
import com.TS.TwiterSimplicado.repository.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner{

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, 
                            UserRepository userRepository,
                            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
            (user)-> System.out.println("admin já existe")
            , ()->{
                var user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("123"));
                user.setRoles(Set.of(roleAdmin));
                userRepository.save(user);
            }
            );

    }
    
}
