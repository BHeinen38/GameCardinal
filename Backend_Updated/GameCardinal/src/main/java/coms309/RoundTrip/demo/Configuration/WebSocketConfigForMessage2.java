package coms309.RoundTrip.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 * What happens here is that the serverendpoint -- in thos case it is
 * the websocket endpoint handler is registered with SPRING
 * so that requests to ws:// will be honored.
 *
 * @author Bailey Heinen
 *
 */

@Configuration
public class WebSocketConfigForMessage2 {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}

