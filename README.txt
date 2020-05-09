Start.java: This class is simply used to initialize everything and start the Agent and Chef threads. To start the program, run the main method in this file.
Table.java: This class is used for the 'shared memory' between the Agent and the Chef. The Agent places ingredients on the table, wheras the Chef takes ingredients from the table. This is the class that ensure mututal exclusion and conditional synchronization between the Agent and Chef.
Agent.java: This class places ingredients on the table when it is empty.
Chef.java: This class takes ingredients from the table when it is not empty, creates a sandwich and eats it.

Setup:
1. Import the contents into Eclipse as a Java project
2. Go to Start.java and run the main method

NOTE: Thread time measurement using the Management class was commented out.
