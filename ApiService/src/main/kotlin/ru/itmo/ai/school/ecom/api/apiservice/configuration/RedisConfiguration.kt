package ru.itmo.ai.school.ecom.api.apiservice.configuration

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.*
import ru.itmo.ai.school.ecom.api.apiservice.model.Task

@Configuration
class RedisConfiguration(
    private val redisProperties: RedisProperties
) {

    @Bean
    @Primary
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

    @Bean
    fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, Task> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer: RedisSerializer<Task> = Jackson2JsonRedisSerializer(Task::class.java)

        val serializationContext = RedisSerializationContext.newSerializationContext<String, Task>(keySerializer)
            .value(valueSerializer)
            .build()

        return ReactiveRedisTemplate(factory, serializationContext)
    }
}

