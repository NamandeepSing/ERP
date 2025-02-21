package Assigment1;

import java.util.ArrayList;

public class Course {
    String syllabus;
    Professor professor;
    String title;
    String code;
    int credits;
    int semester;
    int grade;
    ArrayList<String> prerequisites;
    String Timing;
    ArrayList<Student> enrolledStudents;
    int capacity;
    public Course(String title, String code, int credits, int semester , Professor professor, ArrayList<String> prerequisites, String Timing, ArrayList<Student> enrolledStudents,int capacity) {
        this.title = title;
        this.code = code;
        this.credits = credits;
        this.semester = semester;
        this.professor = professor;
        this.prerequisites = prerequisites;
        this.Timing = Timing;
        this.enrolledStudents = enrolledStudents;
        this.capacity = capacity;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }
    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }
    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
    public String getTiming() {
        return Timing;
    }
    public void setTiming(String Timing) {
        this.Timing = Timing;
    }
    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getGrade(){
        return grade;
    }
}
