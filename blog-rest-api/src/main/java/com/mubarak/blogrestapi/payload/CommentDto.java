package com.mubarak.blogrestapi.payload;

import lombok.Data;

@Data
public class CommentDto {

    private long id;
    // name should not be null or empty
//    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    // email should not be null or empty
    // email field validation
    private String email;

    // comment body should not be bull or empty
    // Comment body must be minimum 10 characters
//    @NotEmpty
//    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String message;

}
