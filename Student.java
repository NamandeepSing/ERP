package Assigment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student extends User{
    static String currday = "15-07-2024";
    static String deadline = "15-08-2024";
    Scanner sc = new Scanner(System.in);
    public ArrayList<Course> takeCourses;
    public ArrayList<Course> completedCourse;
    int totalcredit;
    int currsem;
    ArrayList<Complaint> complaints;
    customArrayList<Integer> rating;
    customArrayList<String> comment;
    HashMap<Course,Integer> Grade;
    public Student(String name,String email,String password,int currsem) {
        super(name,email,password);
        takeCourses = new ArrayList<>();
        completedCourse = new ArrayList<>();
        complaints = new ArrayList<>();
        totalcredit = 0;
        this.currsem = currsem;
        this.rating = new customArrayList<>();
        this.comment = new customArrayList<>();
        this.Grade = new HashMap<>();
    }
    public void viewavailableCourses(ArrayList<Course> courses){
        for(Course c : courses){
            if (c.getSemester() == currsem) {
                System.out.println("Course Title->" + c.getTitle() + " Course code-> " + c.getCode()+ " Semester-> " + c.getSemester() + "Professor Name -> " + c.professor.getName() + "Timmings -> " + c.getTiming());
            }
        }
    }
    public void registerforCourse(Course course) throws InvalidCourseRegistration {
        if (course.capacity<=0){
            throw new InvalidCourseRegistration("Course capacity is full");
        }

        if (takeCourses.contains(course)) {
            System.out.println("You already registered a course");
            return;
        }
        if (course.getSemester() != currsem) {
            System.out.println("You are only register for the current semester");
            return;
        }
        for(String prerequiste : course.getPrerequisites()){
            boolean flag2 = false;
            for (Course Complete : completedCourse) {
                if(Complete.getCode().equals(prerequiste)){
                    flag2 = true;
                    break;
                }
            }
            if(!flag2){
                System.out.println("You have not completed the prerequisite");
                return;
            }
        }
        if (totalcredit+course.getCredits()>20){
            System.out.println("you cannot register for this course");
            return;
        }
        takeCourses.add(course);
        totalcredit += course.getCredits();
        course.capacity--;
        course.enrolledStudents.add(this);
        System.out.println("You have successfully registered for this course");
    }
    public void dropCourse(Course course){
        try{
            if (currday.compareTo(deadline) <= 0) {
                takeCourses.remove(course);
                course.getEnrolledStudents().remove(this);
                totalcredit -= course.getCredits();
                course.capacity++;
                System.out.println("Successfully dropped the course: " + course.getTitle());
            } else {
                throw new CourseDropFailure("Cannot drop the course. The drop deadline has passed.");
            }
        } catch (CourseDropFailure e) {
            System.out.println(e.getMessage());
        }

    }
    public void viewSchedule(){
        for(Course c : takeCourses){
            System.out.println(c.getTiming());
        }
    }
    public void complaint(String complaint){
        complaints.add(new Complaint(complaint));
    }
    public ArrayList<Complaint> getComplaints(){
        return complaints;
    }
    public ArrayList<Course> getcompletecourse(){
        return completedCourse;
    }
    public int gettotalcredit(){
        return totalcredit;
    }
    public int getcurrsem(){
        return currsem;
    }
    public void setCurrsem(int currsem){
        this.currsem = currsem;
    }
    public void trackschedule(){
        double totalgrade= 0;
        double i = 0.0;
        for(Course c : completedCourse){
            totalgrade+= c.getGrade();
            i++;
        }
        System.out.println("CGPA: "+ totalgrade/i);
    }
    public void addCompletedCourse(Course course) {
        completedCourse.add(course);
    }
    public void Feedback(){
        for(Course c : completedCourse){
            Professor professor = c.getProfessor();
            System.out.println("Give feedback for course " + c.getTitle());
            System.out.println("Enter the Rating scale from 1 to 5");
            int rate = sc.nextInt();
            if (rate <= 5 && rate>=1) {
                System.out.println("Enter the Textual Comment optional");
                String com = sc.next();
                rating.add(rate);
                comment.add(com);
                professor.addFeedback(rate,com);
            }
//            professor.addFeedback(rate,comment);
        }
    }

}
