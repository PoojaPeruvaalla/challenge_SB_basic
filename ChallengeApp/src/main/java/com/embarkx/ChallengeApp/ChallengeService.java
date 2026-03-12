package com.embarkx.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChallengeService {
    private static List<Challenge> challenges =
            new ArrayList<>();
    private static Long nextId = 1L;

   public ChallengeService() {


   }
   public static List<Challenge> getAllChallenges(){
       return challenges;
   }
    public boolean addChallenge( Challenge challenge) {
       if(challenge != null) {
           challenge.setId(nextId++);
           challenges.add(challenge);
           return true;

       }
       else return false;

    }

    public Challenge getChallenge(String month) {
       for(Challenge challenge : challenges) {
           if(challenge.getMonth().equalsIgnoreCase(month)) {
               return challenge;
           }
       }
    return null;
    }

    public boolean updateChallenge(Long id, Challenge updateChallenge) {
        for(Challenge challenge : challenges) {
            if(challenge.getId().equals(id)) {
                challenge.setMonth(updateChallenge.getMonth());
                challenge.setDescription(updateChallenge.getDescription());
                return true;
            }
        }
        return false;
    }
    public boolean deleteChallenge(Long id) {
        return challenges.removeIf(challenge -> challenge.getId().equals(id));
    }

}
