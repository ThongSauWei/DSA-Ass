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
@Table(name = "STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByStudentid", query = "SELECT s FROM Student s WHERE s.studentid = :studentid"),
    @NamedQuery(name = "Student.findByStudentname", query = "SELECT s FROM Student s WHERE s.studentname = :studentname"),
    @NamedQuery(name = "Student.findByStudentic", query = "SELECT s FROM Student s WHERE s.studentic = :studentic"),
    @NamedQuery(name = "Student.findByStudentemail", query = "SELECT s FROM Student s WHERE s.studentemail = :studentemail"),
    @NamedQuery(name = "Student.findByPhoneno", query = "SELECT s FROM Student s WHERE s.phoneno = :phoneno"),
    @NamedQuery(name = "Student.findByStudyyear", query = "SELECT s FROM Student s WHERE s.studyyear = :studyyear"),
    @NamedQuery(name = "Student.findBySemester", query = "SELECT s FROM Student s WHERE s.semester = :semester")})
public class Student implements Serializable, Comparable<Student> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STUDENTID")
    private String studentid;
    @Basic(optional = false)
    @Column(name = "STUDENTNAME")
    private String studentname;
    @Basic(optional = false)
    @Column(name = "STUDENTIC")
    private String studentic;
    @Basic(optional = false)
    @Column(name = "STUDENTEMAIL")
    private String studentemail;
    @Basic(optional = false)
    @Column(name = "PHONENO")
    private String phoneno;
    @Basic(optional = false)
    @Column(name = "STUDYYEAR")
    private int studyyear;
    @Basic(optional = false)
    @Column(name = "SEMESTER")
    private int semester;
    @JoinColumn(name = "ASSIGNMENTTEAMID", referencedColumnName = "ASSIGNMENTTEAMID")
    @ManyToOne
    private AssignmentTeam assignmentteamid;
    @JoinColumn(name = "TUTORIALGROUPID", referencedColumnName = "TUTORIALGROUPID")
    @ManyToOne(optional = false)
    private TutorialGroup tutorialgroupid;

    public Student() {
    }

    public Student(String studentid) {
        this.studentid = studentid;
    }

    public Student(String studentid, String studentname, String studentic, String studentemail, String phoneno, int studyyear, int semester) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.studentic = studentic;
        this.studentemail = studentemail;
        this.phoneno = phoneno;
        this.studyyear = studyyear;
        this.semester = semester;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentic() {
        return studentic;
    }

    public void setStudentic(String studentic) {
        this.studentic = studentic;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public int getStudyyear() {
        return studyyear;
    }

    public void setStudyyear(int studyyear) {
        this.studyyear = studyyear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public AssignmentTeam getAssignmentteamid() {
        return assignmentteamid;
    }

    public void setAssignmentteamid(AssignmentTeam assignmentteamid) {
        this.assignmentteamid = assignmentteamid;
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
        hash += (studentid != null ? studentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentid == null && other.studentid != null) || (this.studentid != null && !this.studentid.equals(other.studentid))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Student student) {
        if (studentname.compareTo(student.getStudentname()) > 0) {
            return 1;
        } else if (studentname.compareTo(student.getStudentname()) == 0) {
            return studentid.compareTo(student.getStudentid());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return studentname + "\t" + studentid + "\t" + studentic + "\t" + studentemail + "\t" + phoneno + "\t" + 
                tutorialgroupid.getCoursecode().getCoursecode() + studyyear + "S" + semester + "G" + tutorialgroupid.getGroupno() + "\n";
    }
    
}
