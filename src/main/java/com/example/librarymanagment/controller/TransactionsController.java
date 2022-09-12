package com.example.librarymanagment.controller;
import com.example.librarymanagment.entity.Transactions;
import com.example.librarymanagment.exceptions.ResponseHandler;
import com.example.librarymanagment.repository.TransactionsRepository;
import com.example.librarymanagment.service.TransactionsService;
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
public class TransactionsController {
    @Autowired
    private  TransactionsService transactionsService;

    @Autowired
    private TransactionsRepository transactionsRepository;


    @PostMapping("/saveTransaction")
    public Transactions createTransactions(@RequestBody Transactions transaction) {

        return transactionsRepository.save(transaction);
    }
    @GetMapping("/viewTransaction")
    public List<Transactions> findAllTransactions(){
        return transactionsRepository.findAll();
    }




    @PutMapping("/editTransaction")
    public ResponseEntity<Object> editTransaction(@RequestBody @Valid Transactions transaction){
        try {
            Transactions editedTransaction = transactionsService.updateTransactions(transaction);
            if(editedTransaction!=null){
                return ResponseHandler.handleResponse("Successfully edit product", HttpStatus.OK,editedTransaction);
            }else{
                return ResponseHandler.handleResponse("Product Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/deleteTransaction/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
        try {
            transactionsService.deleteTransactions(id);
            return ResponseHandler.handleResponse("Successfully delete product", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}