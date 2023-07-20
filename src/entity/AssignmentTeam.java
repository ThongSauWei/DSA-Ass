/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import adt.ListInterface;
import javax.persistence.Basic;
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
@Table(name = "ASSIGNMENTTEAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssignmentTeam.findAll", query = "SELECT a FROM AssignmentTeam a"),
    @NamedQuery(name = "AssignmentTeam.findByAssignmentteamid", query = "SELECT a FROM AssignmentTeam a WHERE a.assignmentteamid = :assignmentteamid")})
public class AssignmentTeam implements Serializable, Comparable<AssignmentTeam> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ASSIGNMENTTEAMID")
    private String assignmentteamid;
    @OneToMany(mappedBy = "assignmentteamid")
    private ListInterface<Student> studentList;
    @JoinColumn(name = "PROGRAMMECODE", referencedColumnName = "PROGRAMMECODE")
    @ManyToOne(optional = false)
    private Programme programmecode;
    @JoinColumn(name = "TUTORIALGROUPID", referencedColumnName = "TUTORIALGROUPID")
    @ManyToOne(optional = false)
    private TutorialGroup tutorialgroupid;

    public AssignmentTeam() {
    }

    public AssignmentTeam(String assignmentteamid) {
        this.assignmentteamid = assignmentteamid;
    }

    public String getAssignmentteamid() {
        return assignmentteamid;
    }

    public void setAssignmentteamid(String assignmentteamid) {
        this.assignmentteamid = assignmentteamid;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public Programme getProgrammecode() {
        return programmecode;
    }

    public void setProgrammecode(Programme programmecode) {
        this.programmecode = programmecode;
    }

    public TutorialGroup getTutorialgroupid() {
        return tutorialgroupid;
    }

    public void setTutorialgroupid(TutorialGroup tutorialgroupid) {
        this.tutorialgroupid = tutorialgroupid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignmentteamid != null ? assignmentteamid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssignmentTeam)) {
            return false;
        }
        AssignmentTeam other = (AssignmentTeam) object;
        if ((this.assignmentteamid == null && other.assignmentteamid != null) || (this.assignmentteamid != null && !this.assignmentteamid.equals(other.assignmentteamid))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(AssignmentTeam assignmentTeam) {
        if (programmecode.compareTo(assignmentTeam.getProgrammecode()) > 0) {
            return 1;
        } else if (programmecode.compareTo(assignmentTeam.getProgrammecode()) == 0) {
            return tutorialgroupid.compareTo(assignmentTeam.getTutorialgroupid());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return programmecode.getProgrammecode() + "\t" + tutorialgroupid.getTutorialgroupid() + "\t" + studentList + "\n";
    }
    
}
