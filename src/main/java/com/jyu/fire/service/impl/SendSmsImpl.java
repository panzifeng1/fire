package com.jyu.fire.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jyu.fire.service.SendSms;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class SendSmsImpl implements SendSms {

    @Override
    public boolean send(String msg,String phoneNum) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIWF9QiqONP95E", "wcr5Kmi2yuTuslRIUJIpvsy2957lmn");
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();

        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //自定义参数(手机号，验证码，签名，模板)
        //发送手机号
        request.putQueryParameter("PhoneNumbers", phoneNum);
        //签名名称 【】
        request.putQueryParameter("SignName", "物勒");
        //模板管理->模板code
        request.putQueryParameter("TemplateCode", "SMS_78565161");
        //构建一个短信验证码
        String code = UUID.randomUUID().toString().substring(0, 4);
        HashMap map = new HashMap<>();
        /**
         * 发布的短信内容
         */
        map.put("password",msg);
        map.put("phone",phoneNum);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
