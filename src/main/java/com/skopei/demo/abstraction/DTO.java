package com.skopei.demo.abstraction;

import java.util.function.Function;

public abstract class DTO<T> {

//    Object asDTO(T t);
    @SuppressWarnings("unchecked")
     public Object asDTO(Function<T, Object> function) {
        return function.apply(((T) this));
    }
}
