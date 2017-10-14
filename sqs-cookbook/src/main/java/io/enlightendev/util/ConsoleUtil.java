package io.enlightendev.util;

import com.amazonaws.services.sqs.model.Message;

import java.util.List;
import java.util.Map;

public class ConsoleUtil {

    public static void printSQSMessages(List<Message> messages) {

        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            for (Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
        }

    }

    public static void print(String queueName, int totalMessageCount, long endTime, long startTime){

        System.out.format(
                "Queue Name: %3$s\n" +
                "Total Message send: %1$6d msgs \n" +
                "Send Latency: %2$6d ms\n",
                totalMessageCount,
                endTime - startTime,
                queueName);

    }

    public static void print2(String queueName, int totalMessageCount,
                              long receiveEndTime, long receiveStartTime,
                              long processingEndTime, long processingStartTime, boolean isMessage){

        System.out.format(
                " Queue Name: %4$s\n" +
                " Total Message processed: %1$6d msgs \n" +
                " Receive Wait Time: %2$6d ms\n" +
                        (isMessage ? "Message processed\n" : "No msg received\n") +
                " Processing Time: %3$6d ms\n",
                totalMessageCount,
                receiveEndTime - receiveStartTime,
                processingEndTime - processingStartTime,
                queueName);

    }
}
