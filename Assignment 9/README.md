PING is a network performance evaluation utility used to verify the reachability of a host on an IP network 
and to measure the round trip time (RTT) for messages sent from a sending host to a destination host.

The purpose of this assignment is to implement a PING server and a corresponding PING client that allows the client to measure its RTT to the server.
The functionality provided by these programs must be similar to that of the PING utility available in all modern operating systems. 
The key difference is that it uses UDP for communication between client and server, instead of the Internet Control Message Protocol (ICMP).
Furthermore, since the execution of programs will take place on a single host or on the local network and in both cases both latency 
and packet loss are negligible, the server must introduce an artificial delay and ignore some requests to simulate packet loss.


The Ping Client:
• Accepts two command line arguments: server name and port. If one or more arguments found, the client terminates, after 
  printing an error message of the type ERR -arg x, where x is the argument number.
• It uses UDP communication to communicate with the server and sends 10 messages to the server, with the following format:
                PING seqno timestamp
  where seqno is the sequence number of the PING (between 0-9) and the timestamp (in milliseconds) indicates when the message was sent.
• It does not send a new PING until it has received the echo of the previous PING, or a timeout has expired.
• Print each message sent to the server and the ping RTT or a "*" if the response was not received within 2 seconds after receiving 
  the tenth reply (or after its timeout), the client prints a summary similar to the one printed by PING UNIX:
                                          
                         ---- PING Statistics ----

            10 packets transmitted, 7 packets received, 30% packet loss

            round trip (ms) min / avg / max = 63 / 190.29 / 290
• The average RTT is printed with 2 digits after the decimal point.


The Ping Server:
• It is essentially an echo server: it sends any data it receives back to the sender
• Accepts a command line argument: the port, which is the one on which the server is active + an optional argument, the seed, 
  a long value used for generating latencies and packet loss. If any of the arguments are incorrect, print an error message 
  of the type ERR -arg x, where x is the argument number.
• After receiving a PING, the server determines whether to ignore the packet (simulating its loss) or echo it. The default 
  probability of packet loss is 25%.
• If it decides to echo the PING, the server waits for a random amount of time to simulate network latency
• prints the IP address and port of the client, the PING message and the action taken by the server following its reception 
  (PING not sent, or PING delayed by x ms).
  
  
  
