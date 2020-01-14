package com.poorna.australianPostCodes.Service;

import com.poorna.australianPostCodes.Entity.AustralianPostCode;
import com.poorna.australianPostCodes.Repository.AustraliaPostCodeRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AustraliaPostCodeService {

    @Autowired
    private AustraliaPostCodeRepository australiaPostCodeRepository;

    public List<AustralianPostCode> getAllPostCodes() {
        return (List<AustralianPostCode>) australiaPostCodeRepository.findAll();
    }

    @PostConstruct
    private void saveJsonToDb() {

        ArrayList<AustralianPostCode> postCodesArray = new ArrayList<>();

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

                // Change postcode length to 4 characters if the post code length is 3
                if (postcode.length() == 3) {
                    System.out.println("Initial post code      -> " + postcode);
                    postcode = "0" + postcode;
                    System.out.println("After change post code -> " + postcode);
                }

                AustralianPostCode ausPostCode = new AustralianPostCode();

//                ausPostCode.setId(id);
                ausPostCode.setPostcode(postcode);
                ausPostCode.setState(state);
                ausPostCode.setSuburb(suburb);
                ausPostCode.setLatitude(latitude);
                ausPostCode.setLongitude(longitude);

                postCodesArray.add(ausPostCode);
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        australiaPostCodeRepository.saveAll(postCodesArray);

        System.out.println("Total saved post codes -> " + postCodesArray.size());
    }
}
