/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class AMQPQueueExample {

    public static void main( String[] args ) throws Exception {
        Connection connection = null;
        ConnectionFactory connectionFactory = new JmsConnectionFactory( "amqp://localhost:5672" );
        Session session = null;
        try {

            // Step 1. Create an amqp qpid 1.0 connection
            connection = connectionFactory.createConnection();

            // Step 2. Create a session
            session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );

            // Step 3. Bind in the source queue
            final Destination dest = session.createQueue( "jms.queue.CT_GENERAL_SOURCE_QUEUE" ); // Step 3. Create a sender
            MessageProducer sender = session.createProducer( dest );

            // Step 4. send a few simple message
            final TextMessage textMessage = session.createTextMessage( "Hello world" );
            sender.send( textMessage );

        } finally {
            if ( connection != null ) {
                // Step 5. close the connection
                connection.close();
            }

            if ( session != null ) {
                // Step 6. close the session                
                session.close();
            }

            System.out.println( "FINISHED" );
        }
    }
}