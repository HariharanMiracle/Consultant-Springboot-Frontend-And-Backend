package com.project.consultant.controller;

import com.project.consultant.model.Appointment;
import com.project.consultant.model.Consultant;
import com.project.consultant.model.Consulttime;
import com.project.consultant.model.Student;
import com.project.consultant.repository.AppointmentRepository;
import com.project.consultant.repository.ConsulttimeRepository;
import com.project.consultant.service.ConsultantService;
import com.project.consultant.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ConsulttimeRepository consulttimeRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("student/appointment/stage1")
    public ModelAndView viewAddAppointmentStage1(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("student/addAppointmentViewStage1");
        List<Consultant> consultantList = consultantService.ListConsultants();
        modelAndView.addObject("consultantList", consultantList);

        return modelAndView;
    }

    @GetMapping("student/appointment/stage2/{id}")
    public ModelAndView viewAddAppointmentStage2(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/addAppointmentViewStage2");

        List<Consulttime> consulttimeList = new ArrayList<>();
        try{
            consulttimeList = consulttimeRepository.findConsulttimeByconsultantidAndAfterToday(id);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }

        modelAndView.addObject("consulttimeList", consulttimeList);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @PostMapping("appointment/makeAppointment")
    public ModelAndView makeAppointment(HttpServletRequest request, @RequestParam("consultantid") int consultantid, @RequestParam("date") Date date, @RequestParam("hh") String hh, @RequestParam("mm") String mm, @RequestParam("hours") int hours) throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        List<Consulttime> consulttimeList = consulttimeRepository.findConsulttimeByconsultantidAndAfterToday(consultantid);

        Student student = (Student) request.getSession().getAttribute("student");

        String time = hh+":"+mm+":00";
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        long ms = sdf1.parse(time).getTime();
        Time t = new Time(ms);

        boolean dateStatus = false;
        for(Consulttime consulttime : consulttimeList){
            if(consulttime.getDate().equals(date)){
                dateStatus = true;
            }
        }

        boolean timeStatus = false;
        if(dateStatus == true){
            for(Consulttime consulttime : consulttimeList){
                if(consulttime.getDate().equals(date)){
                    if(consulttime.getFromtime().before(t) || consulttime.getFromtime().equals(t)){
                        timeStatus = true;
                    }
                }
            }
        }

        if(dateStatus == true && timeStatus == true){
            // save
            Appointment appointment = new Appointment();

            appointment.setConsultantid(consultantid);
            appointment.setFromtime(t);
            appointment.setHours(hours);
            appointment.setDate(date);
            appointment.setStudentid(student.getId());

            appointmentRepository.save(appointment);

            modelAndView.setViewName("student/home");
        }
        else{
            // cannot save, no free time
            modelAndView.setViewName("student/addAppointmentViewStage2");

            List<Consulttime> consulttimeList1 = new ArrayList<>();
            try{
                consulttimeList1 = consulttimeRepository.findConsulttimeByconsultantidAndAfterToday(consultantid);
            }
            catch (Exception e){
                System.out.println("Error: " + e);
            }

            modelAndView.addObject("error", "Cannot save, No free time, please choose another time or consultant");
            modelAndView.addObject("consulttimeList", consulttimeList1);
            modelAndView.addObject("id", consultantid);
        }

        return modelAndView;
    }


    @GetMapping("student/appointment/myappointment")
    public ModelAndView myAppointment(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/myAppointment");

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList = appointmentRepository.findAppointmentByStudentId(student.getId());

        List<Consultant> consultantList = consultantService.ListConsultants();

        modelAndView.addObject("appointmentList", appointmentList);
        modelAndView.addObject("consultantList", consultantList);

        return modelAndView;
    }

    @GetMapping("consultant/appointment/myappointment")
    public ModelAndView myAppointmentConsultant(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consultant/myAppointment");

        HttpSession session = request.getSession();
        Consultant consultant = (Consultant) session.getAttribute("consultant");

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList = appointmentRepository.findAppointmentByConsultantid(consultant.getId());

        List<Student> studentList = studentService.ListStudents();

        modelAndView.addObject("appointmentList", appointmentList);
        modelAndView.addObject("studentList", studentList);

        return modelAndView;
    }
}
