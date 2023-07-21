/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Course implements Serializable, Comparable<Course> {
    private String courseCode;
    private String courseName;
    private String courseDetail;
    private Character courseLevel;
    private String faculty;
    private int duration;

    public Course() {
    }

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public Course(String courseCode, String courseName, Character courseLevel, String faculty, int duration) {
        this(courseCode, courseName, null, courseLevel, faculty, duration);
    }
    
    public Course(String courseCode, String courseName, String courseDetail, Character courseLevel, String faculty, int duration) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.courseLevel = courseLevel;
        this.faculty = faculty;
        this.duration = duration;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public Character getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(Character courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseCode != null ? courseCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseCode == null && other.courseCode != null) || (this.courseCode != null && !this.courseCode.equals(other.courseCode))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Course course) {
        if (courseName.compareTo(course.getCourseName()) > 0) {
            return 1;
        } else if (courseName.compareTo(course.getCourseName()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return courseCode + "|" + courseName + "|" + courseDetail + "|" + courseLevel + "|" + faculty + "|" + duration;
    }

    @Override
    public String toString() {
        return courseName + "\t" + courseCode + "\t" + faculty + "\n";
    }
    
}
