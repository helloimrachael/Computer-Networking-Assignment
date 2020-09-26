Program that activates a thread T that performs the approximate calculation of PI.The main program receives two parameters as input from the command line: one indicating the degree of accuracy for the calculation of PiGreco and the other indicating the maximum waiting time after which the main program interrupts the thread T.

Thread T does an infinite loop for the PI computation using the Gregory-Leibniz series (PI = 4/1 – 4/3 + 4/5 - 4/7 + 4/9 - 4/11 ...).

The thread exits the loop when one of the following two conditions is true:

1) the thread was interrupted;

2) the difference between the estimated value of PI and the Math.PI value (from the JAVA library) is less than accuracy.


