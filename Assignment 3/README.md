The IT laboratory of Polo Marzotto is used by three types of users: students, undergraduates and professors.

Each user must make a request to the tutor to access the laboratory.

The computers in the laboratory are numbered from 1 to 20.

The access requests are different depending on the type of user:
- The professors have exclusive access to the whole laboratory, as they need to use all the computers to carry out tests on the network;
- The undergraduates require the exclusive use of a single computer, identified by index i, as a particular software necessary 
  for the development of the thesis is installed on that computer;
- Students require the exclusive use of any computer.
Professors have priority over everyone in accessing the laboratory, graduate students have priority over students.

However, none can be interrupted while using a computer. Write a JAVA program that simulates user and tutor behavior. 

The program receives as input the number of students, undergraduates and professors who use the laboratory and activates a thread for each user. 

Each user logs into the lab k times, with k randomly generated. 

Simulate the time interval between one access and the next and the time spent in the laboratory using the sleep method. 

The tutor must coordinate access to the laboratory. The program must end when all users have completed their logins to the lab.
