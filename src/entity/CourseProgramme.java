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
    private Programme programmeCode;
    private Course courseCode;
    
    private static int courseNo = 1001;

    public CourseProgramme() {
        
    }
    
    public CourseProgramme(Programme programmeCode, Course courseCode) {
        this.id = programmeCode.getProgrammeCode() + courseNo;
        this.programmeCode = programmeCode;
        this.courseCode = courseCode;
        
        courseNo++;
    }

    public String getId() {
        return id;
    }
    
    public Programme getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(Programme programmeCode) {
        this.programmeCode = programmeCode;
    }

    public Course getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Course courseCode) {
        this.courseCode = courseCode;
    }

    public static int getCourseNo() {
        return courseNo;
    }

    public static void setCourseNo(int courseNo) {
        CourseProgramme.courseNo = courseNo;
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
        if (programmeCode.compareTo(courseProgramme.getProgrammeCode()) > 0) {
            return 1;
        } else if (programmeCode.compareTo(courseProgramme.getProgrammeCode()) == 0) {
            return courseCode.compareTo(courseProgramme.getCourseCode());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return programmeCode.getProgrammeCode() + "\t" + courseCode.getCourseCode() + "\t" + courseCode.getCourseName() + "\n";
    }
    
}
