package com.poorna.australianPostCodes.Service;

import com.poorna.australianPostCodes.Entity.AustralianPostCode;
import com.poorna.australianPostCodes.Repository.AustraliaPostCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AustraliaPostCodeService {

    @Autowired
    private AustraliaPostCodeRepository australiaPostCodeRepository;

    public List<AustralianPostCode> getAllPostCodes() {
        return (List<AustralianPostCode>) australiaPostCodeRepository.findAll();
    }
}
