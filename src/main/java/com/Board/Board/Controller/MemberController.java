package com.Board.Board.Controller;

import com.Board.Board.Dto.MemberDto;
import com.Board.Board.Dto.MemberSignupDto;
import com.Board.Board.Service.MemberService;
import com.Board.Board.Validator.CheckSignupValidator;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final MemberService memberService;
    private final CheckSignupValidator checkSignupValidator;

    /* 유효성 검증 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkSignupValidator);
    }

    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberSignupDto memberSignupDto, Errors errors, Model model){
        /* 검증 */
        if (errors.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 유지 */
            model.addAttribute("dto", memberSignupDto);

            /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
            memberService.messageHandling(errors, model);
            return "member/signup";
        }
        Long memberId = memberService.join(memberSignupDto);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        if (!checkSession().equals("")) {
            return "redirect:/list";
        }
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
    public String updateUser(Model model){
        String userid = checkSession();
        MemberDto member = memberService.findMember(userid);
        model.addAttribute("member", member);
        return "member/updateProfile";
    }

    @PostMapping("/update")
    public String updateUser(MemberDto memberUpdatedDto, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("member", memberUpdatedDto);
            memberService.messageHandling(errors, model);
            return "member/updateProfile";
        }

        model.addAttribute("member", memberUpdatedDto);
        memberService.updateMember(memberUpdatedDto);
        return "member/profile";
    }

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "member/withdrawal";
    }

    @PostMapping("/withdrawal")
    public String withdrawal(@RequestParam String password, Model model, HttpServletRequest request, HttpServletResponse response) {
        boolean result = memberService.withdrawal(checkSession(), password);

        if (result) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
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
