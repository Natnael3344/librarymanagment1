package com.example.librarymanagment.controller;
import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.exceptions.ResponseHandler;
import com.example.librarymanagment.repository.AssetsRepository;
import com.example.librarymanagment.service.AssetsService;
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
public class AssetsController {

    @Autowired
    private  AssetsService assetsService;
    @Autowired
    private AssetsRepository assetsRepository;


    @PostMapping("/save")
    public Assets saveAsset(@RequestBody Assets asset) {

        return assetsRepository.save(asset);
    }
    @GetMapping("/view")
    public List<Assets> findAllAssets(){
        return assetsRepository.findAll();
    }




    @PutMapping("/edit")
    public ResponseEntity<Object> editProduct(@RequestBody @Valid Assets asset){
        try {
            Assets editedAsset = assetsService.updateAssets(asset);
            if(editedAsset!=null){
                return ResponseHandler.handleResponse("Successfully edit product", HttpStatus.OK,editedAsset);
            }else{
                return ResponseHandler.handleResponse("Product Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAsset(@PathVariable Long id){
        try {
            assetsService.deleteAssets(id);
            return ResponseHandler.handleResponse("Successfully delete product", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
