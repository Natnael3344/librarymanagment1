package com.example.librarymanagment.service.services;
import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.exceptions.NotFoundException;
import com.example.librarymanagment.repository.AssetsRepository;
import com.example.librarymanagment.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
public  class AssetsServices implements AssetsService {

    @Autowired
    private  AssetsRepository assetsRepository;


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Assets> findAllAssets() {
        return assetsRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Assets findAssetsById(Long id) {
        return assetsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
    }

    @Override
    public void saveAssets(Assets asset) {
        assetsRepository.save(asset);
    }


    @Override
    public Assets updateAssets(Assets asset) {
        boolean exist = assetsRepository.existsById(asset.getId());
        if(exist){
             return assetsRepository.save(asset);
        }
        return null;
    }


    @Override
    public void deleteAssets(Long id) {
        assetsRepository.deleteById(id);
    }

}


