package gr.codelearn.spring.showcase.app.config;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.chat.MessageType;
import gr.codelearn.spring.showcase.app.transfer.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener extends BaseComponent {
	private final SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");
		if (username != null) {
			logger.info("user disconnected: {}", username);
			messagingTemplate.convertAndSend("/topic/public", ChatMessage.builder().type(MessageType.LEAVE)
																		 .sender(username).build());
		}
	}

}
