package com.survey.users.UserService.dto.for_target_user;

import com.survey.users.UserService.domain.target_user.TargetUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoadedUsers {
    private List<TargetUser> success;
    private List<TargetUser> fail;

    public LoadedUsers(){
        success = new ArrayList<>();
        fail = new ArrayList<>();
    }
}
