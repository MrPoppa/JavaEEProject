package com.rappandpoppa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Benjamin
 */
@Entity
@Table(name = "attendancelist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendancelist.findAll", query = "SELECT a FROM Attendancelist a"),
    @NamedQuery(name = "Attendancelist.findById", query = "SELECT a FROM Attendancelist a WHERE a.id = :id"),
    @NamedQuery(name = "Attendancelist.findByAttendanceDate", query = "SELECT a FROM Attendancelist a WHERE a.attendanceDate = :attendanceDate"),
    @NamedQuery(name = "Attendancelist.findAllDatesByCourse", query = "SELECT a.attendanceDate FROM Attendancelist a WHERE a.course = :course_id"),
    @NamedQuery(name = "Attendancelist.findAllListsInPeriod",
            query = "SELECT a FROM Attendancelist a "
            + "WHERE a.attendanceDate >= :startDate "
            + "AND a.attendanceDate <= :endDate "
            + "GROUP BY a.attendanceDate"),
    @NamedQuery(name = "Attendancelist.findCourseListsInPeriod",
            query = "SELECT a FROM Attendancelist a "
            + "WHERE a.attendanceDate >= :startDate "
            + "AND a.attendanceDate <= :endDate "
            + "AND a.course.id = :course_id "
            + "GROUP BY a.attendanceDate")
})
public class Attendancelist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "attendanceDate")
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;
    @JoinTable(name = "student_attendance", joinColumns = {
        @JoinColumn(name = "attendance_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "student_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Student> studentList;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;

    public Attendancelist() {
    }

    public Attendancelist(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
        if (!(object instanceof Attendancelist)) {
            return false;
        }
        Attendancelist other = (Attendancelist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rappandpoppa.entities.Attendancelist[ id=" + id + " ]";
    }

}
