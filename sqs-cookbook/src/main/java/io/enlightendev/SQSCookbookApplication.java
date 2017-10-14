package io.enlightendev;

import io.enlightendev.service.SQSReceiver;
import io.enlightendev.service.SQSSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SQSCookbookApplication implements CommandLineRunner {

    @Autowired
    SQSReceiver sqsReceiver;

    @Autowired
    SQSSender sqsSender;

    public static void main(String[] args) {
        SpringApplication.run(SQSCookbookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        sqsSender.sendSQSMessage();
//        sqsReceiver.readFromSQS();

    }

}
