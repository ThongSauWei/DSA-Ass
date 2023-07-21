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
public class CourseProgramme implements Serializable, Comparable<CourseProgramme> {
    private String id;
    private Course courseCode;
    private Programme programmeCode;

    public CourseProgramme() {
    }

    public CourseProgramme(String id) {
        this.id = id;
    }
    
    public CourseProgramme(String id, Course courseCode, Programme programmeCode) {
        this.id = id;
        this.courseCode = courseCode;
        this.programmeCode = programmeCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Course courseCode) {
        this.courseCode = courseCode;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseProgramme)) {
            return false;
        }
        CourseProgramme other = (CourseProgramme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(CourseProgramme courseProgramme) {
        if (courseCode.compareTo(courseProgramme.getCourseCode()) > 0) {
            return 1;
        } else if (courseCode.compareTo(courseProgramme.getCourseCode()) == 0) {
            return programmeCode.compareTo(courseProgramme.getProgrammeCode());
        } else {
            return -1;
        }
    }
    
    public String saveToFile() {
        return id + "|" + courseCode.getCourseCode() + "|" + programmeCode.getProgrammeCode();
    }

    @Override
    public String toString() {
        return courseCode.getCourseCode() + "\t" + programmeCode.getProgrammeCode() + "\t" + programmeCode.getProgrammeName() + "\n";
    }
    
}
