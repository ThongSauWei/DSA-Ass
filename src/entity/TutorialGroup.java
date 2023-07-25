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
public class TutorialGroup implements Serializable, Comparable<TutorialGroup> {
    private String tutorialGroupId;
    private int groupNo;
    private int numOfStudent;
    private Programme programmeCode;

    public TutorialGroup() {
    }

    public TutorialGroup(String tutorialGroupId) {
        this.tutorialGroupId = tutorialGroupId;
    }

    public TutorialGroup(String tutorialGroupId, int groupNo, int numOfStudent) {
        this(tutorialGroupId, groupNo, numOfStudent, null);
    }
    
    public TutorialGroup(String tutorialGroupId, int groupNo, int numOfStudent, Programme programmeCode) {
        this.tutorialGroupId = tutorialGroupId;
        this.groupNo = groupNo;
        this.numOfStudent = numOfStudent;
        this.programmeCode = programmeCode;
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

    public Programme getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(Programme programmeCode) {
        this.programmeCode = programmeCode;
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
    
    public String saveToFile() {
        return tutorialGroupId + "|" + groupNo + "|" + numOfStudent + "|" + programmeCode.getProgrammeCode();
    }

    @Override
    public String toString() {
        return tutorialGroupId + "\t" + numOfStudent + "\n";
    }
    
}
