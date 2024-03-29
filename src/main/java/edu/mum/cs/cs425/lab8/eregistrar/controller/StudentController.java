package edu.mum.cs.cs425.lab8.eregistrar.controller;


import edu.mum.cs.cs425.lab8.eregistrar.model.Student;
import edu.mum.cs.cs425.lab8.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {
    //    @Autowired
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = {"/eregistrar/student/list"})
    public ModelAndView listStudents(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.getAllStudentsPaged(pageno));
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("student/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eregistrar/student/{studentCode}"})
    public ModelAndView searchStudent(@RequestParam(defaultValue = "0") int pageno, @PathVariable String studentCode) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.findStudentByStudentCode(PageRequest.of(pageno, 3), studentCode));
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("student/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eregistrar/student/new"})
    public String displayNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/new";
    }

    @PostMapping(value = {"/eregistrar/student/new"})
    public String addNewStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student/new";
        }

        student = studentService.saveStudent(student);
        return "redirect:/eregistrar/student/list";
    }

    @GetMapping(value = {"/eregistrar/student/edit/{studentId}"})
    public String editStudent(@PathVariable Integer studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        System.out.println("$$$$$$$$$$$$$$$ "+student.getStudentCode());
        if (student != null) {
            model.addAttribute("student", student);
            return "student/edit";
        }
        return "student/list";
    }


    @PostMapping(value = {"/eregistrar/student/edit"})
    public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student/edit";
        }
        studentService.saveStudent(student);
        return "redirect:/eregistrar/student/list";
    }

    @GetMapping(value = {"/eregistrar/student/delete/{studentId}"})
    public String deleteStudent(@PathVariable Integer studentId, Model model) {
        studentService.deleteStudentById(studentId);
        return "redirect:/eregistrar/student/list";
    }

}
