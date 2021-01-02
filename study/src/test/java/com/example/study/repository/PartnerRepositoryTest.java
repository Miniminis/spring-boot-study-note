package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends StudyApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        Partner partner = new Partner();
        partner.setName("Partner1");
        partner.setStatus("R");
        partner.setPartnerNumber("010-1234-5678");
        partner.setBusinessNumber("01456");
        partner.setCeoName("사장님1");
        partner.setRegisteredAt(LocalDateTime.now());
        partner.setCreatedAt(LocalDateTime.now());
        partner.setCreatedBy("TestServer");
        partner.setCategoryId(1L);

        Partner newPartner = partnerRepository.save(partner);
        Assertions.assertNotNull(newPartner);
    }
}
