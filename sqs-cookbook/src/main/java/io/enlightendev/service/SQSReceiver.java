package io.enlightendev.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import io.enlightendev.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SQSReceiver {

    @Autowired
    AmazonSQS amazonSQSClient;

    @Value("${aws.sqs.simpleQueueURL}")
    private String simpleQueueURL;

    public void readFromSQS() {

        int totalMessageCount = 0;

        while(true){

            long receiveStartTime = System.currentTimeMillis();

            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(simpleQueueURL)
                    .withMaxNumberOfMessages(1)
                    .withWaitTimeSeconds(1);


            List<Message> messages = amazonSQSClient.receiveMessage(receiveMessageRequest).getMessages();

            long receiveEndTime = System.currentTimeMillis();

            Message message = messages.isEmpty() ? null : messages.get(0);

            long processingTime = System.currentTimeMillis();
            
            if(message != null) {
                processMessage(message);

                // Delete a message
                String messageReceiptHandle = messages.get(0).getReceiptHandle();
                amazonSQSClient.deleteMessage(new DeleteMessageRequest(simpleQueueURL, messageReceiptHandle));
                
                totalMessageCount++;
            }

            long processingEndTime = System.currentTimeMillis();

            ConsoleUtil.print2(simpleQueueURL,
                    totalMessageCount,
                    receiveEndTime, receiveStartTime,
                    processingEndTime, processingTime,
                    message != null);
        }

    }

    private void processMessage(Message message) {
        try{
            Thread.sleep(50);
        }catch (InterruptedException ie){

        }
    }

}
