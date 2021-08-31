package ua.com.alevel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @Access(AccessType.PROPERTY)
    public Course course;

    @OneToMany(mappedBy = "group")
    private List<Lesson> lessons;

    public Group() {
    }

    public Group(String name, Course course) {
        this.name = name;
        this.students = new ArrayList<>();
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student){
        students.add(student);
        student.setGroup(this);
    }
}
