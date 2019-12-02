package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.create(newEvent);
        return "redirect:";
    }

    @GetMapping("remove")
    public String displayRemoveEventForm(Model model) {
        model.addAttribute("events", EventData.getAll());
        model.addAttribute("title", "Remove Event");
        return "events/remove";
    }

    @PostMapping("remove")
    public String processRemoveEventForm(@RequestParam int[] eventIds) {

        for (int eventId : eventIds) {
            EventData.remove(eventId);
        }

        return "redirect:";
    }

}
