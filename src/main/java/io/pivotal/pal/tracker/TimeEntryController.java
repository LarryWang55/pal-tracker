package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create (@RequestBody TimeEntry timeEntry) {
        TimeEntry savedEntry = timeEntryRepository.create(timeEntry);
        return new ResponseEntity(savedEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (timeEntry != null)
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(id, expected);
        if (timeEntry != null)
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
