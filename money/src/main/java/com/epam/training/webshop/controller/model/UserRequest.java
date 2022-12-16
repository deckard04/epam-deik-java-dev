package com.epam.training.webshop.controller.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@Getter
@JsonDeserialize(builder = UserRequest.UserRequestBuilder.class)
@ToString
public class UserRequest {

    private final String username;
    private final String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserRequestBuilder {

    }

}
