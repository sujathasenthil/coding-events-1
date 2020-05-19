package org.launchcode.codingevents.controllers;

import jdk.jfr.Description;
import org.launchcode.codingevents.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {
//@Controller
//@RequestMapping("events")
//public class EventController {

  //  private static List<Event> events = new ArrayList<Event>();
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayform(Model model){
    model.addAttribute("title","All Events");
    model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }
    @GetMapping("index")
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }
    @GetMapping("create")
    public String createform(Model model){
        model.addAttribute("title","Create Events");
        model.addAttribute(new Event());
        return "events/create";
    }


    @PostMapping("create")
    public String Reqcreateform(@ModelAttribute @Valid Event newEvent, Errors errors,Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        newEvent.setName(newEvent.getName());
        newEvent.setDescription(newEvent.getDescription());
        eventRepository.save(newEvent);
        return "redirect:";
    }
    @GetMapping("delete")
    public String deleteform(Model model){
        model.addAttribute("title","delete Events");
        model.addAttribute("events",eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String Reqdeleteform(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null ){
            for (int eventId:eventIds){
                  eventRepository.deleteById(eventId);
        }
    }

    return "redirect:";
    }

}


//@Controller
//@RequestMapping("events")
//public class EventController {
//
//    private static List<Event> events = new ArrayList<>();
//
//    @GetMapping
//    public String displayAllEvents(Model model) {
//        model.addAttribute("title", "All Events");
//        model.addAttribute("events", events);
//        return "events/index";
//    }
//
//    @GetMapping("create")
//    public String displayCreateEventForm(Model model) {
//        model.addAttribute("title", "Create Event");
//        return "events/create";
//    }
//
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName) {
//        events.add(new Event(eventName));
//        return "redirect:";
//    }
//
//}
