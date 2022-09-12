package com.example.librarymanagment.controller;
import com.example.librarymanagment.entity.Permissions;
import com.example.librarymanagment.exceptions.ResponseHandler;
import com.example.librarymanagment.repository.PermissionsRepository;
import com.example.librarymanagment.service.PermissionsService;
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
public class PermissionsController {
    @Autowired
    private  PermissionsService permissionsService;
    @Autowired
    private PermissionsRepository permissionsRepository;


    @PostMapping("/savePermissions")
    public Permissions savePermission(@RequestBody Permissions permission) {

        return permissionsRepository.save(permission);
    }
    @GetMapping("/viewPermissions")
    public List<Permissions> findAllPermissions(){
        return permissionsRepository.findAll();
    }




    @PutMapping("/editPermissions")
    public ResponseEntity<Object> editPermission(@RequestBody @Valid Permissions permission){
        try {
            Permissions editedPermission = permissionsService.updatePermissions(permission);
            if(editedPermission!=null){
                return ResponseHandler.handleResponse("Successfully edit product", HttpStatus.OK,editedPermission);
            }else{
                return ResponseHandler.handleResponse("Product Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/deletePermissions/{id}")
    public ResponseEntity<Object> deletePermission(@PathVariable Long id){
        try {
            permissionsService.deletePermissions(id);
            return ResponseHandler.handleResponse("Successfully delete product", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}

