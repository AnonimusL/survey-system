package com.survey.users.UserService.dto.for_target_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RafUsersCsv {
    private String redniBroj;
    private String imeIPrezime;
    private String indeks;
    private String grupa;

    @Override
    public String toString() {
        return redniBroj + ". Ime i prezime: " + imeIPrezime + " indeks: " + indeks + " grupa: " + grupa;
    }
}
