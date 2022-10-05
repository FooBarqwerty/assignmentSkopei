package com.skopei.demo.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Tag(name="Product")
public class ProductCtrl {

    private final ProductDAO productDAO;

    @GetMapping
    @Operation(summary = "get product by id",
            responses = {
                @ApiResponse(description = "Get product success",
                        responseCode = "200",
                        content = @Content(mediaType = "application/json"))
    })
    @ResponseBody
    public Object getProduct(@RequestParam int id) {
        return productDAO.read(id)
                .asDTO(product -> String.format("""
                    {
                        "name": "%s",
                        "quantity": "%s",
                        "price": "%s"
                    }
                    """, product.getName(), product.getQuantity(), product.getPrice()));
    }

    @PostMapping
    @Operation(summary = "create new product",
            responses = {
                @ApiResponse(description = "product create success",
                responseCode = "200")
    })
    public void createProduct(@RequestBody Product product) {
        productDAO.create(product);
    }
}
