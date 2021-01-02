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
        partner.setName("Partner2");
        partner.setStatus("W");
        partner.setPartnerNumber("010-2222-2222");
        partner.setBusinessNumber("22222");
        partner.setCeoName("CEO2");
        partner.setRegisteredAt(LocalDateTime.now());

        Partner newPartner = partnerRepository.save(partner);
        Assertions.assertNotNull(newPartner);
    }
}
