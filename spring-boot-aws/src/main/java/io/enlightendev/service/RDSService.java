package io.enlightendev.service;

import com.amazonaws.util.EC2MetadataUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RDSService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String instanceId = null;

    private String createTableDML = "" +
            "CREATE TABLE web_requests (" +
            "    request_timestamp TIMESTAMP," +
            "    request_data VARCHAR(255)," +
            "    instance_id VARCHAR(50)" +
            ");";

    private String insertWebRequestSQL = "INSERT INTO web_requests (request_timestamp, request_data, instance_id) " +
            "VALUES (now(),'%s','%s');";

    private final Logger log = LogManager.getLogger(this.getClass());

    public void createWebRequestTable(){

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE IF EXISTS web_requests");

        jdbcTemplate.execute(createTableDML);
    }


    public void saveWebRequest(String requestData){

        String sql = String.format(insertWebRequestSQL, requestData, getInstanceId());
        jdbcTemplate.execute(sql);

    }

    private String getInstanceId(){

        if(instanceId == null) {
            instanceId = EC2MetadataUtils.getInstanceId();
        }

        return instanceId;

    }


}
