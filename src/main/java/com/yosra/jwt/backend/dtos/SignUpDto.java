package com.yosra.jwt.backend.dtos;

public record SignUpDto (Long id,String firstName, String lastName, String login, char[] password) { }
