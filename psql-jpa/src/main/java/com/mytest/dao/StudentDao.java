package com.mytest.dao;

import com.mytest.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/21 17:20
 * @purpose null
 * @ModifiedRecords null
 */

public interface StudentDao extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {


}
