# Prim's Algorithm Project
April 19, 2020
Written by Emmanuel Borishade

## How it Works:
 1. Run MinimumSpanTree.java through terminal.
 2. Enter file path to a graph textfile.
 3. Enter starting vertex.
 4. Enjoy results.
 5. Enter new file, or **"exit"** to exit

## Status: 
 *Stable.

 *To Fix: 
 > depending on what style of graph you use (starting vertex at 0 vs starting vertex at 1), there may be 
 > an extra empty element printed out when result is returned. 
 > This could be "fixed" by adding an additional condition to while loop
 > but if one vertex is unreachable/disconnected from tree, 
 > all subsequent vertices will not print. 

 > Program crash on invalid graph path
 > Program crash on certain files: NumberFormatException

## Features:
* *stats* command: prints additional developer information for testing
* *print* command: prints out graph
* *normalize* command: normalize graph for better printing
* *prims* command: prints out minimum spanning tree
* *help* command: print commands
* *exit* command: exit program
