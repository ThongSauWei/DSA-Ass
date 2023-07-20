/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PROGRAMME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programme.findAll", query = "SELECT p FROM Programme p"),
    @NamedQuery(name = "Programme.findByProgrammecode", query = "SELECT p FROM Programme p WHERE p.programmecode = :programmecode"),
    @NamedQuery(name = "Programme.findByProgrammename", query = "SELECT p FROM Programme p WHERE p.programmename = :programmename"),
    @NamedQuery(name = "Programme.findByProgrammedetail", query = "SELECT p FROM Programme p WHERE p.programmedetail = :programmedetail"),
    @NamedQuery(name = "Programme.findByStartdate", query = "SELECT p FROM Programme p WHERE p.startdate = :startdate"),
    @NamedQuery(name = "Programme.findByEnddate", query = "SELECT p FROM Programme p WHERE p.enddate = :enddate"),
    @NamedQuery(name = "Programme.findByAssignmentnum", query = "SELECT p FROM Programme p WHERE p.assignmentnum = :assignmentnum")})
public class Programme implements Serializable, Comparable<Programme> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PROGRAMMECODE")
    private String programmecode;
    @Basic(optional = false)
    @Column(name = "PROGRAMMENAME")
    private String programmename;
    @Column(name = "PROGRAMMEDETAIL")
    private String programmedetail;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Basic(optional = false)
    @Column(name = "ASSIGNMENTNUM")
    private int assignmentnum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programmecode")
    private ListInterface<CourseProgramme> courseProgrammeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programmecode")
    private ListInterface<AssignmentTeam> assignmentTeamList;

    public Programme() {
    }

    public Programme(String programmecode) {
        this.programmecode = programmecode;
    }

    public Programme(String programmecode, String programmename, Date startdate, Date enddate, int assignmentnum) {
        this.programmecode = programmecode;
        this.programmename = programmename;
        this.startdate = startdate;
        this.enddate = enddate;
        this.assignmentnum = assignmentnum;
    }

    public String getProgrammecode() {
        return programmecode;
    }

    public void setProgrammecode(String programmecode) {
        this.programmecode = programmecode;
    }

    public String getProgrammename() {
        return programmename;
    }

    public void setProgrammename(String programmename) {
        this.programmename = programmename;
    }

    public String getProgrammedetail() {
        return programmedetail;
    }

    public void setProgrammedetail(String programmedetail) {
        this.programmedetail = programmedetail;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getAssignmentnum() {
        return assignmentnum;
    }

    public void setAssignmentnum(int assignmentnum) {
        this.assignmentnum = assignmentnum;
    }

    @XmlTransient
    public ListInterface<CourseProgramme> getCourseProgrammeList() {
        return courseProgrammeList;
    }

    public void setCourseProgrammeList(ListInterface<CourseProgramme> courseProgrammeList) {
        this.courseProgrammeList = courseProgrammeList;
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
        hash += (programmecode != null ? programmecode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programme)) {
            return false;
        }
        Programme other = (Programme) object;
        if ((this.programmecode == null && other.programmecode != null) || (this.programmecode != null && !this.programmecode.equals(other.programmecode))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Programme programme) {
        if (programmecode.compareTo(programme.getProgrammecode()) > 0) {
            return 1;
        } else if (programmecode.compareTo(programme.getProgrammecode()) == 0) {
            return programmename.compareTo(programme.getProgrammename());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return programmecode + "\t" + programmename + "\t" + startdate + "\t" + enddate + "\n";
    }
    
}
