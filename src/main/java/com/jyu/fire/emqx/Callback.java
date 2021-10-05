package com.jyu.fire.emqx;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyu.fire.mapper.ManagementMapper;
import com.jyu.fire.pojo.DeviceMsg;
import com.jyu.fire.pojo.Mqtt;
import com.jyu.fire.service.DeviceMsgService;
import com.jyu.fire.service.SendSms;
import com.jyu.fire.service.impl.SendSmsImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 常规MQTT回调函数
 */
@Slf4j
@Component
public class Callback implements MqttCallback {

//    @Autowired
    private SendSms sendSms = new SendSmsImpl();

    @Autowired
    private ManagementMapper managementMapper;
    @Autowired
    private DeviceMsgService deviceMsgService;

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
        ObjectMapper objectMapper = new ObjectMapper();
        String msg1 = new String(message.getPayload());
        Mqtt mqtt = objectMapper.readValue(msg1, Mqtt.class);
        //发送短信的内容拼接设备ID，消息内容，时间
        //手机号码由设备id在数据库中查询得到，暂时写死
        String deviceId = mqtt.getDeviceId();
        String phone = managementMapper.selectPhoneByDeviceId(deviceId);
        //将告警信息插入到device_msg中
        Integer type = Integer.valueOf(mqtt.getType());
        String msg = mqtt.getMsg();
        Integer Id = Integer.valueOf(deviceId);
        DeviceMsg deviceMsg = new DeviceMsg(Id, type, msg);
        deviceMsgService.insert(deviceMsg);
//        boolean success = sendSms.send(mqtt.getDeviceId() + "设备," + mqtt.getMsg() + ",时间" + mqtt.getTime(), "18666340204");
//        boolean success = sendSms.send(mqtt.getMsg(), "18666340204");
        boolean success = sendSms.send(mqtt.getMsg(), phone);
        if (success) {
            System.out.println("发送成功");
        }
    }
}