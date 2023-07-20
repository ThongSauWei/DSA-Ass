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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TUTORIALGROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TutorialGroup.findAll", query = "SELECT t FROM TutorialGroup t"),
    @NamedQuery(name = "TutorialGroup.findByTutorialgroupid", query = "SELECT t FROM TutorialGroup t WHERE t.tutorialgroupid = :tutorialgroupid"),
    @NamedQuery(name = "TutorialGroup.findByGroupno", query = "SELECT t FROM TutorialGroup t WHERE t.groupno = :groupno"),
    @NamedQuery(name = "TutorialGroup.findByNumofstudent", query = "SELECT t FROM TutorialGroup t WHERE t.numofstudent = :numofstudent")})
public class TutorialGroup implements Serializable, Comparable<TutorialGroup> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TUTORIALGROUPID")
    private String tutorialgroupid;
    @Basic(optional = false)
    @Column(name = "GROUPNO")
    private int groupno;
    @Basic(optional = false)
    @Column(name = "NUMOFSTUDENT")
    private int numofstudent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutorialgroupid")
    private ListInterface<Student> studentList;
    @JoinColumn(name = "COURSECODE", referencedColumnName = "COURSECODE")
    @ManyToOne(optional = false)
    private Course coursecode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutorialgroupid")
    private ListInterface<AssignmentTeam> assignmentTeamList;

    public TutorialGroup() {
    }

    public TutorialGroup(String tutorialgroupid) {
        this.tutorialgroupid = tutorialgroupid;
    }

    public TutorialGroup(String tutorialgroupid, int groupno, int numofstudent) {
        this.tutorialgroupid = tutorialgroupid;
        this.groupno = groupno;
        this.numofstudent = numofstudent;
    }

    public String getTutorialgroupid() {
        return tutorialgroupid;
    }

    public void setTutorialgroupid(String tutorialgroupid) {
        this.tutorialgroupid = tutorialgroupid;
    }

    public int getGroupno() {
        return groupno;
    }

    public void setGroupno(int groupno) {
        this.groupno = groupno;
    }

    public int getNumofstudent() {
        return numofstudent;
    }

    public void setNumofstudent(int numofstudent) {
        this.numofstudent = numofstudent;
    }

    @XmlTransient
    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public Course getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(Course coursecode) {
        this.coursecode = coursecode;
    }

    @XmlTransient
    public ListInterface<AssignmentTeam> getAssignmentTeamList() {
        return assignmentTeamList;
    }

    public void setAssignmentTeamList(ListInterface<AssignmentTeam> assignmentTeamList) {
        this.assignmentTeamList = assignmentTeamList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutorialgroupid != null ? tutorialgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutorialGroup)) {
            return false;
        }
        TutorialGroup other = (TutorialGroup) object;
        if ((this.tutorialgroupid == null && other.tutorialgroupid != null) || (this.tutorialgroupid != null && !this.tutorialgroupid.equals(other.tutorialgroupid))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(TutorialGroup tutorialGroup) {
        if (tutorialgroupid.compareTo(tutorialGroup.getTutorialgroupid()) > 0) {
            return 1;
        } else if (tutorialgroupid.compareTo(tutorialGroup.getTutorialgroupid()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return tutorialgroupid + "\t" + numofstudent + "\n";
    }
    
}
