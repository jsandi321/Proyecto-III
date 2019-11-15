package com.petcare.model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Status {
    //Atributos
    private int happiness, health, dirt, hungry, petScore=0;
    private int curarDelay=0, limpiarDelay=0, acariciarDelay=0, alimentarDelay=0;

    private ArrayList<Integer> affectionHours = new ArrayList<>();
    private ArrayList<Integer> eatingHours = new ArrayList<>();
    private ArrayList<Integer> hygieneHours = new ArrayList<>();
    private ArrayList<Integer> medicationHours = new ArrayList<>();

    //Contructores
    public Status(){
        this.happiness = 5;
        this.health = 5;
        this.dirt = 0;
        this.hungry = 0;
    }

    //Getters and Setters
    public int getHappiness() {
        return happiness;
    }

    public int getHealth() {
        return health;
    }

    public int getDirt() {
        return dirt;
    }

    public int getHungry() {
        return hungry;
    }

    public int getPetScore() {
        return petScore;
    }

    public int getCurarDelay() {
        return curarDelay;
    }

    public void setCurarDelay(int curarDelay) {
        this.curarDelay = curarDelay;
    }

    public int getLimpiarDelay() {
        return limpiarDelay;
    }

    public void setLimpiarDelay(int limpiarDelay) {
        this.limpiarDelay = limpiarDelay;
    }

    public int getAcariciarDelay() {
        return acariciarDelay;
    }

    public void setAcariciarDelay(int acariciarDelay) {
        this.acariciarDelay = acariciarDelay;
    }

    public int getAlimentarDelay() {
        return alimentarDelay;
    }

    public void setAlimentarDelay(int alimentarDelay) {
        this.alimentarDelay = alimentarDelay;
    }

    public ArrayList<Integer> getAffectionHours() {
        return affectionHours;
    }

    public ArrayList<Integer> getEatingHours() {
        return eatingHours;
    }

    public ArrayList<Integer> getHygieneHours() {
        return hygieneHours;
    }

    public ArrayList<Integer> getMedicationHours() {
        return medicationHours;
    }

    //Retorna un String en formato JSON con toda la información de todos los atributos de Status
    String getStatusJSON(){
        return new Gson().toJson(this);
    }


    // METODOS

    // Incrementa el PetScore la cantidad de entrada
    public void incPetScore(int n){
        petScore = petScore+n;
    }

    // Disminuye el PetScore la cantidad de entrada
    public void decPetScore(int n){
        petScore = petScore-n;
    }

    // Incrementa la felicidad la cantidad de entrada
    public void incHappiness(int n){
        if(happiness+n<6){
            happiness = happiness+n;
        }else{
            happiness=5;
        }
    }

    // Disminuye la felicidad la cantidad de entrada
    public void decHappiness(int n){
        if(happiness-n>=0){
            happiness = happiness-n;
        }else{
            happiness=0;
        }
    }

    // Incrementa la salud la cantidad de entrada
    public void incHealth(int n){
        if(health+n<6){
            health = health+n;
        }else{
            health=5;
        }
    }

    // Disminuye la salud la cantidad de entrada
    public void decHealth(int n){
        if(health-n>=0){
            health = health-n;
        }else{
            health=0;
        }
    }

    // Incrementa la suciedad la cantidad de entrada
    public void incDirt(int n){
        if(dirt+n<6){
            dirt = dirt+n;
        }else{
            dirt=5;
        }
    }

    // Disminuye la suciedad la cantidad de entrada
    public void decDirt(int n){
        if(dirt-n>=0){
            dirt = dirt-n;
        }else{
            dirt=0;
        }
    }

    // Incrementa el hambre la cantidad de entrada
    public void incHungry(int n){
        if(hungry+n<6){
            hungry = hungry+n;
        }else{
            hungry=5;
        }
    }

    // Disminuye el hambre la cantidad de entrada
    public void decHungry(int n){
        if(hungry-n>=0){
            hungry = hungry-n;
        }else{
            hungry = 0;
        }
    }
}
