package com.example.study.service;

import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ItemApiService extends BaseApiService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> req) {
        ItemApiRequest itemApiRequest = req.getData();

        Item item = Item.builder()
                .status(itemApiRequest.getStatus())
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .brandName(itemApiRequest.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(itemApiRequest.getPartnerId()))       /*TODO. partnerId 존재하지 않는 경우에 대하여 예외처리 필요! */
                .build();

        Item newItem = baseRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> optionalItem = baseRepository.findById(id);
        return optionalItem.map(item -> response(item))
                .orElseGet(() -> Header.ERROR("일치하는 아이템이 없습니다!"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> req) {
        ItemApiRequest itemApiRequest = req.getData();

        Optional<Item> foundItem = baseRepository.findById(itemApiRequest.getId());
        return foundItem
                .map(item -> {
                    item.setStatus(itemApiRequest.getStatus())
                            .setName(itemApiRequest.getName())
                            .setTitle(itemApiRequest.getTitle())
                            .setContent(itemApiRequest.getContent())
                            .setPrice(itemApiRequest.getPrice())
                            .setBrandName(itemApiRequest.getBrandName());
                    return item;
                })
                .map(newItem -> baseRepository.save(newItem))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("아이템이 존재하지 않습니다!"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = baseRepository.findById(id);
        return optional
                .map(foundItem -> {
                    baseRepository.delete(foundItem);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("아이템이 존재하지 않습니다!"));
    }

    public Header<ItemApiResponse> response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(itemApiResponse);
    }
}
