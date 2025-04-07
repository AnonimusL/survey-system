package com.survey.users.UserService.controller;

import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.dto.user.UserCreateDto;
import com.survey.users.UserService.dto.user.UserDto;
import com.survey.users.UserService.dto.user.UserFilterDto;
import com.survey.users.UserService.dto.user.UserUpdateDto;
import com.survey.users.UserService.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAll(Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/all/filter")
    public ResponseEntity<Page<UserDto>> getAllFilter(@RequestBody UserFilterDto userFilterDto, Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsersFiltered(userFilterDto, pageable), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDto> get(@RequestParam String username){
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> post(@RequestBody UserCreateDto userCreateDto){
        return new ResponseEntity<>(userService.addNewUser(userCreateDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> put(@RequestBody UserUpdateDto userUpdateDto){
        return new ResponseEntity<>(userService.updateUser(userUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<MessageDto> delete(@PathVariable String username){
        return new ResponseEntity<>(userService.deleteUser(username), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getRoles(){
        return new ResponseEntity<>(userService.getAllRoles(), HttpStatus.OK);
    }

}
