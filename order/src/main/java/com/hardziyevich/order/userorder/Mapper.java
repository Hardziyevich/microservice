package com.hardziyevich.order.userorder;

public interface Mapper<T,V> {

    T mapTo(V v);

}
