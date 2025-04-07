package com.survey.users.UserService.dto.for_target_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private String predmet;
    private String tip;
    private String nastavnik;
    private String grupe;
    private String dan;
    private String termin;
    private String ucionica;

    @Override
    public String toString() {
        return this.predmet;
    }
}
