package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(IStreamClient.class)
@Slf4j
public class StreamReceiver {

//    @StreamListener(IStreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver:{}", message);
//    }

    /**
     * 接受 orderDTO 对象消息
     * @param message
     */
    @StreamListener(IStreamClient.INPUT)
    @SendTo(IStreamClient.INPUT2)
    public String process(OrderDTO message) {
        log.info("StreamReceiver:{}", message);
        return "received.";
    }

    @StreamListener(IStreamClient.INPUT2)
    public void process2(String message) {
        log.info("StreamReceiver2:{}", message);
    }
}
