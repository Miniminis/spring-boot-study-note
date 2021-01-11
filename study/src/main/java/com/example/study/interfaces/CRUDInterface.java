package com.example.study.interfaces;

import com.example.study.model.network.Header;

public interface CRUDInterface<Req, Res> {

    Header<Res> create(Header<Req> req);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> req);

    Header delete(Long id);
    
}
