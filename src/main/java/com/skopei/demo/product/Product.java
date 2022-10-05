package com.skopei.demo.product;

import com.skopei.demo.abstraction.DTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product extends DTO<Product>{

    private int id;
    private String name;
    private int quantity;
    private long price;
    private long creationDate;
    private long modDate;
    private boolean deleted;
}
