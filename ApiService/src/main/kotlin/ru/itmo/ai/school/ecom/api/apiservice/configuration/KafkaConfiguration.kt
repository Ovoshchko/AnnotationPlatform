package ru.itmo.ai.school.ecom.api.apiservice.configuration

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.itmo.ai.school.ecom.api.apiservice.kafka.KafkaTaskDto


@EnableKafka
@Configuration
@EnableConfigurationProperties(KafkaProperties::class)
class KafkaConfiguration(
    private val kafkaProperties: KafkaProperties,
) {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, KafkaTaskDto> {
        val jsonDeserializer = JsonDeserializer(KafkaTaskDto::class.java).apply {
            setRemoveTypeHeaders(false)
            addTrustedPackages("ru.itmo.ai.school.ecom.api.apiservice.kafka")
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

