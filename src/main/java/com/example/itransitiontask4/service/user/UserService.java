package com.example.itransitiontask4.service.user;

import com.example.itransitiontask4.dto.request.user.EditRequestDtoList;
import com.example.itransitiontask4.dto.response.ApiResponse;
import com.example.itransitiontask4.dto.response.user.UserResponseDto;
import com.example.itransitiontask4.model.entity.UserEntity;
import com.example.itransitiontask4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.itransitiontask4.model.enums.UserStateEnum.*;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ApiResponse getList(){

        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for (UserEntity user : userEntityList){
            if (user.getStates() != DELETED) userResponseDtoList.add(modelMapper.map(user, UserResponseDto.class));
        }

        return new ApiResponse(1, "success", userResponseDtoList);
    }


    public ApiResponse edit(EditRequestDtoList userEditList){

        List<UserEntity> userEntityList = userRepository.findAll();

        userEditList.getList().stream().forEach(editRequestDto -> {
            for (UserEntity user : userEntityList){
                if(user.getEmail().equals(editRequestDto.getEmail()) && user.getStates() != DELETED){
                    if (editRequestDto.getStatesCode() == 0) {
                        user.setStates(ACTIVE);
                        user.setEnable(true);
                    }
                    if (editRequestDto.getStatesCode() == 1) {
                        user.setStates(BLOCKED);
                        user.setEnable(false);
                    }
                    if (editRequestDto.getStatesCode() == 2) {
                        user.setStates(DELETED);
                    }
                }
            }
        });
        userRepository.saveAll(userEntityList);
        return getList();
    }



}
