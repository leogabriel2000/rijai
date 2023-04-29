package com.fortunelife.flicsrvr.controller;

import com.fortunelife.flicsrvr.model.AffiliateCategory;
import com.fortunelife.flicsrvr.model.AffiliateData;
import com.fortunelife.flicsrvr.model.ProductCategory;
import com.fortunelife.flicsrvr.model.ProductData;
import com.fortunelife.flicsrvr.service.AffiliateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AffiliateController {
    Logger logger = LoggerFactory.getLogger(AffiliateController.class);

    @Autowired
    private AffiliateService affiliateService;

    @RequestMapping("/api/map-affiliates")
    public ResponseEntity<List<AffiliateCategory>> getAffiliateCategories()
    {
        var headers = new HttpHeaders();

        try {
            List<AffiliateCategory> affiliateCategories = affiliateService.getAffiliateCategories();
            return ResponseEntity.ok().headers(headers).body(affiliateCategories);
        }
        catch( Exception ex)
        {
            return new ResponseEntity<List<AffiliateCategory>> (
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("/api/list-affiliates")
    public ResponseEntity<List<AffiliateData>> getAffiliates()
    {
        var headers = new HttpHeaders();

        try {
            List<AffiliateData> res = affiliateService.getAllAffiliateData();
            return ResponseEntity.ok().headers(headers).body(res);
        }
        catch( Exception ex)
        {
            return new ResponseEntity<List<AffiliateData>> (
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("api/affiliate")
    public ResponseEntity<AffiliateData> add(@RequestBody AffiliateData affiliate){
        logger.info("Input >> "+  affiliate.toString() );
        var headers = new HttpHeaders();
        try {
            AffiliateData affiliate_data = affiliateService.add(affiliate);
            return ResponseEntity.ok().headers(headers).body(affiliate_data);
        }
        catch( Exception ex)
        {
            return new ResponseEntity<AffiliateData> (
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("api/affiliate")
    public ResponseEntity<AffiliateData> update(@RequestBody AffiliateData data){
        logger.info("Input >> "+  data.toString() );

        var headers = new HttpHeaders();
        try {
            AffiliateData res = affiliateService.update(data);
            return ResponseEntity.ok().headers(headers).body(res);
        }
        catch( Exception ex)
        {
            return new ResponseEntity<AffiliateData> (
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @DeleteMapping("api/affiliate")
    public ResponseEntity<AffiliateData> delete(@RequestBody AffiliateData data){
        logger.info("Input >> "+  data.toString() );

        var headers = new HttpHeaders();
        try {
            affiliateService.delete(data);
            logger.info("Input >> Successfully deleted " +   data.toString());
            return ResponseEntity.ok().headers(headers).body(data);
        }
        catch( Exception ex)
        {
            return new ResponseEntity<> (
                    headers,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
