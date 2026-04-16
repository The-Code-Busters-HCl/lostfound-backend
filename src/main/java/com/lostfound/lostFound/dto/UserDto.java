package com.lostfound.lostFound.dto;

import com.lostfound.lostFound.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String branch;
    private String mobileNo;
    private String role;

    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getName(),
                user.getBranch(),
                user.getMobileNo(),
                user.getRole()
            );
    }
}
