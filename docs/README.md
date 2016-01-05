UML Maker

created by: Brandon Hennessey, Chi Zhang, Bo Peng

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
4. Run UMLMaker as a Java Application. The finished UML diagram will be saved as a png under the given name in the docs folder of the UMLMaker project.