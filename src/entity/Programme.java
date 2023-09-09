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
public class Programme implements Serializable, Comparable<Programme> {
    private String programmeCode;
    private String programmeName;
    private String programmeDetail;
    private Character programmeLevel;
    private String faculty;
    private int duration;

    public Programme() {
    }

    public Programme(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public Programme(String programmeCode, String programmeName, Character programmeLevel, String faculty, int duration) {
        this(programmeCode, programmeName, null, programmeLevel, faculty, duration);
    }
    
    public Programme(String programmeCode, String programmeName, String programmeDetail, Character programmeLevel, String faculty, int duration) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.programmeDetail = programmeDetail;
        this.programmeLevel = programmeLevel;
        this.faculty = faculty;
        this.duration = duration;
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

    public Character getProgrammeLevel() {
        return programmeLevel;
    }

    public void setProgrammeLevel(Character programmeLevel) {
        this.programmeLevel = programmeLevel;
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
        if (programmeName.compareTo(programme.getProgrammeName()) > 0) {
            return 1;
        } else if (programmeName.compareTo(programme.getProgrammeName()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return programmeCode;
    }
    
}
