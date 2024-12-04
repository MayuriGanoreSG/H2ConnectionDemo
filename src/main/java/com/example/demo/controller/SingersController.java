package com.example.demo.controller;

import com.example.demo.entity.Singers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.SingersService;
import java.util.List;

@RestController
@RequestMapping("rest/api/singers")
public class SingersController {
    @Autowired
    private SingersService singersService;

    //creating new singer
    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody Singers singers) {
        ResponseEntity<String> responseEntity=null;
        try{
            Integer integer=singersService.saveSingers(singers);
            responseEntity=new ResponseEntity<String>("Singer "+integer+" created", HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return responseEntity;
    }

    //updating singers
    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@RequestBody Singers singers) {
        ResponseEntity<String> responseEntity=null;
        boolean available=singersService.isAvailable(singers.getSingersPosition());
        if(available){
            singersService.update(singers);
            responseEntity=new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
        }
        else{
            responseEntity=new ResponseEntity<String>("Record "+singers.getSingersPosition()+" not found",HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    //Delete Operation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable Integer id) {
        ResponseEntity responseEntity=null;
        boolean available=singersService.isAvailable(id);
        if(available){
            singersService.delete(id);
            responseEntity=new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
        }
        else{
            responseEntity=new ResponseEntity<String>("Record "+id+" not found",HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

//    @GetMapping("getSingleSinger/{id}")
//    public ResponseEntity getSingleSingerbyId(@PathVariable Integer id) {
//        ResponseEntity<String> responseEntity=null;
//        if(singersService.isAvailable(id)){
//            Singers oneSinger=singersService.getOneSinger(id);
//            responseEntity=new ResponseEntity<Singers>(oneSinger,HttpStatus.OK);
//        }
//        else
//        {
//            responseEntity= new ResponseEntity("Record "+id+" not found",HttpStatus.BAD_REQUEST);
//        }
//        return responseEntity;
//    }


    //get single singer
    @GetMapping("getSingleSinger/{id}")  // Add {id} in the URL path
    public ResponseEntity<?> getSingleSingersbyId(@PathVariable Integer id) {
        if (singersService.isAvailable(id)) {
            Singers oneSinger = singersService.getOneSinger(id);
            return new ResponseEntity<>(oneSinger, HttpStatus.OK); // Return Singers object
        } else {
            return new ResponseEntity<>("Record " + id + " not found", HttpStatus.BAD_REQUEST); // Return error message
        }
    }


    //get all singers
    @GetMapping("/getAllsingers")
    public ResponseEntity<?> getAllSingers() {
        ResponseEntity responseEntity=null;
        List<Singers>allSingers=singersService.getAllSingers();
        if(allSingers==null||allSingers.isEmpty()){
            String message ="No Data Found";
            responseEntity=new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
        }
        else {
            responseEntity=new ResponseEntity<List<Singers>>(allSingers, HttpStatus.OK);
        }
        return responseEntity;
    }


}
