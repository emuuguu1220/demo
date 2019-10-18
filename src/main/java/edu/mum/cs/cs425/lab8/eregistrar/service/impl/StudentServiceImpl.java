package edu.mum.cs.cs425.lab8.eregistrar.service.impl;

import edu.mum.cs.cs425.lab8.eregistrar.model.Student;
import edu.mum.cs.cs425.lab8.eregistrar.repository.StudentRepository;
import edu.mum.cs.cs425.lab8.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 3, Sort.by("lastName")));
    }
    @Override
    public Page<Student> findStudentByStudentCode(PageRequest pageRequest, String studentCode) {

        Page<Student> page = new PageImpl<>(repository.findByStudentCodeStartingWith(studentCode));
        return page;
    }


    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return
                repository.findById(studentId)
                        .orElse((Student) null);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        repository.deleteById(studentId);
    }
}
