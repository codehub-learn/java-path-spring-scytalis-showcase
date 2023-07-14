package gr.codelearn.spring.showcase.app.transfer;

import gr.codelearn.spring.showcase.app.domain.chat.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatMessage {
	private String sender;
	private MessageType type;
	private String content;
}
