<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
	<title>Spring Boot WebSocket Demo Chat Application</title>
	<link rel="stylesheet" href="/static/css/main.css"/>
</head>
<body>
<noscript>
	<h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>

<div id="username-page">
	<div class="username-page-container">
		<h1 class="title">Type your username to enter the Chatroom</h1>
		<p><em>Open extra browser tab(s) and/or instances to create a forum</em></p>

		<form id="usernameForm" name="usernameForm">
			<div class="form-group">
				<input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control"/>
			</div>
			<div class="form-group">
				<button type="submit" class="accent username-submit">Start Chatting</button>
			</div>
		</form>
	</div>
</div>

<div id="chat-page" class="hidden">
	<div class="chat-container">
		<div class="chat-header">
			<h2>Spring Boot WebSocket Demo Chat Application - Code.Hub</h2>
		</div>
		<div class="connecting">
			Connecting...
		</div>
		<ul id="messageArea">

		</ul>
		<form id="messageForm" name="messageForm">
			<div class="form-group">
				<div class="input-group clearfix">
					<input type="text" id="message" placeholder="Type a message..." autocomplete="off"
						   style="width:580px" class="form-control"/>
					<button type="submit" class="primary">Send</button>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/static/js/main.js"></script>
</body>
</html>
