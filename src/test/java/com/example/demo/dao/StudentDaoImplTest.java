package com.example.demo.dao;

import com.example.demo.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional//測試結束後，對資料庫所有異動的資料都會 rollback
@SpringBootTest
public class StudentDaoImplTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    @DisplayName("測試查詢指定 ID")
    public void getById(){
        Student student = studentDao.getById(1);
        assertNotNull(student);
        assertEquals("Amy", student.getName());
        assertEquals(90.3, student.getScore());
        assertTrue(student.isGraduate());
        assertNotNull(student.getCreateDate());
    }

    @Test
    @DisplayName("測試刪除指定 ID")
    public void deleteById(){
        studentDao.deleteById(3);

        Student student = studentDao.getById(3);
        assertNull(student);
    }

    @Test
    @DisplayName("測試新增資料")
    public void insert() {
        Student student = new Student();
        student.setName("Justin");
        student.setScore(100.0);
        student.setGraduate(true);

        Integer studentId = studentDao.insert(student);

        Student result = studentDao.getById(studentId);
        assertNotNull(result);
        assertEquals("Justin", result.getName());
        assertEquals(100.0, result.getScore());

        assertTrue(result.isGraduate());
        assertNotNull(result.getCreateDate());
    }

    @Test
    @DisplayName("測試修改指定 ID 的 name 欄位")
    public void update(){
        Student student = studentDao.getById(3);
        student.setName("John");

        studentDao.update(student);

        Student result = studentDao.getById(3);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }
}