package com.skopei.demo.product;

import com.skopei.demo.abstraction.AbstractCtrl;
import com.skopei.demo.abstraction.ICRUD;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name="Product")
public class ProductCtrl extends AbstractCtrl<Product> {

    public ProductCtrl(@Qualifier("ProductDAO") ICRUD<Product> DAO) { super(DAO); }
}
