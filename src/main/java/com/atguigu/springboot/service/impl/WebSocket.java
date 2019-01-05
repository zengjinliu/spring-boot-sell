package com.atguigu.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Jason
 * @Description: 前端与后端之间的联系
 * @date 2018/12/6 13:19
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

	private Session session;

	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet();

	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		webSocketSet.add(this);
		log.info("【webSocket消息】 有新的连接，总数:{}",webSocketSet.size());
	}

	@OnClose
	public void onClose(){
		webSocketSet.remove(this);
		log.info("【webSocket消息】 断开连接，总数:{}",webSocketSet.size());
	}

	@OnMessage
	public void onMessage(String message){
		log.info("【webSocket消息】 收到客户端发来的消息:{}",message);
	}
	public void sendMessage(String message){
		for (WebSocket webSocket : webSocketSet) {
			log.info("【webSocket消息】 广播消息, message:{}",message);
			try {
				webSocket.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
