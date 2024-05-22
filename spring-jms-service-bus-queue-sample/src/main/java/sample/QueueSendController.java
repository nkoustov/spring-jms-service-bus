/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueSendController {

    @Value("${spring.jms.servicebus.queue-name}")
    private String queueName;

    private static final Logger logger = LoggerFactory.getLogger(QueueSendController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/queue")
    public String postMessage(@RequestParam String message) {
        logger.info("Sending message");
        jmsTemplate.convertAndSend(queueName, new User(message));
        return message;
    }
}
