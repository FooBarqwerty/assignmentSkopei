package com.skopei.demo.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Builder
@Getter
public class Product {

    @JsonProperty(access = READ_ONLY)
    private int id;
    private String name;
    private int quantity;
    private long price;
    @JsonIgnore
    private long creationDate;
    @JsonIgnore
    private long modDate;
    @JsonProperty(access = READ_ONLY)
    private boolean deleted;
}
