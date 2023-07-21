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
    private Programme programmeCode;
    private TutorialGroup tutorialGroupId;

    public AssignmentTeam() {
    }

    public AssignmentTeam(String assignmentTeamId) {
        this(assignmentTeamId, null, null);
    }
    
    public AssignmentTeam(String assignmentTeamId, Programme programmeCode, TutorialGroup tutorialGroupId) {
        this.assignmentTeamId = assignmentTeamId;
        this.programmeCode = programmeCode;
        this.tutorialGroupId = tutorialGroupId;
    }

    public String getAssignmentTeamId() {
        return assignmentTeamId;
    }

    public void setAssignmentTeamId(String assignmentTeamId) {
        this.assignmentTeamId = assignmentTeamId;
    }

    public Programme getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(Programme programmeCode) {
        this.programmeCode = programmeCode;
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
        if (programmeCode.compareTo(assignmentTeam.getProgrammeCode()) > 0) {
            return 1;
        } else if (programmeCode.compareTo(assignmentTeam.getProgrammeCode()) == 0) {
            return tutorialGroupId.compareTo(assignmentTeam.getTutorialGroupId());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return assignmentTeamId + "|" + programmeCode.getProgrammeCode() + "|" + tutorialGroupId.getTutorialGroupId();
    }

    @Override
    public String toString() {
        return programmeCode.getProgrammeCode() + "" + tutorialGroupId.getTutorialGroupId() + "\n";
    }
    
}
