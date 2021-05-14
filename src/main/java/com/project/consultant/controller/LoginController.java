package com.project.consultant.controller;

import com.project.consultant.model.Consultant;
import com.project.consultant.model.Student;
import com.project.consultant.service.ConsultantService;
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
public class LoginController {

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/welcome");
        return modelAndView;
    }

    @PostMapping("login/login_consultant")
    public ModelAndView login_teacher(@RequestParam("c_username") String username, @RequestParam("c_password") String password, HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(username + " --- " + password);

        Consultant consultant = consultantService.findByUsername(username);
        if(consultant.getId() == 0){
            // error
            modelAndView.setViewName("login/welcome");
            modelAndView.addObject("error", "Error: Username or password is incorrect!");
        }
        else{
            if(consultant.getPassword().equals(password)){
                // success
                modelAndView.setViewName("consultant/home");
                HttpSession session = request.getSession();
                session.setAttribute("consultant", consultant);
            }
            else{
                // error
                modelAndView.setViewName("login/welcome");
                modelAndView.addObject("error", "Error: Username or password is incorrect!");
            }
        }

        return modelAndView;
    }

    @PostMapping("login/login_student")
    public ModelAndView login_student(HttpServletRequest request, @RequestParam("s_username") String username, @RequestParam("s_password") String password) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(username + " --- " + password);

        Student student = studentService.findByUsername(username);
        if(student.getId() == 0){
            // error
            modelAndView.setViewName("login/welcome");
            modelAndView.addObject("error", "Error: Username or password is incorrect!");
        }
        else{
            if(student.getPassword().equals(password)){
                // success
                modelAndView.setViewName("student/home");
                HttpSession session = request.getSession();
                session.setAttribute("student", student);
            }
            else{
                // error
                modelAndView.setViewName("login/welcome");
                modelAndView.addObject("error", "Error: Username or password is incorrect!");
            }
        }

        return modelAndView;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/welcome");
        HttpSession session = request.getSession();
        session.invalidate();

        return modelAndView;
    }

}
