package com.petcare.model;

public class Pet {
    //Atributos
    private String name;
    private Status status;

    //Contructor
    public Pet(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    //Getters
    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    //Retorna un String en formato JSON con toda la información de todos los atributos de Pet
    String getPetJSON(){
        String petJSON = String.format("{\"Name\": \"%s\",", this.name);

        String statusJSON = String.format("\"Status\": %s }", this.status.getStatusJSON());

        petJSON = petJSON.concat(statusJSON);

        return petJSON;
    }
}
