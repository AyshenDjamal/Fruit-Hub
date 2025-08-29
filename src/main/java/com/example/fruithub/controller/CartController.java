package com.example.fruithub.controller;

import com.example.fruithub.entity.User;
import com.example.fruithub.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/count")
    public Integer getCartCount(@AuthenticationPrincipal User user){
        return cartService.getTotalItems(user);
    }



}
