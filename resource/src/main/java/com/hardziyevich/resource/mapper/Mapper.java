package com.hardziyevich.resource.mapper;

public interface Mapper<T,V> {

    T mapTo(V v);

}
