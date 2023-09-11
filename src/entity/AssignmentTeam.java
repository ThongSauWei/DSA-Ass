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
public class AssignmentTeam implements Serializable, Comparable<AssignmentTeam> {
    private String assignmentTeamId;
    private String assignmentTitle;
    private int NumOfMembers;
    private int submissionWeek;
    private Course courseCode;
    private TutorialGroup tutorialGroupId;

    public AssignmentTeam() {
    }

    public AssignmentTeam(String assignmentTeamId) {
        this(assignmentTeamId, "", 0, 0, null, null);
    }
    
    public AssignmentTeam(String assignmentTeamId, String assignmentTitle, int NumOfMembers, int submissionWeek, Course courseCode, TutorialGroup tutorialGroupId) {
        this.assignmentTeamId = assignmentTeamId;
        this.assignmentTitle = assignmentTitle;
        this.NumOfMembers = NumOfMembers;
        this.submissionWeek = submissionWeek;
        this.courseCode = courseCode;
        this.tutorialGroupId = tutorialGroupId;
    }

    public String getAssignmentTeamId() {
        return assignmentTeamId;
    }

    public void setAssignmentTeamId(String assignmentTeamId) {
        this.assignmentTeamId = assignmentTeamId;
    }
    
    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public int getNumOfMembers() {
        return NumOfMembers;
    }

    public void setNumOfMembers(int NumOfMembers) {
        this.NumOfMembers = NumOfMembers;
    }

    public int getSubmissionWeek() {
        return submissionWeek;
    }

    public void setSubmissionWeek(int submissionWeek) {
        this.submissionWeek = submissionWeek;
    }
    
    public Course getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Course courseCode) {
        this.courseCode = courseCode;
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
        hash += (assignmentTeamId != null ? assignmentTeamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssignmentTeam)) {
            return false;
        }
        AssignmentTeam other = (AssignmentTeam) object;
        if ((this.assignmentTeamId == null && other.assignmentTeamId != null) || (this.assignmentTeamId != null && !this.assignmentTeamId.equals(other.assignmentTeamId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(AssignmentTeam assignmentTeam) {
        if (courseCode.compareTo(assignmentTeam.getCourseCode()) > 0) {
            return 1;
        } else if (courseCode.compareTo(assignmentTeam.getCourseCode()) == 0) {
            return tutorialGroupId.compareTo(assignmentTeam.getTutorialGroupId());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return assignmentTeamId + "|" + assignmentTitle + "|" + NumOfMembers + "|" + submissionWeek + "|" + courseCode.getCourseCode() + "|" + tutorialGroupId.getTutorialGroupId();
    }

    @Override
    public String toString() {
        return courseCode.getCourseCode() + "" + tutorialGroupId.getTutorialGroupId() + "\n";
    }//??
    
}
