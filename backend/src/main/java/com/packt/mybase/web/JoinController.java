package com.packt.mybase.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.mybase.dto.JoinDTO;
import com.packt.mybase.service.JoinService;

@Controller
@ResponseBody
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        System.out.println(joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "ok";
    }

}
