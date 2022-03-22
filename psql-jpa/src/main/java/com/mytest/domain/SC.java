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
@Table(name = "sc")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SC {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "cou_id")
    @JsonProperty
    private Integer courseId;

    @Column(name = "stu_id")
    @JsonProperty
    private Integer studentId;
}
