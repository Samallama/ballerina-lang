/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.ballerinalang.net.jms.nativeimpl.endpoint.topic.publisher;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.CallableUnitCallback;
import org.ballerinalang.connector.api.Struct;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BMap;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.Receiver;
import org.ballerinalang.net.jms.AbstractBlockingAction;
import org.ballerinalang.net.jms.JmsConstants;
import org.ballerinalang.net.jms.JmsUtils;
import org.ballerinalang.net.jms.nativeimpl.endpoint.common.SessionConnector;
import org.ballerinalang.net.jms.utils.BallerinaAdapter;
import org.ballerinalang.util.exceptions.BallerinaException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Initialize the topic producer.
 *
 * @since 0.966
 */
@BallerinaFunction(
        orgName = "ballerina",
        packageName = "jms",
        functionName = "initTopicPublisher",
        receiver = @Receiver(type = TypeKind.OBJECT, structType = "TopicPublisher", structPackage = "ballerina/jms"),
        args = { @Argument(name = "session", type = TypeKind.OBJECT, structType = "Session"),
                @Argument(name = "destination", type = TypeKind.OBJECT)
                },
        isPublic = true
)
public class InitTopicPublisher extends AbstractBlockingAction {

    @Override
    public void execute(Context context, CallableUnitCallback callback) {
        Struct topicProducerBObject = BallerinaAdapter.getReceiverObject(context);

        Struct topicProducerConfig = topicProducerBObject.getStructField(JmsConstants.TOPIC_PUBLISHER_FIELD_CONFIG);
        String topicPattern = JmsUtils.getTopicPattern(topicProducerConfig);

        BMap<String, BValue> sessionBObject = (BMap<String, BValue>) context.getRefArgument(1);
        Session session = BallerinaAdapter.getNativeObject(sessionBObject,
                                                           JmsConstants.JMS_SESSION,
                                                           Session.class,
                                                           context);
        BMap<String, BValue> destinationBObject = (BMap<String, BValue>) context.getNullableRefArgument(2);
        Destination destinationObject = JmsUtils.getDestination(context, destinationBObject);

        if (JmsUtils.isNullOrEmptyAfterTrim(topicPattern) && destinationObject == null) {
            throw new BallerinaException("Topic pattern and destination cannot be null at the same time", context);
        }

        try {
            Destination topic = destinationObject != null ? destinationObject :
                    JmsUtils.getTopic(session, topicPattern);
            MessageProducer producer = session.createProducer(topic);
            topicProducerBObject.addNativeData(JmsConstants.JMS_PRODUCER_OBJECT, producer);
            topicProducerBObject.addNativeData(JmsConstants.SESSION_CONNECTOR_OBJECT,
                                               new SessionConnector(session));
        } catch (JMSException e) {
            BallerinaAdapter.throwBallerinaException("Error creating topic producer", context, e);
        }

    }
}
