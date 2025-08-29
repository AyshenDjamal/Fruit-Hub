package com.example.fruithub.controller;

import com.example.fruithub.dto.ProductDto;
import com.example.fruithub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
   // private final CartService cartService;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int offset
    ){
        return productService.getALlProducts(search, sort, page, offset);
    }


    /*@GetMapping("/count")
    public Integer getProductsCount(@AuthenticationPrincipal User user){
        return cartService.getTotalItems(user);
    }*/
}
