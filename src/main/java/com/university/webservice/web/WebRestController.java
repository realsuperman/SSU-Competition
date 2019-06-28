package com.university.webservice.web;

import com.university.webservice.dto.posts.PostsSaveRequestDto;
import com.university.webservice.dto.users.UsersSaveRequestDto;
import com.university.webservice.service.posts.PostsService;
import com.university.webservice.service.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private UsersService usersService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PostMapping("/users")
    public Long saveUsers(@RequestBody UsersSaveRequestDto dto){
        return usersService.save(dto);
    }
}