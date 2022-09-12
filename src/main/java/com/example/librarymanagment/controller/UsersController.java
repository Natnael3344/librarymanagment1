package com.example.librarymanagment.controller;
import com.example.librarymanagment.entity.Assets;
import com.example.librarymanagment.entity.Users;
import com.example.librarymanagment.exceptions.ResponseHandler;
import com.example.librarymanagment.repository.AssetsRepository;
import com.example.librarymanagment.repository.UsersRepository;
import com.example.librarymanagment.service.AssetsService;
import com.example.librarymanagment.service.UsersService;
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
public class UsersController {
    @Autowired
    private  UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/saveUser")
    public Users saveAsset(@RequestBody Users user) {

        return usersRepository.save(user);
    }
    @GetMapping("/viewUser")
    public List<Users> findAllUsers(){
        return usersRepository.findAll();
    }




    @PutMapping("/editUser")
    public ResponseEntity<Object> editUser(@RequestBody @Valid Users user){
        try {
            Users editedUser = usersService.updateUsers(user);
            if(editedUser!=null){
                return ResponseHandler.handleResponse("Successfully edit user", HttpStatus.OK,editedUser);
            }else{
                return ResponseHandler.handleResponse("user Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        try {
            usersService.deleteUsers(id);
            return ResponseHandler.handleResponse("Successfully delete user", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
