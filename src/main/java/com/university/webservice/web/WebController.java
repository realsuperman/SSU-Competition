package com.university.webservice.web;

import com.university.webservice.service.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class WebController {

    private UsersService usersService;
    public static final String mainPage = "login";

    @GetMapping("/")
    public String main() {
        return mainPage;
    }

    @PostMapping("/login/users")
    public String login(@RequestParam("uId") String uId, Model model, HttpSession session) {
        System.out.println(session);
        return commonCode(uId,model,session,"main");
    }

    @PostMapping("/common")
    public String common(@RequestParam("uId") String uId, Model model,HttpSession session) {
        return commonCode(uId,model,session,"common");
    }

    @PostMapping("/view")
    public String view(@RequestParam("uId") String uId, Model model,HttpSession session) {
        return commonCode(uId,model,session,"view");
    }

    public String commonCode(@RequestParam("uId") String uId, Model model,HttpSession session,String changePage){
        if(session.getAttribute("id")==null){
            return mainPage;
        }
        model.addAttribute("uId", uId);
        return changePage;
    }
}
