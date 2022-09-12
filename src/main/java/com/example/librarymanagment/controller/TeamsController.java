package com.example.librarymanagment.controller;
import com.example.librarymanagment.entity.Teams;
import com.example.librarymanagment.exceptions.ResponseHandler;
import com.example.librarymanagment.repository.TeamsRepository;
import com.example.librarymanagment.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.List;

@RestController
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT, RequestMethod.DELETE,}
)
@Configuration
public class TeamsController {
    @Autowired
    private  TeamsService teamsService;
    @Autowired
    private TeamsRepository teamsRepository;

    @PostMapping("/saveTeams")
    public Teams saveTeam(@RequestBody Teams team) {

        return teamsRepository.save(team);
    }
    @GetMapping("/viewTeams")
    public List<Teams> findAllTeams(){
        return teamsRepository.findAll();
    }




    @PutMapping("/editTeams")
    public ResponseEntity<Object> editTeam(@RequestBody @Valid Teams team){
        try {
            Teams editedTeam = teamsService.updateTeams(team);
            if(editedTeam!=null){
                return ResponseHandler.handleResponse("Successfully edit Team", HttpStatus.OK,editedTeam);
            }else{
                return ResponseHandler.handleResponse("Teams Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/deleteTeams/{id}")
    public ResponseEntity<Object> deleteTeam(@PathVariable Long id){
        try {
            teamsService.deleteTeams(id);
            return ResponseHandler.handleResponse("Successfully delete Teams", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
