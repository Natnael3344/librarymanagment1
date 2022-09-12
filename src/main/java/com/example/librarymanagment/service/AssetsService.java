package com.example.librarymanagment.service;

import com.example.librarymanagment.entity.Assets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AssetsService {

    @Autowired
    public List<Assets> findAllAssets();
    public Assets findAssetsById(Long id);

    public void saveAssets(Assets asset);

    Assets updateAssets(Assets asset);

    void deleteAssets(Long id);
}
