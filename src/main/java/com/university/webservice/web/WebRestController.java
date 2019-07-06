package com.university.webservice.web;

import com.university.webservice.dto.posts.PostsSaveRequestDto;
import com.university.webservice.dto.users.UsersSaveRequestDto;
import com.university.webservice.service.posts.PostsService;
import com.university.webservice.service.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private UsersService usersService;

    
    @GetMapping("/users")
    public int users(@RequestParam("userId") String userId) {
        if(usersService.findAllDesc(userId).size()>0){
            return 1;
        }else{
            return 0;
        }

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