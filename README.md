### Genetic Algorithms

For this assessment I ask you to optimise two functions (problems) using genetic algorithms. 
The assessment will be partially marked by machine. It is therefore very important that you follow the instructions given to you very carefully.
Failure to do so will result in substantial loss of marks.

To help you complete the assessment you are given 2 pieces of software: 
1. A a shell program code called Example.java. 
2. A compiled class file Assess.class that implements the problems. 
It contains 2 methods, namely double Assess.getTest1(double[]) and double[] Assess.getTest2(boolean[]).
These functions accept candidate solutions for problems 1 and 2 respectively and return the fitness of the proposed solutions.
The example code in Example.java demonstrates how to access them. 
Look at the code carefully to understand how it works. 
Additionally, it also contains a function checkIn(...). 
You will need to call this function at the end of your assignment in order to indicate the solutions to the two problems given to you.

_Extract from "co528 Artificial Intelligence Assessment 1" set by d.f.chu@kent.ac.uk_

#### Feedback

Dear Angus,

The results of the test harness were as follows:

1.txt   correct and optimal
2.txt   correct and optimal
3.txt   correct and optimal
4.txt   correct and optimal
5.txt   correct and optimal
6.txt   correct and optimal
7.txt   correct and optimal
8.txt   correct and optimal
9.txt   correct and optimal
10.txt  correct and optimal
11.txt  correct and optimal
12.txt  correct and optimal

take-away lesson:

I liked your idea of using a cache but, in general, for a cache not to become
a drain on resources you need to flush it. I think that the depth-first
nature of iterative deepening will mean that you'll have temporal locality
though so it might scale to larger problems if you tune it correctly.  But
it is nevertheless an original solution, which is something that I always like.

You are building the targets on each call to nextConfigs but this could be done
once, before nextConfigs is ever called.

You could have speed up iterative deepening by using the de ja vu check that
we introduced in the lectures for this search algorithm.

subtotals: 

    correct solutions = 12/12
    optimal solutions = 12/12
    operator          = 6/8
    search algorithm  = 3/4

**grandtotal: 33/36**