package com.poorna.australianPostCodes.Controller;

import com.poorna.australianPostCodes.Entity.AustralianPostCode;
import com.poorna.australianPostCodes.Service.AustraliaPostCodeService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
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

    @GetMapping("/json")
    public List<AustralianPostCode> getPostCodesFromJson() {

        ArrayList<AustralianPostCode> postCodes = new ArrayList<>();

        try {

            JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader("postcodes.json"));
            Iterator iterator = jsonArray.iterator();

            while (iterator.hasNext()) {

                JSONObject postCode = (JSONObject) iterator.next();

                Long id = (Long) postCode.get("id");
                String postcode = (String) postCode.get("postcode");
                String state = (String) postCode.get("state");
                String suburb = (String) postCode.get("suburb");
                Double latitude = (Double) postCode.get("latitude");
                Double longitude = (Double) postCode.get("longitude");

                AustralianPostCode ausPostCode = new AustralianPostCode();

                ausPostCode.setId(id);
                ausPostCode.setPostcode(postcode);
                ausPostCode.setState(state);
                ausPostCode.setSuburb(suburb);
                ausPostCode.setLatitude(latitude);
                ausPostCode.setLongitude(longitude);

                postCodes.add(ausPostCode);
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return postCodes;
    }
}
