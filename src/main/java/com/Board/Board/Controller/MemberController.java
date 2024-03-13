package com.Board.Board.Controller;

import ch.qos.logback.core.model.Model;
import com.Board.Board.Dto.MemberDto;
import com.Board.Board.Service.MemberService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MemberController {
    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDto memberDto){
        Long memberId = memberService.join(memberDto);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }
}
