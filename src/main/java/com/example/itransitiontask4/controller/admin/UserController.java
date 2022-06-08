package com.example.itransitiontask4.controller.admin;

import com.example.itransitiontask4.dto.request.user.EditRequestDtoList;
import com.example.itransitiontask4.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get_list")
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok(userService.getList());
    }

    @PreAuthorize(value = "hasAnyRole(\"USER\")")
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody EditRequestDtoList userEditList){
        return ResponseEntity.ok(userService.edit(userEditList));
    }


}
