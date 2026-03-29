package fit.se2.group21.wangzhou.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // /topic is used for broadcasting to multiple subscribers (e.g., a chat room)
        config.enableSimpleBroker("/topic");

        // /app is the prefix for messages sent FROM the client TO the server
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // The endpoint the client uses to connect to the WebSocket server
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*") // Configure according to your CORS policy
                .withSockJS(); // Fallback for browsers that don't support WebSockets
    }
}