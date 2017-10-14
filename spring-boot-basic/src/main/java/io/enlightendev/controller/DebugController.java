package io.enlightendev.controller;

import com.amazonaws.util.EC2MetadataUtils;
import io.enlightendev.controller.util.WebDebug;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Juan on 5/9/17.
 */
@RestController
@RequestMapping("api/debug")
public class DebugController {

    @Autowired
    private Environment env;

    @Autowired
    private ConfigurableApplicationContext ctx;

    private static final Logger log = LogManager.getLogger(DebugController.class);

    @GetMapping("/get")
    public String getGet(HttpServletRequest request) throws IOException {

        log.info("Making debug get request.");
        return WebDebug.print(request);

    }

    @PostMapping("/post")
    public String getPost(HttpServletRequest request) throws IOException {

        log.info("Making debug post request.");

        String requestDetails = WebDebug.print(request);

        String body = WebDebug.printBody(request);

        return "" + requestDetails + " \n" + body;

    }

    @GetMapping("/config/{var:.+}")
    public String getConfig(@PathVariable("var") String var) throws IOException {

        log.info("Making config request.");

        return env.getProperty(var);

    }

    @RequestMapping(value = "/kill", method = RequestMethod.GET)
    public void kill(){
        ctx.close();
    }

    @RequestMapping(value = "/ec2", method = RequestMethod.GET)
    public String scratch(){

        String instanceId = EC2MetadataUtils.getInstanceId();

        if(instanceId == null){
            instanceId = "dev-instance-na";
        }

        return "Spring Boot AWS: 4.0 (" + instanceId + ").";
    }

}