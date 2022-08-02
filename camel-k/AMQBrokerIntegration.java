// camel-k: dependency=camel-activemq

import org.apache.camel.builder.RouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.BindToRegistry;

public class AMQBrokerIntegration extends RouteBuilder {
    @BindToRegistry
    public ActiveMQConnectionFactory registerActiveMQConnectionFactory() {
        System.out.println("ActiveMQ Listener: STARTING...");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://ex-aao-hdls-svc:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("redhat");
        connectionFactory.setUseAsyncSend(false);
        connectionFactory.setClientID("Message Topic");
        connectionFactory.setConnectResponseTimeout(300);
        System.out.println("ActiveMQ Listener: STARTED");
        return connectionFactory;
    }

    @Override
    public void configure() throws Exception {
      from("activemq:topic:topic.prices")
        .log("body = ${body}")
        ;
    }
}
