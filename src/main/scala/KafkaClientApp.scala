import java.time.Duration

object KafkaClientApp extends App {

  import org.apache.kafka.clients.consumer.KafkaConsumer
  import java.util
  import java.util.Properties

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "test")
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(util.Arrays.asList("adam", "skrzypek"))

  while ( {
    true
  }) {
    val records = consumer.poll(Duration.ofMillis(100))
      .forEach(record => {
        println("offset = %d, key = %s, value = %s%n", record.offset, record.key, record.value)
      })
  }
}
