/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
public class Programme implements Serializable, Comparable<Programme> {
    private String programmeCode;
    private String programmeName;
    private String programmeDetail;
    private Date startDate;
    private Date endDate;
    private int assignmentNum;

    public Programme() {
    }

    public Programme(String programmeCode) {
        this.programmeCode = programmeCode;
    }
    
    public Programme(String programmeCode, String programmeName, Date startDate, Date endDate, int assignmentNum) {
        this(programmeCode, programmeName, null, startDate, endDate, assignmentNum);
    }

    public Programme(String programmeCode, String programmeName, String programmeDetail, Date startDate, Date endDate, int assignmentNum) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.programmeDetail = programmeDetail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignmentNum = assignmentNum;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getProgrammeDetail() {
        return programmeDetail;
    }

    public void setProgrammeDetail(String programmeDetail) {
        this.programmeDetail = programmeDetail;
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
        hash += (programmeCode != null ? programmeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programme)) {
            return false;
        }
        Programme other = (Programme) object;
        if ((this.programmeCode == null && other.programmeCode != null) || (this.programmeCode != null && !this.programmeCode.equals(other.programmeCode))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Programme programme) {
        if (programmeCode.compareTo(programme.getProgrammeCode()) > 0) {
            return 1;
        } else if (programmeCode.compareTo(programme.getProgrammeCode()) == 0) {
            return programmeName.compareTo(programme.getProgrammeName());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return programmeCode + "|" + programmeName + "|" + programmeDetail + "|" + startDate + "|" + endDate + "|" + assignmentNum;
    }

    @Override
    public String toString() {
        return programmeCode + "\t" + programmeName + "\t" + startDate + "\t" + endDate + "\n";
    }
    
}
