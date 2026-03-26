package com.fitnest.controller;

import com.fitnest.dto.request.CreateUserRequest;
import com.fitnest.dto.request.UpdateUserRequest;
import com.fitnest.dto.response.ResponseModel;
import com.fitnest.dto.response.UserResponse;
import com.fitnest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseModel<?>> createUser(@Valid @RequestBody CreateUserRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel<>(true, 201, "User created", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<?>> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {

        UserResponse response = userService.updateUser(id, request);

        return ResponseEntity.ok(new ResponseModel<>(true, 200, "User updated", response));
    }

    @GetMapping("/email")
    public ResponseEntity<ResponseModel<?>> getByEmail(@RequestParam String email) {

        UserResponse response = userService.getUserByEmail(email);

        return ResponseEntity.ok(new ResponseModel<>(true, 200, "User fetched", response));
    }

    @GetMapping
    public ResponseEntity<ResponseModel<?>> getAll() {

        List<UserResponse> allUsers = (List<UserResponse>) userService.getAllUsers();

        return ResponseEntity.ok(new ResponseModel<>(true, 200, "Users fetched", allUsers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<?>> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(new ResponseModel<>(true, 200, "User deleted", null));
    }
}
