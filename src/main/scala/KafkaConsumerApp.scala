import org.apache.kafka.clients.consumer.ConsumerConfig

object KafkaConsumerApp extends App {

  import org.apache.kafka.clients.consumer.KafkaConsumer

  import java.time.Duration
  import java.util.Properties

  val props = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "test")
  val consumer = new KafkaConsumer[String, String](props)

  import scala.jdk.CollectionConverters._

  consumer.subscribe(Seq("janusz").asJava)
  //consumer.subscribe(util.Arrays.asList("janusz"))

  while ( {
    true
  }) {
    val records = consumer.poll(Duration.ofMillis(100)) //.asScala       //foreach
    records.forEach(record => {
      println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}")
    })
  }
  consumer.close()
}