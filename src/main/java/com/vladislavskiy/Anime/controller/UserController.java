package com.vladislavskiy.Anime.controller;

import com.vladislavskiy.Anime.dto.UsernameRequest;
import com.vladislavskiy.Anime.models.UserInfo;
import com.vladislavskiy.Anime.services.Impl.UserInfoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
    private final UserInfoService service;

    public UserController(@Qualifier("userInfoService") UserInfoService service) {
        this.service = service;
    }

    @PostMapping("/isUserExistByName")
    public String isUserWithNameExist(@RequestBody UsernameRequest request) {
        return service.checkUserOnExistByUsername(request.username()).toString();
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

}
