package io.enlightendev.service;

import com.amazonaws.services.sqs.AmazonSQS;
import io.enlightendev.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@Service
public class SQSSender {

    @Autowired
    AmazonSQS amazonSQSClient;

    @Value("${aws.sqs.simpleQueueURL}")
    private String simpleQueueURL;

    public void sendSQSMessage() {

        int totalMessageCount = 0;

        while(true){

            String message = prepareMessage();

            long startTime = System.currentTimeMillis();
            amazonSQSClient.sendMessage(simpleQueueURL, message);
            long endTime = System.currentTimeMillis();

            totalMessageCount++;

            ConsoleUtil.print(simpleQueueURL, totalMessageCount, endTime, startTime);

        }


    }

    private static String prepareMessage() {

        return "This is a demo message sent through SQS: " + UUID.randomUUID().toString();

    }


}