package com.example.librarymanagment.service.services;

import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Teams;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.TeamsRepository;
import com.example.librarymanagment.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Primary
public  class  TeamsServices implements TeamsService {
    @Autowired
    private  TeamsRepository teamsRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Teams> findAllTeams() {
        return teamsRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Teams findTeamsById(Long id) {
        return teamsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Team not found with ID %d", id)));
    }

    @Override
    public void createTeams(Teams team) {
        teamsRepository.save(team);
    }

    @Override
    public Teams updateTeams(Teams team) {
        boolean exist = teamsRepository.existsById(team.getId());
        if(exist){
            return teamsRepository.save(team);
        }
        return null;
    }



    @Override
    public void deleteTeams(Long id) {
        teamsRepository.deleteById(id);
    }

}
