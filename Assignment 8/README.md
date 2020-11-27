Write an echo server program using the NIO java library and, in particular, the Selector and channels in non-blocking mode, 
and an echo client program, using NIO (also fine with blocking mode).

The server accepts requests for connections from clients, receives messages sent by clients and sends them back 
(possibly adding "echoed by server" to the received message).

The client reads the message to be sent from the console, sends it to the server and displays what it has received from the server.
