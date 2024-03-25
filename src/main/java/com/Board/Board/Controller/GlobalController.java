package com.Board.Board.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GlobalController {
    @GetMapping("/")
    public String home() { return "redirect:/list"; }

    @GetMapping("/error/403")
    public String authorization() { return "error/403"; }

    @GetMapping("/admin")
    public String admin() {
        return "admin"; }
}
