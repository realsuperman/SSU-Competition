package com.university.webservice.web;

import com.university.webservice.dto.common.CommonMainResponseDto;
import com.university.webservice.dto.common.CommonSaveRequestDto;
import com.university.webservice.dto.posts.PostsSaveRequestDto;
import com.university.webservice.dto.users.UsersSaveRequestDto;
import com.university.webservice.service.common.CommonService;
import com.university.webservice.service.posts.PostsService;
import com.university.webservice.service.users.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private UsersService usersService;
    private CommonService commonService;

    @GetMapping("/users")
    public int users(@RequestParam("userId") String userId) {
        if (usersService.findAllDesc(userId).size() > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    @GetMapping("/login")
    public int login(@RequestParam("userId") String uId, @RequestParam("userPw") String uPw, HttpSession session) throws Exception {
        if (usersService.perfectLogin(uId, uPw).size() == 0) {
            throw new Exception();
        }
        session.setAttribute("id", "user");
        return 1;

    }
    @GetMapping("/logout")
    public int logout(HttpSession session) {
        session.invalidate();
        return 1;
    }

    @GetMapping("/common")
    public List<CommonMainResponseDto> common(@RequestParam("userId") String userId) /*throws Exception*/ {
        if(commonService.findAllDesc(userId).size()==0){
            //throw new Exception();
        }
        return commonService.findAllDesc(userId);
    }

    @GetMapping("/view")
    public List<CommonMainResponseDto> view(@RequestParam("userId") String userId) /*throws Exception*/ {
        if(commonService.findAllDesc(userId).size()==0){
            //throw new Exception();
        }
        return commonService.findAllDesc(userId);
    }


    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @PostMapping("/users")
    public Long saveUsers(@RequestBody UsersSaveRequestDto dto) {
        return usersService.save(dto);
    }


    @DeleteMapping("/common")
    public void deleteCommon(@RequestParam String userId, @RequestParam Long typeCode){
        commonService.deleteCommon(userId,typeCode);
    }

    @PostMapping("/savecommon")
    public void saveCommon(@RequestBody CommonSaveRequestDto dto) {
        if (dto.toEntity().getTypeCode() == null){
            commonService.insertCommon(dto);
        }else{
            commonService.updateCommon(dto);
        }
    }
}