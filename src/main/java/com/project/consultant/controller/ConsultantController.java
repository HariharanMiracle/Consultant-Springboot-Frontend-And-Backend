package com.project.consultant.controller;

import com.project.consultant.model.Consultant;
import com.project.consultant.service.ConsultantService;
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
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    @GetMapping("consultant/register")
    public ModelAndView viewRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consultant/register");
        return modelAndView;
    }

    @PostMapping("consultant/register")
    public ModelAndView register(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("contact") String contact){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Consultant: " + username + "--" + password + "--" + email + "--" + contact);

        Consultant consultant = consultantService.findByUsername(username);
        if(consultant.getId() == 0){
            // can register
            modelAndView.setViewName("consultant/home");

            consultant.setUsername(username);
            consultant.setPassword(password);
            consultant.setEmail(email);
            consultant.setContact(contact);

            consultantService.saveConsultant(consultant);

            HttpSession session = request.getSession();
            session.setAttribute("consultant", consultant);
        }
        else{
            // cannot register
            System.out.println("cannot register: username not available");
            modelAndView.setViewName("consultant/register");
            modelAndView.addObject("error", "Username not available");
        }

        return modelAndView;
    }

    @GetMapping("consultant/details")
    public ModelAndView details(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consultant/details");

        HttpSession session = request.getSession();
        Consultant consultant = (Consultant) session.getAttribute("consultant");
        consultant = consultantService.getConsultantInfo(consultant.getId());
        modelAndView.addObject("consultant", consultant);

        return modelAndView;
    }

    @GetMapping("consultant/update")
    public ModelAndView update(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consultant/update");
        HttpSession session = request.getSession();
        Consultant consultant = (Consultant) session.getAttribute("consultant");
        consultant = consultantService.getConsultantInfo(consultant.getId());
        modelAndView.addObject("consultant", consultant);
        return modelAndView;
    }

    @PostMapping("consultant/update")
    public ModelAndView update(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("contact") String contact){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Consultant: " + username + "--" + password + "--" + email + "--" + contact);

        modelAndView.setViewName("consultant/home");

        Consultant consultant = new Consultant();

        consultant.setId(id);
        consultant.setUsername(username);
        consultant.setPassword(password);
        consultant.setEmail(email);
        consultant.setContact(contact);

        consultantService.saveConsultant(consultant);

        return modelAndView;
    }

    @GetMapping("consultant/delete")
    public ModelAndView delete(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = request.getSession();
        Consultant consultant = (Consultant) session.getAttribute("consultant");
        consultantService.deleteConsultant(consultant.getId());
        session.invalidate();
        modelAndView.setViewName("login/welcome");

        return modelAndView;
    }

    @GetMapping("consultant/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consultant/home");
        return modelAndView;
    }

}
