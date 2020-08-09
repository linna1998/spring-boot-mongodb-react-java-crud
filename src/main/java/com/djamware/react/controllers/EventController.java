package com.djamware.react.controllers;

import com.djamware.react.models.Event;
import com.djamware.react.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/event")
    public Iterable<Event> event() {
        return eventRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/events")
    public Event save(@RequestBody Event event) {
        eventRepository.save(event);
        return event;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events/{id}")
    public Optional<Event> show(@PathVariable String id) {
        return eventRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/events/{id}")
    public Event update(@PathVariable String id, @RequestBody Event Event) {
        Optional<Event> optEvent = eventRepository.findById(id);
        Event c = optEvent.get();
        if (Event.getName() != null)
            c.setName(Event.getName());
        if (Event.getLink() != null) {
            c.setLink(Event.getLink());
        }
        if (Event.getStartTime() != null) {
            c.setStartTime(Event.getStartTime());
        }
        if (Event.getEndTime() != null) {
            c.setEndTime(Event.getEndTime());
        }
        if (Event.getCategory() != null) {
            c.setCategory(Event.getCategory());
        }
        if (Event.getDescription() != null) {
            c.setDescription(Event.getDescription());
        }
        eventRepository.save(c);
        return c;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public String delete(@PathVariable String id) {
        Optional<Event> optEvent = eventRepository.findById(id);
        Event Event = optEvent.get();
        eventRepository.delete(Event);

        return "";
    }
}
