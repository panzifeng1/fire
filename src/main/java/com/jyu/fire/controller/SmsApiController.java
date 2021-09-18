package com.jyu.fire.controller;

import com.jyu.fire.service.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
@CrossOrigin
public class SmsApiController {
    @Autowired
    private SendSms sendSms;

    @GetMapping("/send/{phone}")
    public String code(@PathVariable("phone")String phone) {
        //调用方法发送短信
        //生成验证码
        String code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code",code);

        boolean isSend = sendSms.send("",phone);
        if (isSend) {
            return phone + ":" + code + " 发送成功";
        }
        return "发送失败";
    }
}
