package com.packt.mybase.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.packt.mybase.domain.User;
import com.packt.mybase.domain.UserBase;
import com.packt.mybase.dto.JoinDTO;

@Service
public class JoinService {
    private final UserBase userBase;
    private final BCryptPasswordEncoder bcEncoder;

    public JoinService(UserBase userBase, BCryptPasswordEncoder bcEncoder) {

        this.userBase = userBase;
        this.bcEncoder = bcEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userBase.existsByUsername(username);

        if (isExist) {

            return;
        }

        User data = new User();

        data.setUsername(username);
        data.setPassword(bcEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userBase.save(data);
    }
}

