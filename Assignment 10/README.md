Define a TimeServer that:
- Sends the date and time on a group of multicast dategroup at regular intervals.
- It waits for a time interval simulated by the sleep () method between one sending and the next.

The dategroup IP address is entered from the command line. Then define a TimeClient client that joins dategroup and receives, 
ten consecutive times, the date and time, displays them, then ends.
