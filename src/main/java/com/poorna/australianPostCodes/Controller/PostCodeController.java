package com.poorna.australianPostCodes.Controller;

import com.poorna.australianPostCodes.Entity.AustralianPostCode;
import com.poorna.australianPostCodes.Service.AustraliaPostCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class PostCodeController {

    @Autowired
    private AustraliaPostCodeService australiaPostCodeService;

    @GetMapping("/")
    public List<AustralianPostCode> getPostCodes() {
        return australiaPostCodeService.getAllPostCodes();
    }
}
