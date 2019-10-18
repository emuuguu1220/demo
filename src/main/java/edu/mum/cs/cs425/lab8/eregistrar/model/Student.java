package edu.mum.cs.cs425.lab8.eregistrar.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @NotBlank(message = "* Student code is required")
    @Column(name = "studentCode", nullable = false, unique = true)
    private String studentCode;

    @NotBlank(message = "* First name is required")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    private String middleName;

    @NotBlank(message = "* Last name is required")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    // @NotBlank(message = "* CGPA is required")
    private double cgpa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "enrollmentDate", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "isInternational", nullable = false)
    private boolean isInternational;

    public Student() {
    }

    public Student(String studentCode, String firstName, String middleName, String lastName, double cgpa, LocalDate enrollmentDate, boolean isInternational) {
        this.studentCode = studentCode;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cgpa = cgpa;
        this.enrollmentDate = enrollmentDate;
        this.isInternational = isInternational;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public boolean getIsInternational() {
        return isInternational;
    }

    public void setIsInternational(boolean isInternational) {
        this.isInternational = isInternational;
    }
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentCode='" + studentCode + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName=" + middleName +
                ", lastName='" + lastName + '\'' +
                ", cgpa=" + cgpa + '\'' +
                ", enrollmentDate=" + enrollmentDate + '\'' +
                ", isInternational=" + isInternational +
                '}';
    }
}
