package com.skopei.demo.product;

public class ProductDTO {

    public final String name;
    public final int quantity;
    public final long price;
    public final boolean deleted;

    ProductDTO(Product product) {
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.deleted = product.isDeleted();
    }
}
