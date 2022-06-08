package com.example.itransitiontask4.dto.request.user;

import com.example.itransitiontask4.dto.request.user.EditRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditRequestDtoList {

    List<EditRequestDto> list;

}
