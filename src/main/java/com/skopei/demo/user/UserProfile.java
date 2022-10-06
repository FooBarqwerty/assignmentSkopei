package com.skopei.demo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/***
 * class annotations generate builder pattern and getters
 * Json annotations restricts api from performing certain operations
 * javax validation annotations throw errors to the client upon violation
 */

@Builder
@Getter
public class UserProfile {

    @JsonProperty(access = READ_ONLY)
    private int id;
    private String name;
    @Email(message = "not a valid email") @NotNull
    @JsonProperty(access = WRITE_ONLY)
    private String email;
    @JsonIgnore
    private long creationDate;
    @JsonIgnore
    private long modDate;
    @JsonProperty(access = READ_ONLY)
    private boolean deleted;
}