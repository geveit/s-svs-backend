package com.geveit.s_svs.expection;

public class VersionNotFoundException extends RuntimeException{
    public VersionNotFoundException(String message) {
        super(message);
    }
}