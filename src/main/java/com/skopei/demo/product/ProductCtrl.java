package com.skopei.demo.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Tag(name="Product")
public class ProductCtrl {

    private final ProductDAO productDAO;

    @PostMapping
    @Operation(summary = "create new product",
            responses = {
                    @ApiResponse(description = "product create success",
                            responseCode = "200")
            })
    public void createProduct(@RequestBody Product product) {
        productDAO.create(product);
    }

    @GetMapping
    @Operation(summary = "get product by id",
            responses = {
                @ApiResponse(description = "Get product success",
                        responseCode = "200",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    })
    @ResponseBody
    public Product getProduct(@RequestParam int id) {
        return productDAO.read(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Operation(summary = "get all products",
            responses = {
                    @ApiResponse(description = "Get products success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))))
            })
    public List<Product> getProducts() {
        return productDAO.readList();
    }

    @PatchMapping
    @Operation(summary = "update product",
            responses = {
                    @ApiResponse(description = "product update success",
                            responseCode = "200")
            })
    public void updateProduct(@RequestBody Product product) {
        productDAO.update(product);
    }

    @DeleteMapping
    @Operation(summary = "delete product",
        responses = {
            @ApiResponse(description = "product delete success",
            responseCode = "200")
    })
    public void deleteProduct(@RequestParam int id) {
        productDAO.delete(id);
    }
}