package com.Board.Board.Controller;

import com.Board.Board.Dto.MemberDto;
import com.Board.Board.Service.MemberService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/profile")
    public String mypage(Model model){
        String userid = checkSession();
        MemberDto member = memberService.findMember(userid);
        model.addAttribute("member", member);
        return "member/profile";
    }

    @GetMapping("/update")
    public String updateUsernameForm(Model model){
        String userid = checkSession();
        MemberDto member = memberService.findMember(userid);
        model.addAttribute("member", member);
        return "member/updateProfile";
    }

    @PostMapping("/update")
    public String updateUser(MemberDto memberUpdatedDto, Model model){
        model.addAttribute("member", memberUpdatedDto);
        memberService.updateMember(memberUpdatedDto);
        return "member/profile";
    }

    @GetMapping("/withdrawal")
    public String memberWithdrawal() {
        return "member/withdrawal";
    }

    @PostMapping("/withdrawal")
    public String memberWithdrawal(@RequestParam String password, Model model) {
        boolean result = memberService.withdrawal(checkSession(), password);

        if (result) {
            return "redirect:/list";
        } else {
            model.addAttribute("wrongPassword", "비밀번호가 일치하지 않습니다");
            return "member/withdrawal";
        }
    }
    private static String checkSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userid = "";

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            userid = userDetails.getUsername();
            String auth = userDetails.getAuthorities().toString();

            System.out.println("userid : " + userid);
            System.out.println("auth : " + auth);
        } else {
            System.out.println("인증되지 않은 사용자입니다.");
        }
        return userid;
    }
}
