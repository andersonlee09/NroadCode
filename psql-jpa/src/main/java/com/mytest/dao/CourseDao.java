package com.mytest.dao;

import com.mytest.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/21 17:20
 * @purpose null
 * @ModifiedRecords null
 */
@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
}
