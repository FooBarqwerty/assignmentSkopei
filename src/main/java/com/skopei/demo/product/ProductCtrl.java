package com.skopei.demo.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name="Product")
public class ProductCtrl {

    @GetMapping
    @Operation(summary = "get product by ...")
    public String test() {
        return "test";
    }
}
