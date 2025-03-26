package edu.du.sb_thymeleaff.controller;

import edu.du.sb_thymeleaff.model.Account;
import edu.du.sb_thymeleaff.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home() {
        return "index"; // 기본 홈페이지로 index.jsp를 반환
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Account account) {
        accountService.register(account);
        return "success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 로그인 페이지로 이동
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, Model model) {
        if (accountService.login(id, password)) {
            return "redirect:/success"; // 로그인 성공 시 success 페이지 이동
        }
        model.addAttribute("error", "Invalid ID or Password");
        return "login"; // 로그인 실패 시 다시 로그인 페이지
    }
    @GetMapping("/logout")
    public String logout() {
        accountService.logout();
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 이동
    }
}
