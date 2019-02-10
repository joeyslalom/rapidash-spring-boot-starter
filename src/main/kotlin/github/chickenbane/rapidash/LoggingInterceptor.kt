package github.chickenbane.rapidash

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets

class LoggingInterceptor : ClientHttpRequestInterceptor {

    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        logRequest(request, body)
        val response = execution.execute(request, body)
        logResponse(response)
        return response
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoggingInterceptor::class.java)

        private fun logRequest(request: HttpRequest, body: ByteArray) {
            log.debug("request method=${request.method} uri=${request.uri}")
            log.debug("request headers=${request.headers}")
            val requestBody = String(body, StandardCharsets.UTF_8)
            val traceBody = if (requestBody.isBlank()) "{blank request body}" else requestBody
            log.trace(traceBody)
        }

        private fun logResponse(response: ClientHttpResponse) {
            log.debug("response code=${response.statusCode} status=${response.statusText}")
            log.debug("response headers=${response.headers}")
            val body = StreamUtils.copyToString(response.body, StandardCharsets.UTF_8)
            val traceBody = if (body.isBlank()) "{blank response body}" else body
            log.trace(traceBody)
        }
    }
}

