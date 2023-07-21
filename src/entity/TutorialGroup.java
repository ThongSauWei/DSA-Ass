/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import adt.ListInterface;

/**
 *
 * @author User
 */
public class TutorialGroup implements Serializable, Comparable<TutorialGroup> {
    private String tutorialGroupId;
    private int groupNo;
    private int numOfStudent;
    private Course courseCode;
    private ListInterface<Student> studentList;
    private ListInterface<AssignmentTeam> assignmentTeamList;

    public TutorialGroup() {
    }

    public TutorialGroup(String tutorialGroupId) {
        this.tutorialGroupId = tutorialGroupId;
    }

    public TutorialGroup(String tutorialGroupId, int groupNo, int numOfStudent) {
        this(tutorialGroupId, groupNo, numOfStudent, null);
    }
    
    public TutorialGroup(String tutorialGroupId, int groupNo, int numOfStudent, Course courseCode) {
        this.tutorialGroupId = tutorialGroupId;
        this.groupNo = groupNo;
        this.numOfStudent = numOfStudent;
        this.courseCode = courseCode;
    }

    public String getTutorialGroupId() {
        return tutorialGroupId;
    }

    public void setTutorialGroupId(String tutorialGroupId) {
        this.tutorialGroupId = tutorialGroupId;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public Course getCoursecode() {
        return courseCode;
    }

    public void setCoursecode(Course courseCode) {
        this.courseCode = courseCode;
    }

    public ListInterface<AssignmentTeam> getAssignmentTeamList() {
        return assignmentTeamList;
    }

    public void setAssignmentTeamList(ListInterface<AssignmentTeam> assignmentTeamList) {
        this.assignmentTeamList = assignmentTeamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutorialGroupId != null ? tutorialGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutorialGroup)) {
            return false;
        }
        TutorialGroup other = (TutorialGroup) object;
        if ((this.tutorialGroupId == null && other.tutorialGroupId != null) || (this.tutorialGroupId != null && !this.tutorialGroupId.equals(other.tutorialGroupId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(TutorialGroup tutorialGroup) {
        if (tutorialGroupId.compareTo(tutorialGroup.getTutorialGroupId()) > 0) {
            return 1;
        } else if (tutorialGroupId.compareTo(tutorialGroup.getTutorialGroupId()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return tutorialGroupId + "\t" + numOfStudent + "\n";
    }
    
}
