package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "date_of_start")
    private String dateOfStart;
    @Column(name = "date_of_finish")
    private String dateOfFinish;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "groups_courses", joinColumns = @JoinColumn(name = "groups_id")
            , inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Course> course;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE,CascadeType.REFRESH},mappedBy = "groups")
    private List<Student> students;
    @Transient
    private Long courseId;

}
