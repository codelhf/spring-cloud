package com.imooc.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface IStreamClient {

    String INPUT = "myMessage";
    String OUTPUT = "myMessageOut";
    String INPUT2 = "myMessage2";
    String OUTPUT2 = "myMessageOut2";

    @Input(IStreamClient.INPUT)
    SubscribableChannel input();

    @Output(IStreamClient.OUTPUT)
    MessageChannel output();

    @Input(IStreamClient.INPUT2)
    SubscribableChannel input2();

    @Output(IStreamClient.OUTPUT2)
    MessageChannel output2();
}
