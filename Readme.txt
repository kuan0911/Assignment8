Implementation:
app:	When the calculation botton is pressed, the result String will store in a variable String oper	
	After that, a thread will be create and send the result String to server by socket.

server:	A window will be create when running the server program.
	Server class will keep accepting client socket and receive their messages.
	MyWindow class will put the received String on the window.

Discussion:
	I was no fimilar with the way for app to send messages to server.
	After some trials on different codes, I succeed in sending message to server.