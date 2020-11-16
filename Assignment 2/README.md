Simulate the flow of customers in a post office that has 4 branches. In the office there is:
- a large waiting room where each person can enter freely. When entering, each person takes the number 
  from the numbering machine and waits for their turn in this room;
- a second smaller room, located in front of the counters, which can only be entered by groups of k people.

A person then queues first in the first room, then goes into the second room.
Each person takes a different time for their counter operation.
Once the operation is finished, the person leaves the office.

Write a program where:
- The office is modeled as a JAVA class, in which a ThreadPool of a size equal to the number of branches is activated;
- The queue of people in the waiting room is explicitly managed by the program;
- The second queue (in front of the counters) is the one implicitly managed by the ThreadPool;
- Each person is modeled as a task, a task that must be assigned to one of the threads associated with the branches;
- Plan to let everyone into the post office at the beginning of the program;

Optional: foresee the case of a continuous flow of customers and the possibility that the operator closes the door itself 
after no customers appear at his counter in a certain time interval.
