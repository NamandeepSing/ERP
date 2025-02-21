package Assigment1;

import java.util.ArrayList;
import java.util.Scanner;

public class Professor extends User{
    Scanner sc = new Scanner(System.in);
    customArrayList<Integer> rating;
    customArrayList<String> comment;
    ArrayList<TeachingAssistant> ta;
    String officehour;
    public Professor(String name, String email, String password,String officehour) {
        super(name, email, password);
        this.officehour = officehour;
        this.rating = new customArrayList<>();
        this.comment = new customArrayList<>();
        this.ta = new ArrayList<>();
    }
    public void managecourse(ArrayList<Course> courses,Scanner sc) {
        System.out.println("Enter the course code to mange");
        sc.nextLine();
        String courseCode = sc.nextLine();
        Course course = Main.findCourse(courses,courseCode);
        if(course != null) {
            System.out.println("1) Change Timing");
            System.out.println("2) Change Credits");
            System.out.println("3) Change Prerequisite");
            int choice = sc.nextInt();
            if(choice == 1) {
                System.out.println("Enter Timing");
                sc.nextLine();
                course.setTiming(sc.nextLine());
            }else if(choice == 2) {
                System.out.println("Enter Credits");
                course.credits = sc.nextInt();
            }else if(choice == 3) {
                System.out.println("Enter Prerequisite");
                System.out.println("1)Add prerequisite or 2) for delete prerequisite");
                if(sc.nextInt() == 1) {
                    sc.nextLine();
                    course.prerequisites.add(sc.nextLine());
                }else if(sc.nextInt() == 2) {
                    sc.nextLine();
                    course.prerequisites.remove(sc.nextLine());
                }
            }
        }
    }
    public void enrolledstudent(Course course) {
        for(Student s : course.getEnrolledStudents()){
            System.out.println(s.getName());
        }
    }
    public String getOfficehour(){
        return officehour;
    }
    public void setOfficehour(String officehour){
        this.officehour = officehour;
    }
    public void addFeedback(int rate,String com){
        rating.add(rate);
        comment.add(com);
    }
    public void viewFeedback(){
        System.out.println("Ratings->");
        for (int i= 0; i<rating.size(); i++){
            System.out.println(rating.get(i));
        }
        System.out.println("Comments->");
        for (int i= 0; i<comment.size(); i++){
            System.out.println(comment.get(i));
        }

    }
    public void assignedTA(TeachingAssistant Ta,Course course) {
        if (Ta.completedCourse.contains(course)){
            Ta.courses.add(course);
            ta.add(Ta);
            System.out.println("Assigned TA");
        }else {
            System.out.println("Cannot assign TA as they cannot completed the course");
        }
    }
    public void getTa(){
        for (int i= 0; i<ta.size(); i++){
            System.out.println(ta.get(i).getName());
        }
    }

}
