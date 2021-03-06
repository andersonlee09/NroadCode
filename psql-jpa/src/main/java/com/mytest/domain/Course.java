package com.mytest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/21 17:18
 * @purpose null
 * @ModifiedRecords null
 */
@Data
@Entity
@Table(name = "course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer courseId;

    @Column(name = "cou_name")
    @JsonProperty
    private String courseName;
}
