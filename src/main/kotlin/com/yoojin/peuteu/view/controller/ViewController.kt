package com.yoojin.peuteu.view.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {
    @GetMapping("/", "/main")
    fun mainPage(): String {
        return "main";
    }

    @GetMapping("/history")
    fun historyPage(): String {
        return "history";
    }

    @GetMapping("/analysis")
    fun analysisPage(): String {
        return "analysis";
    }

    @GetMapping("/profile")
    fun profilePage(): String {
        return "profile";
    }

    @GetMapping("/profile-edit")
    fun editProfilePage(): String {
        return "profile-edit";
    }

    @GetMapping("/profile-password-check")
    fun profilePasswordCheckPage(): String {
        return "profile-password-check";
    }

    @GetMapping("/login")
    fun loginPage(): String {
        return "login";
    }

    @GetMapping("/signup")
    fun signupPage(): String {
        return "signup";
    }

    @GetMapping("/signup-success")
    fun signupSuccessPage(): String {
        return "signup-success";
    }
}