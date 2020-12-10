Design a Client / Server application for managing registrations at a congress. The organization of the congress provides 
the speakers of the various sessions with an interface through which to register for a session, and the ability to view 
the programs of the various days of the congress, with the interventions of the various sessions. 

The server keeps the programs of the 3 days of the congress, each of which is stored in a data structure like the one shown below, in which 
each line corresponds to a session (12 for each day in all). For each session, the names of the speakers who have registered are stored (maximum 5).

The client can request operations to:

- register a speaker at a session;
- obtain the program of the congress;

The client forwards requests to the server through the RMI mechanism. For each possible operation, provide for the management of any 
abnormal conditions (for example the request for registration to a non-existent day and / or session or for which all the intervention 
spaces have already been covered).

The client is implemented as a cyclic process that continues making synchronous requests until all user needs are exhausted. 
Establish an appropriate termination condition for the request process.
