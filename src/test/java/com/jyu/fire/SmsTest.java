package com.jyu.fire;

import com.jyu.fire.service.SendSms;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FireApplication.class)
public class SmsTest {
    @Autowired
    private SendSms sms;
    @Test
    public void testSend() {
        sms.send("18666340204");
    }
}
