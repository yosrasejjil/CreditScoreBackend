package com.yosra.jwt.backend.dtos;

public record CredentialsDto (String login, char[] password) { }