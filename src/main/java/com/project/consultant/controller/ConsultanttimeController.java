package com.project.consultant.controller;

import com.project.consultant.model.Consultant;
import com.project.consultant.model.Consulttime;
import com.project.consultant.repository.ConsulttimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class ConsultanttimeController {

    @Autowired
    private ConsulttimeRepository consulttimeRepository;

    @GetMapping("consultant/addtime")
    public ModelAndView addTimeView(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consultant/addTimeView");

        HttpSession session = request.getSession();
        Consultant consultant = (Consultant) session.getAttribute("consultant");

        List<Consulttime> consulttimeListView = new ArrayList<>();
        try{
            consulttimeListView = consulttimeRepository.findConsulttimeByconsultantid(consultant.getId());
        }
        catch (Exception e){
            System.out.println("Error : @PostMapping(\"consultant/addtime\")" + e);
        }

        modelAndView.addObject("consulttimeListView", consulttimeListView);

        return modelAndView;
    }

    @PostMapping("consultant/addtime")
    public ModelAndView addTime(HttpServletRequest request, @RequestParam("date") Date date, @RequestParam("hh") String hh, @RequestParam("mm") String mm, @RequestParam("hours") int hours) throws Exception {
        String ss = "00";
        String time = hh + ":" + mm + ":" + ss;
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("date: " + date.toString());
        System.out.println("time: " + time);
        boolean status =  true;

        HttpSession session = request.getSession();
        Consultant consultant = (Consultant) session.getAttribute("consultant");
        List<Consulttime> consulttimeList = consulttimeRepository.findByConsultantidAndDate(consultant.getId(), date);

        List<Consulttime> consulttimeListView = new ArrayList<>();
        try{
            consulttimeListView = consulttimeRepository.findConsulttimeByconsultantid(consultant.getId());
        }
        catch (Exception e){
            System.out.println("Error : @PostMapping(\"consultant/addtime\")" + e);
        }

        modelAndView.addObject("consulttimeListView", consulttimeListView);

        if(consulttimeList.size() != 0){
            for(Consulttime consulttime : consulttimeList){
                Time firtstime = consulttime.getFromtime();
                LocalTime localtime = firtstime.toLocalTime();
                localtime = localtime.plusMinutes(consulttime.getHours() * 60);
                String strLasttime = localtime.toString();
                String[] timeSplit=firtstime.toString().split(":");

                try {
                    String string1 = firtstime.toString();
                    System.out.println(string1);
                    java.util.Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(time1);
                    calendar1.add(Calendar.DATE, 1);


                    String string2 = strLasttime+":"+timeSplit[2];
                    System.out.println(string2);
                    java.util.Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(time2);
                    calendar2.add(Calendar.DATE, 1);

                    String someRandomTime = time;
                    java.util.Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(d);
                    calendar3.add(Calendar.DATE, 1);

                    java.util.Date x = calendar3.getTime();
                    if (x.after(calendar1.getTime()) && x.before(calendar2.getTime()) || x.equals(calendar1.getTime()) || x.equals(calendar2.getTime())) {
                        //checks whether the current time is between a and b
                        status = false;
                        System.out.println("checks whether the current time is between a and b: false");
                    }
                    else{
                        System.out.println("else if : checks whether the current time is between a and b: true");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                    System.out.println("Exception: false");
                }
            }
        }
        else{
            status = true;
            System.out.println("no value: true");
        }

        if(status==true){
            // can add time
            SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
            long ms = sdf1.parse(time).getTime();
            Time t = new Time(ms);

            Consulttime consulttime = new Consulttime();
            consulttime.setConsultantid(consultant.getId());
            consulttime.setFromtime(t);
            consulttime.setDate(date);
            consulttime.setHours(hours);
            consulttimeRepository.save(consulttime);

            modelAndView.setViewName("consultant/home");
        }
        else{
            // cannot add time
            modelAndView.setViewName("consultant/addTimeView");
            modelAndView.addObject("error", "The following time is already added, there is a clash");
            modelAndView.addObject("list");
        }
        return modelAndView;
    }
}
