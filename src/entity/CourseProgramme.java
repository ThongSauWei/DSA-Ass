/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "COURSEPROGRAMME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseProgramme.findAll", query = "SELECT c FROM CourseProgramme c"),
    @NamedQuery(name = "CourseProgramme.findById", query = "SELECT c FROM CourseProgramme c WHERE c.id = :id")})
public class CourseProgramme implements Serializable, Comparable<CourseProgramme> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @JoinColumn(name = "COURSECODE", referencedColumnName = "COURSECODE")
    @ManyToOne(optional = false)
    private Course coursecode;
    @JoinColumn(name = "PROGRAMMECODE", referencedColumnName = "PROGRAMMECODE")
    @ManyToOne(optional = false)
    private Programme programmecode;

    public CourseProgramme() {
    }

    public CourseProgramme(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(Course coursecode) {
        this.coursecode = coursecode;
    }

    public Programme getProgrammecode() {
        return programmecode;
    }

    public void setProgrammecode(Programme programmecode) {
        this.programmecode = programmecode;
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
        if (coursecode.compareTo(courseProgramme.getCoursecode()) > 0) {
            return 1;
        } else if (coursecode.compareTo(courseProgramme.getCoursecode()) == 0) {
            return programmecode.compareTo(courseProgramme.getProgrammecode());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return coursecode.getCoursecode() + "\t" + programmecode.getProgrammecode() + "\t" + programmecode.getProgrammename() + "\n";
    }
    
}
