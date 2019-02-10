package github.chickenbane.rapidash

import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory

@Configuration
class LoggingRestTemplateAutoConfig {

    @Bean
    fun restTemplateCustomizer(): RestTemplateCustomizer =
            RestTemplateCustomizer { restTemplate ->
                with(restTemplate) {
                    // getRequestFactory() must be called before adding interceptors
                    requestFactory = BufferingClientHttpRequestFactory(requestFactory)
                    interceptors.add(LoggingInterceptor())
                }
            }
}