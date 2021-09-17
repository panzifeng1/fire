package com.jyu.fire.config;

import com.jyu.fire.emqx.MQTTConnect;
import com.jyu.fire.emqx.MQTTListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ListenerConfig {

    @Bean
    public MQTTListener applicationStartListener(){
        return new MQTTListener(new MQTTConnect());
    }
}