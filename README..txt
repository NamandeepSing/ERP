In Assigment Additional Functionality->
Student Class->Feedback Submission
Ta - > Assigning Grades, View Assigned Grades
Professor-> Assign Ta,

Concept Used->
Custom Genric The project uses a custom generic class CustomArrayList<T> to handle feedback submissions. This generic class allows the system to store different types of feedback, such as numeric ratings and textual comments.

TeachingAssistant inherit from student class 
Usage: Teaching assistants inherit all student functionalities but have additional features like managing grades and assisting professors.

InvalidLoginException: Thrown when a user tries to log in with incorrect credentials.
CourseFullException: Thrown when a student attempts to register for a course that is already full.
DropDeadlinePassedException: Thrown when a student tries to drop a course after the drop deadline.