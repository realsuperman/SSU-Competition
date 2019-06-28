package com.university.webservice.web;

import com.university.webservice.service.posts.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class webController {

    private PostsService postsService;

/*    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }*/
    @GetMapping("/")
    public String main() {
        return "login";
    }
}
