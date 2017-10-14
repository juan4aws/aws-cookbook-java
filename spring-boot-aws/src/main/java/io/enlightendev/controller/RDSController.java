package io.enlightendev.controller;

import io.enlightendev.controller.util.WebDebug;
import io.enlightendev.domain.Greeting;
import io.enlightendev.service.RDSService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *
 */
@RestController
@RequestMapping("/api/rds")
public class RDSController {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    RDSService rdsService;

    /**
     * Request params are appended to url
     * @return
     */
    @RequestMapping("/createWebRequestsTable")
    public String createWebRequestTable() {

        rdsService.createWebRequestTable();

        return "created table";

    }

    @GetMapping("/saveGetRequest")
    public @ResponseBody ResponseEntity<String> saveRequest(HttpServletRequest request) throws IOException {

        String requestDetails = WebDebug.print(request);

        rdsService.saveWebRequest(requestDetails);

        return new ResponseEntity<String>("request saved", HttpStatus.OK);

    }

}