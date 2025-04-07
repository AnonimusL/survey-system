package com.survey.users.UserService.dto.for_target_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadTargetUsers {
    private List<MultipartFile> files;
    private Long organizationId;
    private MultipartFile config;
    private Map<Integer, String> emailRules;
    private boolean emailExists;
    private boolean hasHeader;
}
