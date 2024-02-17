package design.lld.notification.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTopicCreator {

  private final AdminClient adminClient;

  public KafkaTopicCreator(String bootstrapServers) {
    Properties config = new Properties();
    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    this.adminClient = AdminClient.create(config);
  }

  public void createTopic(String topicName, int numPartitions, short replicationFactor) {
    NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
    try {
      adminClient.createTopics(Collections.singleton(newTopic)).all().get();
      System.out.println("Topic created: " + topicName);
    } catch (InterruptedException | ExecutionException e) {
      // Handle exceptions, e.g., topic already exists, connection error
      System.err.println("Error creating topic: " + e.getMessage());
    }
  }

  // Close the AdminClient connection when done
  public void close() {
    adminClient.close();
  }
}
