Write a JAVA program that:
- Receive in input a filepath that identifies a directory D;
- Print the information of the contents of that directory and, recursively, all files contained in the subdirectories of D.

The program must be structured as follows:
- Activate a producer thread and a set of k consumer threads;
- The producer communicates with consumers through a queue;
- The producer recursively visits the given directory and possibly all subdirectories and puts in the queue the name of each directory found;
- Consumers take the names of directories from the queue and print their content;
- The queue must be created with a LinkedList. Remember that a Linked List is not a thread-safe structure. 
  From the JAVA API “Note that the implementation is not synchronized. 
  If multiple threads access a linked list concurrently, and at least one of the threads modifies the list structurally, it must be synchronized externally ".
