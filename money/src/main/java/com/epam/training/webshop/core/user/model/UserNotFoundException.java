package com.epam.training.webshop.core.user.model;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final Long id) {
        super("User is not existing with ID:" + id);
    }
}
