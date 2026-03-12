package com.embarkx.ChallengeApp;

import com.embarkx.ChallengeApp.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(),HttpStatus.OK);// use instance
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge); // only once
        if (isChallengeAdded) {
            return new ResponseEntity<>("Challenge added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not added", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(
            @PathVariable String month) {
       Challenge challenge = challengeService.getChallenge(month);
       if(challenge!= null)
           return new ResponseEntity<>(challenge, HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);// use instance
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updateChallenge) {
      boolean isChallengeUpdted =  challengeService.updateChallenge(id,updateChallenge);
        if (isChallengeUpdted) {
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not updated", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        boolean deleteChallenge = challengeService.deleteChallenge(id);
        if(deleteChallenge) {
            return new ResponseEntity("challenge deleted successfully",
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity("challenge not found",
                    HttpStatus.NOT_FOUND);
    }

}