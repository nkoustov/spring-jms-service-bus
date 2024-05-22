/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiveController {

    @Value("${spring.jms.servicebus.queue-name}")
    private final String queueName = "queue";

    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);

    @JmsListener(destination = queueName, containerFactory = "myQueueFactory")
    public void receiveQueueMessage(User user) {
        logger.info("Receiving message from queue: {}", user.getName());
    }

}
