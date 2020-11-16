Create a file containing objects representing a bank's checking accounts. 
Each current account contains the name of the account holder and a list of transactions. 
The movements recorded for a current account are related to the last 2 years, so they can be very numerous.

For each movement, the date and reason for the movement are recorded. 
The set of possible reasons is fixed: Bonfico, Accredito, Bollettino, F24, PagoBancomat.

Reread the file and find, for each possible reason, how many movements have that reason.

Design an application that activates a collection of threads. 
One of them reads the “current account” objects from the file and passes them, one at a time, to the threads present in a thread pool.

Each thread calculates the number of occurrences of each possible cause within that current account and updates a global counter.

At the end the program prints the total number of occurrences for each possible reason.

Use NIO for interacting with the file and JSON for serialization
