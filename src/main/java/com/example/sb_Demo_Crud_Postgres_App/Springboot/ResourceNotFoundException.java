package com.example.sb_Demo_Crud_Postgres_App.Springboot;

public class ResourceNotFoundException extends Exception {
    ResourceNotFoundException(Long employeeId){
        super("Could not find employee: " + employeeId);
    }
}
