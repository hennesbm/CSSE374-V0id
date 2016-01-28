# CSSE374-V0id
In Mileston5
Brandon implemented the adapter pattern detection code and changed the structure of the current project.
Bo implemented the decorator pattern detection code and helped with testcases.
Chi did most of the test cases and draw the UML diagram for current milestone.

Instruction:
In order to use our pattern detector, you need to give the absolute path to the application. Also, you could give the packages name to limit the scope, which is optional. Then, after finishing executing the application, a brand new UML diagram will be generated and appeared automatically on the screen. It is saved under input_output folder of the current project.

Code Detection:
In order to detect specific patterns, we use visitors to go through files to get the information we need. We have a new interface called IPattern to store specific pattern information include the pattern name, the component name and also some other information related to the pattern. Then, we use our visitor to traverse all the files we want and add those IPattern to specific Declarations. 

New Custom Tests:
Chi and Bo made tests for both of those patterns.
