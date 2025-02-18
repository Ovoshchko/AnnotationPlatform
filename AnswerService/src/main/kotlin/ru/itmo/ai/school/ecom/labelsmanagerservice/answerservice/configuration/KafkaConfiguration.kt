package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.configuration

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.CompletedTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.FilledTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.service.FilledTaskConsumer

@EnableKafka
@Configuration
@EnableConfigurationProperties(KafkaProperties::class)
class KafkaConfiguration(
    private val kafkaProperties: KafkaProperties,
) {

    @Bean
    fun producerFactory(): ProducerFactory<String, CompletedTaskDto> {
        val config: Map<String, Any> = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProperties.producer.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProperties.producer.valueSerializer,
            ProducerConfig.TRANSACTIONAL_ID_CONFIG to "completed-task-producer-1"
        )
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, CompletedTaskDto> {
        return KafkaTemplate(producerFactory())
    }
    @Bean
    fun consumerFactory(): ConsumerFactory<String, FilledTaskDto> {
        val jsonDeserializer = JsonDeserializer(FilledTaskDto::class.java).apply {
            setRemoveTypeHeaders(false)
            addTrustedPackages("*")
            setUseTypeMapperForKey(true)
        }

        return DefaultKafkaConsumerFactory(
            mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG to kafkaProperties.consumer.groupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java
            ),
            StringDeserializer(),
            jsonDeserializer
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, KafkaTaskDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, KafkaTaskDto>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}