package com.petcare.model;

import com.petcare.util.Time;

import java.util.ArrayList;

public class Player {
    //Atributos
    private String name;
    private Integer playerScore = 0;
    private ArrayList<Pet> pets;

    //Contructores
    public Player(String name){
        this.name = name;
        this.pets = new ArrayList<>();
    }

    public Player(String name, String playerScore, ArrayList<Pet> pets) {
        this.name = name;
        this.playerScore = Integer.parseInt(playerScore);
        this.pets = pets;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(Integer playerScore) {
        this.playerScore = playerScore;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    //Retorna el Pet con el nombre de entrada
    public Pet getPetByName(String petName){
        for (Pet pet : pets){
            if(pet.getName().equals(petName)){
                return pet;
            }
        }return null;
    }

    //Retorna un String en formato JSON con toda la información de todos los atributos de Player
    public String getPlayerJSON() {

        String playerJSON = String.format("{\"Name\":\"%s\",", this.name);
        String playerScoreJSON = String.format("\"PlayerScore\":%d,", this.playerScore);
        String petsJSON = "\"Pets\":[";
        String timeJSON = "\"Time\":";


        String petsArrayJSON = "";
        for (Pet pet : pets){
            petsArrayJSON = petsArrayJSON.concat(pet.getPetJSON()+",");
        }
        petsArrayJSON = petsArrayJSON.substring(0, petsArrayJSON.length()-1);
        petsJSON = petsJSON.concat(petsArrayJSON+"],");

        String timeArrayJSON = "";
        timeArrayJSON = timeArrayJSON.concat(Time.getTimeArrayJSON());



        timeJSON = timeJSON.concat(timeArrayJSON);

        playerJSON = playerJSON.concat(playerScoreJSON);
        playerJSON = playerJSON.concat(petsJSON);
        playerJSON = playerJSON.concat(timeJSON+"}");

        return playerJSON;
    }

    //Metodos

    //Añade al final del ArrayList llamado pets, el Pet recibido.
    public void addPet(Pet pet){
        pets.add(pet);
    }

}
