/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Benjamin
 */
public class Student implements Serializable, Comparable<Student> {
    private String studentId;
    private String studentName;
    private String studentIc;
    private String studentEmail;
    private String phoneNo;
    private int studyYear;
    private int semester;
    private TutorialGroup tutorialGroupId;

    public Student() {
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public Student(String studentId, String studentName, String studentIc, String studentEmail, String phoneNo, int studyYear, int semester) {
        this(studentId, studentName, studentIc, studentEmail, phoneNo, studyYear, semester, null);
    }
    
    public Student(String studentId, String studentName, String studentIc, String studentEmail, String phoneNo, int studyYear, int semester, TutorialGroup tutorialGroupId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentIc = studentIc;
        this.studentEmail = studentEmail;
        this.phoneNo = phoneNo;
        this.studyYear = studyYear;
        this.semester = semester;
        this.tutorialGroupId = tutorialGroupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentIc() {
        return studentIc;
    }

    public void setStudentIc(String studentIc) {
        this.studentIc = studentIc;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public TutorialGroup getTutorialGroupId() {
        return tutorialGroupId;
    }

    public void setTutorialGroupId(TutorialGroup tutorialGroupId) {
        this.tutorialGroupId = tutorialGroupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Student student) {
        if (studentName.compareTo(student.getStudentName()) > 0) {
            return 1;
        } else if (studentName.compareTo(student.getStudentName()) == 0) {
            return studentId.compareTo(student.getStudentId());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return studentId + "|" + studentName + "|" + studentIc + "|" + studentEmail + "|" + phoneNo + "|" + 
                studyYear + "|" + semester + "|" + tutorialGroupId.getTutorialGroupId();
    }

    @Override
    public String toString() {       
        return String.format("%-12s %-30s %-16s %-40s %-13s %-14s", studentId, studentName, studentIc, studentEmail, phoneNo, tutorialGroupId.getTutorialGroupId());
    }
    
}
