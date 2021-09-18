package com.jyu.fire.emqx;
 
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 项目启动 监听主题
 */
@Slf4j
@Component
public class MQTTListener implements ApplicationListener<ContextRefreshedEvent> {


//    private final MQTTConnect server;
//    private final Callback callback;
//
//    @Autowired
//    public MQTTListener(MQTTConnect server,Callback callback) {
//        this.server = server;
//        this.callback = callback;
//    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            MQTTConnect server = new MQTTConnect();
            server.setMqttClient("admin", "public", new Callback());
            server.sub("alert");
        } catch (MqttException e) {
            log.error(e.getMessage(), e);
        }
    }
}
 
 