package com.project.consultant.controller;

import com.project.consultant.model.Student;
import com.project.consultant.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("student/register")
    public ModelAndView viewRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/register");
        return modelAndView;
    }

    @PostMapping("student/register")
    public ModelAndView register(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("contact") String contact){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("student: " + username + "--" + password + "--" + email + "--" + contact);

        Student student = studentService.findByUsername(username);
        if(student.getId() == 0){
            // can register
            modelAndView.setViewName("student/home");

            student.setUsername(username);
            student.setPassword(password);
            student.setEmail(email);
            student.setContact(contact);

            studentService.saveStudent(student);

            HttpSession session = request.getSession();
            session.setAttribute("student", student);
        }
        else{
            // cannot register
            System.out.println("cannot register: username not available");
            modelAndView.setViewName("student/register");
            modelAndView.addObject("error", "Username not available");
        }

        return modelAndView;
    }

    @GetMapping("student/details")
    public ModelAndView details(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/details");

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        student = studentService.getStudentInfo(student.getId());
        modelAndView.addObject("student", student);

        return modelAndView;
    }

    @GetMapping("student/update")
    public ModelAndView update(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/update");
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        student = studentService.getStudentInfo(student.getId());
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("student/update")
    public ModelAndView update(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("contact") String contact){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("student: " + username + "--" + password + "--" + email + "--" + contact);

        modelAndView.setViewName("student/home");

        Student student = new Student();

        student.setId(id);
        student.setUsername(username);
        student.setPassword(password);
        student.setEmail(email);
        student.setContact(contact);

        studentService.saveStudent(student);

        return modelAndView;
    }

    @GetMapping("student/delete")
    public ModelAndView delete(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        studentService.deleteStudent(student.getId());
        session.invalidate();
        modelAndView.setViewName("login/welcome");

        return modelAndView;
    }

    @GetMapping("student/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/home");
        return modelAndView;
    }

}
