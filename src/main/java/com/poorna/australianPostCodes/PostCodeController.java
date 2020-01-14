package com.poorna.australianPostCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class PostCodeController {

    @Autowired
    private AustraliaPostCodeRepository australiaPostCodeRepository;

    @GetMapping("/")
    public List<AustralianPostCode> getPostCodes() {
        return (List<AustralianPostCode>) australiaPostCodeRepository.findAll();
    }
}
