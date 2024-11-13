package com.packt.mybase.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.packt.mybase.domain.User;
import com.packt.mybase.domain.UserBase;
import com.packt.mybase.dto.UserDetailsImp;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
    
    private final UserBase userBase;

    public UserDetailsServiceImp(UserBase userBase) {

        this.userBase = userBase;
    }


    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User data = userBase.findByUsername(username); 

        if (data != null) {
						
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
        return new UserDetailsImp(data);
        }

        return null;   
	}


}
