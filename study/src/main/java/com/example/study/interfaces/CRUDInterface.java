package com.example.study.interfaces;

import com.example.study.model.network.Header;

public interface CRUDInterface {

    public Header create();

    public Header read(Long id);

    public Header update();

    public Header delete(Long id);

}
