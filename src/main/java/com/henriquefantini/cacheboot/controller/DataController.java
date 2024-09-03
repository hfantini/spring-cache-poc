package com.henriquefantini.cacheboot.controller;

import com.henriquefantini.cacheboot.model.Data;
import com.henriquefantini.cacheboot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping(path = "data/{id}", method = RequestMethod.GET)
    public ResponseEntity getData(@PathVariable String id) throws Exception
    {
        List<Data> response = dataService.getData(id);
        return ResponseEntity.status(200).body(response);
    }

    @RequestMapping(path = "manual/{id}", method = RequestMethod.GET)
    public ResponseEntity getManualData(@PathVariable String id) throws Exception
    {
        List<Data> response = dataService.getManualData(id);
        return ResponseEntity.status(200).body(response);
    }

    @RequestMapping(path = "interceptor/{id}", method = RequestMethod.GET)
    public ResponseEntity getDataCustomInterceptor(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                   @PathVariable String id) throws Exception
    {
        List<Data> response = dataService.getDataInterceptor(id, authorization);
        return ResponseEntity.status(200).body(response);
    }

    @TimeToLive()
    @RequestMapping(path = "customttl/{id}", method = RequestMethod.GET)
    public ResponseEntity getDataCustomTTL(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                                   @PathVariable String id) throws Exception
    {
        List<Data> response = dataService.getDataCustomTTL(id);
        return ResponseEntity.status(200).body(response);
    }

    @RequestMapping(path = "data", method = RequestMethod.DELETE)
    public ResponseEntity clearEntireCache() {

        dataService.clearEntireCache();
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "data/{key}", method = RequestMethod.DELETE)
    public ResponseEntity clearCacheByKey(@PathVariable String key) {

        dataService.clearCacheByKey(key);
        return ResponseEntity.status(200).build();
    }
}
