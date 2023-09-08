package com.myapp.myclient.user.model;

import com.myapp.UserDetailsResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDetails {
    private String id;
    private Integer numericId;
    private String firstName;
    private String lastName;
    private String city;

    public UserDetails(UserDetailsResponse response) {
        this.firstName = response.getFirstName();
        this.lastName = response.getLastName();
        this.city = response.getCity();
        this.id = response.getId();
        this.numericId = response.getNumericId();
    }
}