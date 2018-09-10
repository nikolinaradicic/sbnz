package com.sbnz.sbnzproject.controller;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class WebSocketController {

	@SendTo("/topic")
	public String onReceivedMesage(String message){
    	String s = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return s +"- "+message;
    }
}