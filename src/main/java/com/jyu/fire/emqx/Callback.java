package com.jyu.fire.emqx;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyu.fire.pojo.Mqtt;
import com.jyu.fire.service.SendSms;
import com.jyu.fire.service.impl.SendSmsImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 常规MQTT回调函数
 */
@Slf4j
public class Callback implements MqttCallback {

    private SendSms sendSms = new SendSmsImpl();

    /**
     * MQTT 断开连接会执行此方法
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("断开了MQTT连接 ：{}", throwable.getMessage());
        log.error(throwable.getMessage(), throwable);
    }
 
    /**
     * publish发布成功后会执行到这里
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("发布消息成功");
    }
 
    /**
     * subscribe订阅后得到的消息会执行到这里
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //  TODO    此处可以将订阅得到的消息进行业务处理、数据存储
        log.info("收到来自 " + topic + " 的消息：{}", new String(message.getPayload()));
        //发送短信给用户
//        ObjectMapper objectMapper = new ObjectMapper();
//        String msg = new String(message.getPayload());
//        Mqtt mqtt = objectMapper.readValue(msg, Mqtt.class);
        //发送短信的内容拼接设备ID，消息内容，时间
        //手机号码由设备id在数据库中查询得到，暂时写死


//        sendSms.send(mqtt.getDeviceId()+"设备,"+mqtt.getMsg()+",时间"+mqtt.getTime(),"18666340204");
    }
}