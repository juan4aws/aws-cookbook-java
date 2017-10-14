package io.enlightendev.service;

import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SQSInfo {

    @Autowired
    AmazonSQS amazonSQSClient;

    @Value("${aws.sqs.simpleQueueURL}")
    private String simpleQueueURL;

    public void listQueues() {

        System.out.println("Listing all queues in your account.\n");
        for (String queueUrl : amazonSQSClient.listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
        }
        System.out.println();

    }

}