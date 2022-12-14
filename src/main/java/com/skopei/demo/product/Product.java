package com.skopei.demo.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/***
 * class annotations generate builder pattern and getters
 * Json annotations restricts api from performing certain operations and allows entity to function as its own DTO
 * javax validation annotations throw errors to the client upon violation
 */

@Builder
@Getter
public class Product {

    @JsonProperty(access = READ_ONLY)
    private int id;
    @NotNull @NotBlank
    private String name;
    private int quantity;
    private double price;
    @JsonIgnore
    private long creationDate;
    @JsonIgnore
    private long modDate;
    @JsonProperty(access = READ_ONLY)
    private boolean deleted;
}