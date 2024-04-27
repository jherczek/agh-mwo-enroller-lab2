package com.company.enroller.controllers;

import com.company.enroller.model.Participant;
import com.company.enroller.persistence.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;

import java.util.Collection;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {


    @Autowired
    MeetingService meetingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<Meeting>> getAll() {
        Collection<Meeting> allMeetings = meetingService.getAll();
        if (allMeetings != null) {
            return ResponseEntity.ok(allMeetings);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Meeting> getMeeting(@PathVariable("id") long id) {
        Meeting meeting = meetingService.findByID(id);
        if (meeting == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meeting, HttpStatus.OK);
    }

}