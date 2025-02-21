package Assigment1;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws InvalidLogin {

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        Professor prof1 = new Professor("Tammam Tillo", "tammam@iiitd.ac.in", "tammam", "Fri - 10 AM - 12 PM");
        Professor prof2 = new Professor("Sanjit Kaul", "sanjit@iiitd.ac.in", "sanjit", "Mon - 1 PM - 3 PM");

        // Create Students
        Student s1 = new Student("Naman", "naman@iiitd.ac.in", "naman", 1);
        Student s2 = new Student("Mitul", "mitul@iiitd.ac.in", "mitul", 1);
        Student s3 = new Student("vishal", "vishal@iiitd.ac.in", "vishal", 1);

        TeachingAssistant ta1 = new TeachingAssistant("sargun", "sargun@iiitd.ac.in", "sargun", 1);
        users.add(ta1);
        Adminstrator admin1 = new Adminstrator("Admin", "admin@iiitd.ac.in", "admin");

        users.add(prof1);
        users.add(prof2);
        users.add(s1);
        users.add(s2);
        users.add(s3);
        users.add(admin1);
        ArrayList<String> prereqs1 = new ArrayList<>();

        Course course1 = new Course("Introduction to Programming", "CS101", 4, 1, prof1, prereqs1, "Mon - 10 AM - 12 PM",new ArrayList<>(),2);

        ArrayList<String> prereqs2 = new ArrayList<>();
        prereqs2.add("CS101");
        Course course2 = new Course("Data Structures", "CS102", 4, 2, prof2, prereqs2, "Fri - 1 PM - 3 PM",new ArrayList<>(),2);

        ArrayList<String> prereqs3 = new ArrayList<>();
        Course course3 = new Course("Probability and Statistics", "MTH201", 2, 2, prof2, prereqs3, "Thu - 3 PM - 5 PM",new ArrayList<>(),2);
        ArrayList<String> prereqs4 = new ArrayList<>();
        prereqs4.add("CS102");
        Course course4 = new Course("Advance Programming", "CS102", 2, 3, prof1, prereqs4, "Thu - 3 PM - 5 PM",new ArrayList<>(),2);
        ArrayList<String> prereqs5 = new ArrayList<>();
        Course course5 = new Course("ADA", "CS201", 2, 4, prof1, prereqs5, "Thu - 3 PM - 5 PM",new ArrayList<>(),2);

        // Add courses to the system
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
//        customArrayList<Professor> feedback = new customArrayList<Professor>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the University Course Registration System");
            System.out.println("1) Enter the Application");
            System.out.println("2) Exit The Application");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Login as a:");
                System.out.println("1) Student");
                System.out.println("2) Professor");
                System.out.println("3) Administrator");
                System.out.println("4) Teaching Assistant");
                int n = sc.nextInt();
                if (n == 1) {
                    try {
                        // Login Process
                        System.out.println("Enter the email id:");
                        String emailId = sc.next();
                        System.out.println("Enter the password:");
                        String password = sc.next();

                        Student s = null;

                        // Search for the user (either a Student or TeachingAssistant)
                        for (User user : users) {
                            if ((user instanceof Student || user instanceof TeachingAssistant) &&
                                user.getEmail().equals(emailId) &&
                                user.getPassword().equals(password)) {
                                s = (Student) user;  // Assign the user to the student reference
                                break;
                            }
                        }

                        // If no matching user found, throw InvalidLoginException
                        if (s == null) {
                            throw new InvalidLogin("Wrong Email or Password");
                        }

                        // If login is successful, proceed with the student's actions
                        while (true) {
                            System.out.println("1) View Available Courses");
                            System.out.println("2) Register For Courses");
                            System.out.println("3) View Schedule");
                            System.out.println("4) Track Academic Progress");
                            System.out.println("5) Drop Course");
                            System.out.println("6) Submit Complaint");
                            System.out.println("7) Add Feedbacks");
                            System.out.println("8) Logout");

                            int n1 = sc.nextInt();
                            sc.nextLine();  // Clear buffer

                            if (n1 == 1) {
                                s.viewavailableCourses(courses);
                            } else if (n1 == 2) {
                                System.out.println("Enter the course code:");
                                String courseCode = sc.next();
                                Course c = findCourse(courses, courseCode);
                                if (c != null) {
                                    try {
                                        s.registerforCourse(c);
                                    }catch (InvalidCourseRegistration e){
                                        System.out.println(e.getMessage());
                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }

                                } else {
                                    System.out.println("Course not found.");
                                }
                            } else if (n1 == 3) {
                                s.viewSchedule();
                            } else if (n1 == 4) {
                                s.trackschedule();
                            } else if (n1 == 5) {
                                System.out.println("Enter the course code:");
                                String courseCode = sc.next();
                                Course c = findCourse(courses, courseCode);
                                if (c != null) {
                                    s.dropCourse(c);
                                } else {
                                    System.out.println("Course not found.");
                                }
                            } else if (n1 == 6) {
                                System.out.println("Enter the Complaint:");
                                String complaint = sc.nextLine();
                                s.complaint(complaint);
                            } else if (n1 == 7) {
                                s.Feedback();
                            } else if (n1 == 8) {
                                break;  // Logout
                            } else {
                                System.out.println("Invalid option. Please try again.");
                            }
                        }

                    } catch (InvalidLogin e) {
                        // Handle invalid login
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (n == 2) {
                    System.out.println("Enter the email id:");
                    String emailId = sc.next();
                    System.out.println("Enter the password:");
                    String password = sc.next();
                    Professor prof = null;
                    for (User user : users) {
                        if (user instanceof Professor && user.getEmail().equals(emailId) && user.getPassword().equals(password)) {
                            prof = (Professor) user;
                            break;
                        }
                    }
                    if (prof == null) {
                        throw new InvalidLogin("Wrong Email or Password");
                    }
                    else if (prof != null) {
                        while (true) {
                            System.out.println("1) Manage Courses");
                            System.out.println("2) View Enrolled Students");
                            System.out.println("3) View Feedbacks");
                            System.out.println("4) Assigned Ta");
                            System.out.println("5) View all Ta");
                            System.out.println("6) Logout");
                            int n1 = sc.nextInt();
                            if (n1 == 1) {
                                prof.managecourse(courses, sc);
                            } else if (n1 == 2) {
                                System.out.println("Enter course code to view students:");
                                String courseCode = sc.next();
                                Course c = findCourse(courses, courseCode);
                                if (c != null) {
                                    prof.enrolledstudent(c);
                                } else {
                                    System.out.println("Course not found.");
                                }
                            } else if (n1 == 3) {
                                prof.viewFeedback();
                            }else if (n1 == 4) {
                                System.out.println("Enter the ta Email");
                                TeachingAssistant ta = null;
                                String taemail = sc.next();
                                for (User user : users) {
                                    if(user instanceof TeachingAssistant && user.getEmail().equals(taemail)) {
                                        ta = (TeachingAssistant) user;
                                        break;
                                    }
                                }
                                if (ta == null) {
                                    throw new InvalidLogin("Wrong Email or Password");
                                }else {
                                    System.out.println("Enter Course code to assign ta:");
                                    String courseCode = sc.next();
                                    Course c = findCourse(courses, courseCode);
                                    if (c != null) {
                                        prof.assignedTA(ta,c);
                                    }
                                }

                            }
                            else if (n1 == 5) {
                                prof.getTa();
                            }else if (n1 == 6) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Wrong Username or Password");
                    }
                } else if (n == 3) {
                    Adminstrator admin = null;
                    for (User user : users) {
                        if (user instanceof Adminstrator) {
                            admin = (Adminstrator) user;
                        }
                    }
                    if (admin == null) {
                        throw new InvalidLogin("Wrong Email or Password");
                    }
                    else if (admin != null) {


                        System.out.println("Manage Course Catalog");
                        System.out.println("Manage Student Records");
                        System.out.println("Assign professor to course");
                        System.out.println("handle Complaints");
                        int n1 = sc.nextInt();
                        if (n1 == 1) {
                            admin.manageCourseCatalog(courses,users,sc);
                        }else if (n1 == 2) {
                            admin.manageStudentRecords(users,sc);
                        }else if (n1 == 3) {
                            admin.assignProfessorsToCourses(courses,users,sc);
                        }else if (n1 == 4) {
                            for(User u : users) {
                                if (u instanceof Student) {
                                    admin.handleComplaints(((Student) u).getComplaints(), sc);
                                }
                            }
                        }else {
                            System.out.println("Wrong Username or Password");
                        }
                    }
                } else if (n == 4) {
                    System.out.println("Enter the email id:");
                    String emailId = sc.next();
                    System.out.println("Enter the password:");
                    String password = sc.next();
                    TeachingAssistant ta = null;
                    for (User user : users) {
                        if (user instanceof TeachingAssistant && user.getEmail().equals(emailId) && user.getPassword().equals(password)) {
                            ta = (TeachingAssistant) user;
                            break;
                        }
                    }
                    if (ta == null) {
                        throw new InvalidLogin("Wrong Email or Password");
                    }
                    else if (ta != null) {
                        System.out.println("1) Assigned Grades");
                        System.out.println("2) View Courses");
                        System.out.println("3) logout");
                        int n1 = sc.nextInt();
                        if (n1 == 1) {
                            System.out.println("Enter course code:");
                            String courseCode = sc.next();
                            Course c = findCourse(courses, courseCode);
                            if (c != null) {
                                Student s = null;
                                System.out.println("Enter Student Email");
                                String studentEmail = sc.next();
                                System.out.println("Enter Student Password");
                                String studentPassword = sc.next();
                                for (User user : users) {
                                    if (user instanceof Student && user.getEmail().equals(studentEmail) && user.getPassword().equals(studentPassword)) {
                                        s = (Student) user;
                                        break;
                                    }
                                }
                                if (s == null) {
                                    throw new InvalidLogin("Wrong Email or Password");
                                }else {
                                    System.out.println("Enter the Grade");
                                    int grade = sc.nextInt();
                                    ta.assignedGrades(c,s,grade);
                                }
                            }
                        }else if (n1 == 2) {
                            ta.getCourses();
                        }else if (n1 == 3) {
                            break;
                        }
                    }

                }
            } else if (choice == 2) {
                break;
            }
        }

        sc.close();
    }

    public static Course findCourse(ArrayList<Course> courses, String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}
