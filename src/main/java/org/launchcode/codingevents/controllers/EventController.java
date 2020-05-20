package org.launchcode.codingevents.controllers;

import jdk.jfr.Description;
import org.launchcode.codingevents.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String displayform(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("index")
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create")
    public String createform(Model model) {
        model.addAttribute("title", "Create Events");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String Reqcreateform(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        newEvent.setName(newEvent.getName());
        newEvent.setDescription(newEvent.getDescription());
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String deleteform(Model model) {
        model.addAttribute("title", "delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String Reqdeleteform(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int eventId : eventIds) {
                eventRepository.deleteById(eventId);
            }
        }

        return "redirect:";
    }

    @GetMapping("events/edit/{eventId}")
    public String editAllEvents(@PathVariable int eventId, Event event, BindingResult bindingResult, Model model) {
        model.addAttribute("title", "Edit Event");
        model.addAttribute("eventId",eventId);
        model.addAttribute("events", eventRepository.findById(eventId));
        Event event1=eventRepository.findById(eventId).get();
        model.addAttribute("name",event1.getName());
        model.addAttribute("desc",event1.getDescription());
        return "events/edit";
    }

    @PostMapping("events/edit/{eventId}")
    public String Reqeditform(@PathVariable int eventId, @RequestParam String name,
                              @RequestParam String description, Event event1) {
            Event event=eventRepository.findById(eventId).get();
            event.setName(name);
            event.setDescription(description);
            eventRepository.save(event);
            return "events/index";

    }
}

