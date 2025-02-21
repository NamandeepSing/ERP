package Assigment1;
import java.util.*;
public class Adminstrator extends User {
    public Adminstrator(String name, String emailid, String password) {
        super(name, emailid, password);
    }
    public void manageCourseCatalog(ArrayList<Course> courses, ArrayList<User> users, Scanner sc) {
        System.out.println("1) Add New Course");
        System.out.println("2) Remove Course");
        ArrayList<Student> enrolledStudents = new ArrayList<>();
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println("Enter course code:");
            String code = sc.nextLine();
            System.out.println("Enter course title:");
            String title = sc.nextLine();
            System.out.println("Enter course credits:");
            int credits = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.println("Enter the Semester");
            int sem = sc.nextInt();
            ArrayList<String> pre = new ArrayList<>();
            System.out.println("How many prerequisites?");
            int prereq = sc.nextInt();
            for (int i = 0; i < prereq; i++) {
                String prerequisites = sc.next();
                pre.add(prerequisites);
            }
            System.out.println("Enter course timing:");
            sc.nextLine();
            String timing = sc.nextLine();
            Professor assignedProfessor = null;
            System.out.println("Available Professors:");
            for (User user : users) {
                if (user instanceof Professor) {
                    System.out.println(user.getEmail() + " - " + user.getName());
                }
            }

            System.out.println("Enter the professor email to assign:");
            String professorEmail = sc.next();
            for (User user : users) {
                if (user instanceof Professor) {
                    if (user.getEmail().equals(professorEmail)) {
                        assignedProfessor = (Professor) user;
                        break;
                    }
                }
            }
            if (assignedProfessor == null) {
                System.out.println("Professor not found. Course creation aborted.");
                return;
            }
            System.out.println("Enter the Capacity of the course");
            int cap = sc.nextInt();
            // Create a new course and assign the professor
            Course newCourse = new Course(title, code, credits, sem,assignedProfessor, pre, timing,enrolledStudents,cap);
            courses.add(newCourse);

            System.out.println("Course added: " + title + " and assigned to Professor " + assignedProfessor.getName());
        } else if (choice == 2) {
            System.out.println("Enter course code to remove:");
            String code = sc.nextLine();
            Course course = Main.findCourse(courses,code);

            if (course != null) {
                courses.remove(course);
                System.out.println("Course removed: " + course.getTitle());
            } else {
                System.out.println("Course not found.");
            }
        }
    }

    public void manageStudentRecords(ArrayList<User> users, Scanner sc) {
        System.out.println("Enter student email to view records:");
        String email = sc.next();
        for (User user : users) {
            if ((user instanceof Student || user instanceof TeachingAssistant) &&
                user.getEmail().equals(email)) {
                Student student = (Student) user;
                System.out.println("1)Change the Student Name");
                System.out.println("2)Change the Student Email");
                System.out.println("3)Change the Student Password");
                System.out.println("4)Change the Student Semester");
                System.out.println("5) Assign the grades for the semester");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Enter student name:");
                    sc.nextLine();
                    String name = sc.nextLine();
                    student.setName(name);
                }else if (choice == 2) {
                    System.out.println("Enter student email:");
                    sc.nextLine();
                    email = sc.nextLine();
                    student.setEmail(email);

                }else if (choice == 3) {
                    System.out.println("Enter student password:");
                    sc.nextLine();
                    String password = sc.nextLine();
                    student.setPassword(password);
                }else if (choice == 4) {
                    for(Course c : student.takeCourses){
                        if (c.getSemester() == student.getcurrsem()){
                            System.out.println("Cannot change the Semester");
                            return;
                        }
                    }
                    int sem = student.getcurrsem();
                    sem++;
                    student.setCurrsem(sem);
                    System.out.println("Semester changed to " + sem);
                }else if (choice == 5) {
                    ArrayList<Course> toremoved = new ArrayList<>();
                    for (Course c : student.takeCourses){
                        if (c.getSemester() == student.getcurrsem()){
                            System.out.println("Enter the grade for this course " + c.getTitle());
                            int grade = sc.nextInt();
                            c.setGrade(grade);
                            if (grade>=4){
                                student.addCompletedCourse(c);
                                c.capacity++;
                                student.Grade.put(c,grade);
                                toremoved.add(c);
                            }
                        }
                    }
                    for (Course c : toremoved){
                        student.takeCourses.remove(c);
                    }
                }
            }
        }
    }

    public void assignProfessorsToCourses(ArrayList<Course> courses, ArrayList<User> users, Scanner sc) {
        System.out.println("Enter course code to assign a professor:");
        String courseCode = sc.next();
        Course course = Main.findCourse(courses,courseCode);

        if (course != null) {
            System.out.println("Enter professor email to assign:");
            String professorEmail = sc.next();
            for (User user : users) {
                if (user instanceof Professor && user.getEmail().equals(professorEmail)) {
                    Professor professor = (Professor) user;
                    course.setProfessor(professor);
                    System.out.println("Professor " + professor.getName() + " assigned to course " + course.getTitle());
                    break;
                }
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public void handleComplaints(ArrayList<Complaint> complaints, Scanner sc) {
        for (Complaint complaint : complaints) {
            System.out.println(complaint.getComplaint());
            System.out.println("Enter 'y' to resolve this complaint, 'n' to leave it unresolved.");
            String response = sc.next();
            if (response.equals("y")) {
                complaint.setStatus("Solved");
                complaints.remove(complaint);
                System.out.println("Complaint resolved.");
            }
        }
    }
}


