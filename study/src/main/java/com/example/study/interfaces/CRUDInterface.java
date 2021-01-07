package com.example.study.interfaces;

import com.example.study.model.network.Header;

public interface CRUDInterface<Req, Res> {

    public Header<Res> create(Header<Req> req);

    public Header<Res> read(Long id);

    public Header<Res> update(Header<Req> req);

    public Header delete(Long id);

}
