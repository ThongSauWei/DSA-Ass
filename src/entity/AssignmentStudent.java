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
public class AssignmentStudent implements Serializable, Comparable<AssignmentStudent>{
    private String id;
    private AssignmentTeam assignmentTeamId;
    private Student studentId;
    
    private static int idNo = 1009;
    
    public AssignmentStudent() {
        
    }
    
    public AssignmentStudent(String id) {
        this.id = id;
    }
    
    public AssignmentStudent(String id, AssignmentTeam assignmentTeamId, Student studentId) {
        this.id = idNo + "";
        this.assignmentTeamId = assignmentTeamId;
        this.studentId = studentId;
        
        idNo++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AssignmentTeam getAssignmentTeamId() {
        return assignmentTeamId;
    }

    public void setAssignmentTeamId(AssignmentTeam assignmentTeamId) {
        this.assignmentTeamId = assignmentTeamId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssignmentStudent)) {
            return false;
        }
        AssignmentStudent other = (AssignmentStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(AssignmentStudent assignmentStudent) {
        if (assignmentTeamId.compareTo(assignmentStudent.getAssignmentTeamId()) > 0) {
            return 1;
        } else if (assignmentTeamId.compareTo(assignmentStudent.getAssignmentTeamId()) == 0) {
            return studentId.getStudentName().compareTo(assignmentStudent.getStudentId().getStudentName());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return id + "|" + assignmentTeamId.getAssignmentTeamId() + "|" + studentId.getStudentId();
    }
    
    @Override
    public String toString() {
        return studentId.getTutorialGroupId().getProgrammeCode().getProgrammeCode() + "\t" + assignmentTeamId.getCourseCode().getCourseCode() + "\t" + studentId.getTutorialGroupId().getTutorialGroupId() + "\t" + studentId.getStudentId();
    }
}
