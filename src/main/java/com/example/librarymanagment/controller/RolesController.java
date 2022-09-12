package com.example.librarymanagment.controller;
import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Roles;
import com.example.librarymanagment.exceptions.ResponseHandler;
import com.example.librarymanagment.repository.AssetsRepository;
import com.example.librarymanagment.repository.RolesRepository;
import com.example.librarymanagment.service.AssetsService;
import com.example.librarymanagment.service.RolesService;
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
public class RolesController {
    @Autowired
    private  RolesService rolesService;
    @Autowired
    private RolesRepository rolesRepository;

    @PostMapping("/saveRoles")
    public Roles saveRole(@RequestBody Roles role) {

        return rolesRepository.save(role);
    }
    @GetMapping("/viewRoles")
    public List<Roles> findAllRoles(){
        return rolesRepository.findAll();
    }




    @PutMapping("/editRoles")
    public ResponseEntity<Object> editProduct(@RequestBody @Valid Roles role){
        try {
            Roles editedRoles = rolesService.updateRoles(role);
            if(editedRoles!=null){
                return ResponseHandler.handleResponse("Successfully edit product", HttpStatus.OK,editedRoles);
            }else{
                return ResponseHandler.handleResponse("Role Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/deleteRoles/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id){
        try {
            rolesService.deleteRoles(id);
            return ResponseHandler.handleResponse("Successfully delete Role", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
