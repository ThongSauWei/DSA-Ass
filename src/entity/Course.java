/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import adt.ListInterface;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCoursecode", query = "SELECT c FROM Course c WHERE c.coursecode = :coursecode"),
    @NamedQuery(name = "Course.findByCoursename", query = "SELECT c FROM Course c WHERE c.coursename = :coursename"),
    @NamedQuery(name = "Course.findByCoursedetail", query = "SELECT c FROM Course c WHERE c.coursedetail = :coursedetail"),
    @NamedQuery(name = "Course.findByCourselevel", query = "SELECT c FROM Course c WHERE c.courselevel = :courselevel"),
    @NamedQuery(name = "Course.findByFaculty", query = "SELECT c FROM Course c WHERE c.faculty = :faculty"),
    @NamedQuery(name = "Course.findByDuration", query = "SELECT c FROM Course c WHERE c.duration = :duration")})
public class Course implements Serializable, Comparable<Course> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COURSECODE")
    private String coursecode;
    @Basic(optional = false)
    @Column(name = "COURSENAME")
    private String coursename;
    @Column(name = "COURSEDETAIL")
    private String coursedetail;
    @Basic(optional = false)
    @Column(name = "COURSELEVEL")
    private Character courselevel;
    @Basic(optional = false)
    @Column(name = "FACULTY")
    private String faculty;
    @Basic(optional = false)
    @Column(name = "DURATION")
    private int duration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coursecode")
    private ListInterface<CourseProgramme> courseProgrammeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coursecode")
    private ListInterface<TutorialGroup> tutorialGroupList;

    public Course() {
    }

    public Course(String coursecode) {
        this.coursecode = coursecode;
    }

    public Course(String coursecode, String coursename, Character courselevel, String faculty, int duration) {
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.courselevel = courselevel;
        this.faculty = faculty;
        this.duration = duration;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedetail() {
        return coursedetail;
    }

    public void setCoursedetail(String coursedetail) {
        this.coursedetail = coursedetail;
    }

    public Character getCourselevel() {
        return courselevel;
    }

    public void setCourselevel(Character courselevel) {
        this.courselevel = courselevel;
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

    @XmlTransient
    public ListInterface<CourseProgramme> getCourseProgrammeList() {
        return courseProgrammeList;
    }

    public void setCourseProgrammeList(ListInterface<CourseProgramme> courseProgrammeList) {
        this.courseProgrammeList = courseProgrammeList;
    }

    @XmlTransient
    public ListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(ListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coursecode != null ? coursecode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.coursecode == null && other.coursecode != null) || (this.coursecode != null && !this.coursecode.equals(other.coursecode))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Course course) {
        if (coursename.compareTo(course.getCoursename()) > 0) {
            return 1;
        } else if (coursename.compareTo(course.getCoursename()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return coursename + "\t" + coursecode + "\t" + faculty + "\n";
    }
    
}
