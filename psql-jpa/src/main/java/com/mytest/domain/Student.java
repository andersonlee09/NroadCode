package com.mytest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/21 17:17
 * @purpose null
 * @ModifiedRecords null
 */
// @Entity 说明这个 class 是实体类，并且使用默认的 orm 规则，即 class 名即数据库表中表名，class 字段名即表中的字段名。
@Data
@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {
    // 主键id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer studentId;

    // 学号
    @JsonProperty
    @Column(name = "stu_number")
    private String numId;

    // 姓名
    @JsonProperty
    @Column(name = "stu_name")
    private String name;

    // 班级
    @JsonProperty
    @Column(name = "stu_class")
    private String studentClass;
}
