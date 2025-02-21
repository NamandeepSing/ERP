package Assigment1;

import java.util.ArrayList;

public class TeachingAssistant extends Student {
    Professor professor;
    ArrayList<Course> courses;

    public TeachingAssistant(String name,String email,String password,int currsem) {
        super(name, email, password, currsem);
        this.courses = new ArrayList<>();
    }
    public void seeGrades(Course course){
        if (course.getProfessor().equals(professor)){
            System.out.println("See Grade of " + course.getTitle() + " this course");
            for(Student s : course.getEnrolledStudents()){
                System.out.println(s.getName() + " -> Grade" + course.getGrade());
            }
        }else {
            System.out.println("Not Assigned");
        }
    }
    public void assignedGrades(Course course, Student student, int grade){
        if (this.courses.contains(course)){
            if (student.takeCourses.contains(course)) {
                System.out.println("Assigned Grade " + grade + " to " + student.getName());
                student.addCompletedCourse(course);
                student.takeCourses.remove(course);
                course.capacity++;
                student.Grade.put(course, grade);
                course.setGrade(grade);
            }
        }
    }
    public void getCourses(){
        for(Course c : courses){
            System.out.println(c.getTitle());
        }
    }
}
