/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class Course implements Serializable, Comparable<Course> {
    private String courseCode;
    private String courseName;
    private String courseDetail;
    private Date startDate;
    private Date endDate;
    private int assignmentNum;

    public Course() {
    }

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public Course(String courseCode, String courseName, Date startDate, Date endDate, int assignmentNum) {
        this(courseCode, courseName, null, startDate, endDate, assignmentNum);
    }

    public Course(String courseCode, String courseName, String courseDetail, Date startDate, Date endDate, int assignmentNum) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignmentNum = assignmentNum;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAssignmentNum() {
        return assignmentNum;
    }

    public void setAssignmentNum(int assignmentNum) {
        this.assignmentNum = assignmentNum;
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
        if (courseCode.compareTo(course.getCourseCode()) > 0) {
            return 1;
        } else if (courseCode.compareTo(course.getCourseCode()) == 0) {
            return courseName.compareTo(course.getCourseName());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return courseCode + "|" + courseName + "|" + courseDetail + "|" + new SimpleDateFormat("yyyy-MM-dd").format(startDate) + "|" + 
                new SimpleDateFormat("yyyy-MM-dd").format(endDate) + "|" + assignmentNum;
    }

    @Override
    public String toString() {
        return courseCode + "\t" + courseName + "\t" + startDate + "\t" + endDate + "\n";
    }
    
}
