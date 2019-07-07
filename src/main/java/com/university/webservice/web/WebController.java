package com.university.webservice.web;

import com.university.webservice.service.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class WebController {

    private UsersService usersService;

/*    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }*/
    @GetMapping("/")
    public String main() {
        return "login";
    }

    @GetMapping("/login/users")
    public String login(Model model, HttpSession session) {
        if(session.getAttribute("id")==null){
            return "login";
        }
        model.addAttribute("users", usersService.findAllDesc());
        return "main";
    }
}
