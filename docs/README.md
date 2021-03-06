UML Maker

created by: Brandon Hennessey, Chi Zhang, Bo Peng

M1

--------------------

Design:

The design of our UML maker tool uses the ASM Java parsers to go through and collect all the information about the given classes and adds it to our model object. Once the model is complete and the parsers are complete, we use our own visitors to go through the model and translate the information to a text file in the format required by GraphViz to generate a UML diagram. Finally, the tool runs GraphViz using the generated text file, thus creating our UML diagram of the given classes and saving the png file for later use.

--------------------

Responsibilities:

- Brandon Hennessey:
	Set up basic logic similar to the "CarApp" example given in class.
	Worked on gathering class declaration information.
	
- Chi Zhang:
	Formatting for the printing of information to the text file.
	Worked on gathering method information.
	
- Bo Peng:
	Running the text file through GraphViz using the console to generate the png of the UML diagram.
	Worked on gathering field information.
	
--------------------

Instructions:

1. Replace the CLASSES field in UMLMaker with the list of classes that are wanting to be used in the diagram.
2. Make sure the project that you are taking the classes from in on the current build path for the UMLMaker project.
3. Replace "title" with desired UML diagram name for saving purposes.
	*NOTE: No spaces or special symbols can be in the file name. Use _ for spaces.
4. Run UMLMaker as a Java Application. The finished UML diagram will be saved as a png under the given name in the docs folder of the UMLMaker project.

--------------------
--------------------

M2

--------------------

Design:

We enhance  our class diagrams with minimal modifications. The design basically retrieves data from classes, especially focuses on types of the variables and keeps track of the relationship which generates corresponding arrows. Previous design almost works perfectly. We did make a few modifications to make it work.

--------------------

Responsibilities:

- Brandon
	worked on aggregation arrows. Help cleaning messy png graphs.
	
- Chi
	Created UML diagrams and worked on use arrows.
	
- Bo
	Worked on use arrows and aggregation arrows. Debugging and testing.
	
--------------------

Instructions:
	1. Run UMLMaker.java. The finished UML diagram will be saved as a png under the given name in the docs folder of the UMLMaker project.
	
--------------------
--------------------

M3

--------------------

Design:

We attempted to get the information needed from the ClassReader in order to show the method calls and their order in a Sequence Diagram generated using SDEdit. This involved a pattern of generating the document similar to our UMLMaker.

-------------------

Responsibilities:

- Brandon
	Worked on making a new visitor to attain the needed information from our ClassReader
	
- Chi
	Worked on identifying the needed information in order to correctly generate and make the various calls on the sequence diagram
	
- Bo
	Set up the generator for the text file to be put into SDEdit and finalized the formatting of the information
	
-------------------

Instructions:
	*Instructions are similar to that of our UMLMaker.
	1. Copy/paste the directory name into the corresponding spot in the SDEdit file. Be sure to add extra backslashes where needed. If only certain packages are needing to be used, specify the package name after the directory name.
	2. Place the name of the generated png file in the title spot.
	3. Run SDEdit. The final Sequence Diagram will be under docs and titled with the given name.
	
--------------------
--------------------

M4

--------------------
Design:

We added the functionality to our UMLMaker to identify Singleton instances within the code. These are then highlighted blue on the resulting diagram and show the correct composition arrow pointing back to the given class.

--------------------

Responsibilities:

- Brandon
	Created IRelations and the corresponding Singleton relation
	
- Chi
	Created the comparison diagrams
	Aided Bo in fixing the bugs from Milestone 3
	
- Bo
	Worked on fixing the bugs from Milestone 3 and displaying the information in the Sequence Diagrams correctly
	
--------------------

Instructions:
	* The operating instructions are the exact same as previous milestones.
	
--------------------
--------------------

M5 & M6

--------------------
![alt tag](https://github.com/hennesbm/CSSE374-V0id/blob/master/docs/Project%20UML%20Clean.png)
Code Detection:
	In order to detect specific patterns, we use visitors to go through files to get the information we need. We have a new interface called IPattern to store 	specific pattern information include the pattern name, the component name and also some other information related to the pattern. Then, we use our visitor to 	traverse all the files we want and add those IPattern to specific Declarations.
	
-------------------

Responsibilities:

- Brandon 
	implemented the adapter pattern detection code and changed the structure of the current project.

- Bo 
	implemented the decorator pattern detection code and helped with testcases.
	implemented the composite pattern detection code and helped with testcases.

- Chi 
	did most of the test cases and draw the UML diagram for current milestone.
	updated manually generated UML diagrams
	
--------------------

Instructions:
	In order to use our pattern detector, you need to give the absolute path to the application. Also, you could give the packages name to limit the scope, which 	is optional. Then, after finishing executing the application, a brand new UML diagram will be generated and appeared automatically on the screen. It is saved 	under input_output folder of the current project.
	
--------------------

New Custom Tests:
Chi and Bo made tests for both of those patterns.

-------------------

M6

![alt tag](https://github.com/hennesbm/CSSE374-V0id/blob/master/docs/Project%20UML%20M6.png?raw=true)
Added Composite pattern detection. Enable detection for leaf, component and composite.
-------------------

Responsibilities:

- Brandon 
refactor the design

- Bo 
implemented the composite pattern detection code and helped with testcases.

- Chi 
did most of the test cases and update the UML diagram for current milestone.
updated manually generated UML diagrams

--------------------

Instructions:
In order to use our pattern detector, you need to give the absolute path to the application. Also, you could give the packages name to limit the scope, which is optional. Then, after finishing executing the application, a brand new UML diagram will be generated and appeared automatically on the screen. It is saved under input_output folder of the current project.

--------------------

New Custom Tests:
Chi and Bo made tests for composite pattern.
