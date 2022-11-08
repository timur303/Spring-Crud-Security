package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_courses", joinColumns = @JoinColumn(name = "teachers_id")
            , inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private Course course;

    @Transient
    Long courseId;

}

