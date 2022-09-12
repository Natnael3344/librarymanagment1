package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TeamsService {
    @Autowired
    public List<Teams> findAllTeams();

    public Teams findTeamsById(Long id);

    public void createTeams(Teams team);

    public Teams updateTeams(Teams team);

    public void deleteTeams(Long id);
}
