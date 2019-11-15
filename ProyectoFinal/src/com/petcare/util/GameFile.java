package com.petcare.util;

import com.petcare.model.Pet;
import com.petcare.model.Player;
import com.petcare.model.Status;

import com.google.gson.Gson;    //Convertir JSON a objecto (SE USA PARA CREAR EL OBJECTO STATUS DE CADA PET)
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;   //Trabajar con archivos
import java.util.ArrayList;

// Esta clase trata con todo lo que tiene que ver con el archivo de guardado de la partida, Carga de juego y guardado.
public class GameFile {

    //Atributo
    private static String fileName = "autosave";


    //Getter
    public static String getFileName() {
        return fileName;
    }


    //Setter
    public static void setFileName(String fileName) {
        GameFile.fileName = fileName;
    }


    //Metodos

    //FUENTE: https://www.baeldung.com/reading-file-in-java
    //Retorna un String con el JSON del archivo ("SaveGames/"+fileName+".json")
    private static String getJSONStringFromFile() throws IOException {
        FileInputStream inputStream = new FileInputStream("SaveGames/"+fileName+".json");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (
                BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }


    //FUENTE: https://www.baeldung.com/java-write-to-file
    //Escribe en el archivo ("SaveGames/"+fileName+".json") el String de entrada.
    private static void WriteStrToFile(String str) throws IOException {
        FileOutputStream fops = new FileOutputStream("SaveGames/"+fileName+".json");
        byte[] strToBytes = str.getBytes();
        fops.write(strToBytes);
        fops.close();
    }

    //Retorna un Player creado con el JSON en el archivo ("SaveGames/"+fileName+".json")
    public static Player loadGameDate() throws IOException, ParseException {

        Gson gson = new Gson();                     //Gson initialization
        JSONParser parser = new JSONParser();       //JSONParser initialization
        ArrayList<Pet> pets = new ArrayList<>();    //PLAYER PETS ARRAYLIST


        String JSONString = getJSONStringFromFile();   //JSON STRING FROM FILE

        JSONObject json = (JSONObject) parser.parse(JSONString);    //JSONObject from String

        JSONArray JSONPetsArray = (JSONArray) json.get("Pets");      // JSONArray from JSONObject.get("Pets")


        for (Object petJSON : JSONPetsArray) {   //For Object in JSONPetsArray

            JSONObject JSONPet = (JSONObject) parser.parse(petJSON.toString());     //JSONObject from JSONPetsArray

            Status status = gson.fromJson(JSONPet.get("Status").toString(), Status.class);      //StatusObject from JSONPet.get("Status")

            String petName = JSONPet.get("Name").toString();    //PetName from JSONPet.get("Name")

            Pet pet = new Pet(petName, status);  //PetObject from Pet(petName, status)

            pets.add(pet);  //Add pet to pets ArrayList
        }

        JSONArray JSONTimeArray = (JSONArray) json.get("Time");  //JSONTimeArray from json.get("Time")

        Time.setIntDay(Integer.parseInt(JSONTimeArray.get(0).toString()));       //Day from JSONTimeArray index 0
        Time.setIntHours(Integer.parseInt(JSONTimeArray.get(1).toString()));     //Hours from JSONTimeArray index 1
        Time.setIntMinutes(Integer.parseInt(JSONTimeArray.get(2).toString()));   //Minutes from JSONTimeArray index 2

        Player player;
        player = new Player(json.get("Name").toString(), json.get("PlayerScore").toString(), pets);

        return player;
    }

    // Guarda los datos del juego en un archivo con formato JSON
    static void saveGameData(Player player) throws IOException {
        WriteStrToFile(player.getPlayerJSON());
        System.out.println("Archivo Guardado!\n"+player.getPlayerJSON());
    }

}
