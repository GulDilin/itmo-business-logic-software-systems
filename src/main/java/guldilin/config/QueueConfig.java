package guldilin.config;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import com.rabbitmq.jms.client.RMQMessageConsumer;
import guldilin.service.interfaces.JmsConsumer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.*;

@Configuration
public class QueueConfig {

    @Value("${spring.rabbitmq.blps.username}")
    private String username;
    @Value("${spring.rabbitmq.blps.password}")
    private String password;
    @Value("${spring.rabbitmq.blps.host}")
    private String host;
    @Value("${spring.rabbitmq.blps.port}")
    private Integer port;
    @Value("${spring.rabbitmq.blps.virtualhost}")
    private String vHost;
    @Value("${spring.rabbitmq.blps.timeout}")
    private Integer timeout;

    private final String QUEUE_NAME = "medicaments";

    @Bean
    public Queue recommendations() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Destination jmsDestination() {
        RMQDestination jmsDestination = new RMQDestination();
        jmsDestination.setAmqp(true);
        jmsDestination.setAmqpQueueName(QUEUE_NAME);

        return jmsDestination;
    }

    @Bean
    public ConnectionFactory jmsConnectionFactory(Destination jmsDestination, JmsConsumer jmsConsumer) throws JMSException {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost(vHost);
        connectionFactory.setOnMessageTimeoutMs(timeout);

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        session.createQueue(QUEUE_NAME);

        RMQMessageConsumer consumer = (RMQMessageConsumer) session.createConsumer(jmsDestination);
        consumer.setMessageListener(jmsConsumer);
        return connectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory jmsConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(jmsConnectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        return factory;
    }

}
