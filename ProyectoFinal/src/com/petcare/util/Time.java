package com.petcare.util;
import java.util.ArrayList; // import the ArrayList class

public class Time extends Thread{
    //Atributos
    private static String day="Lunes", hours="00", minutes="00";
    private static int intDay=1, intHours=0, intMinutes=0;
    private static Thread thread;


    //Getters
    static int getIntHours() {
        return intHours;
    }

    //Retorna un String en formato JSON con la información de Time: intDay, intHours, intMinutes
    public static String getTimeArrayJSON(){
        //Time(int intDay, int intHours, int intMinutes)
        String day = String.valueOf(intDay);
        String hours = String.valueOf(intHours);
        String minutes = String.valueOf(intMinutes);

        String timeJSON;
        timeJSON = String.format("[%s,%s,%s]", day, hours, minutes);
        return timeJSON;
    }

    // Retorna un String formado por day, hours y minutes
    public static String getTime() {
        return day+" "+hours+":"+minutes;
    }


    //Setters
    static void setIntDay(int intDay) {
        Time.intDay = intDay;
    }

    static void setIntHours(int intHours) {
        Time.intHours = intHours;
    }

    static void setIntMinutes(int intMinutes) {
        Time.intMinutes = intMinutes;
    }


    //Metodos

    // Thread
    public static void startReloj(){
        thread = new Thread((() -> {
            Thread ct= Thread.currentThread();
            while (ct== thread){
                try{
                    /*
                     ------------ Aproximadamente -------------
                     ------ Cada 2 minutos avanza un día ------
                     ---- Cada 5 segundos avanza una hora -----
                     - Cada 83 milisegundos avanza un minuto --
                     */

                    addMinute(); //Avanza un minuto en el reloj


                    Thread.sleep(83); //Pausa de 83Milisegundos
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }));
        thread.start();
    }



    //Hace avanzar un minuto en el reloj
    private static void addMinute(){
        intMinutes = Integer.parseInt(minutes);
        intHours = Integer.parseInt(hours);

        intMinutes++;

        //Cambio de hora
        if (intMinutes ==60){
            intMinutes = 0;
            intHours++;
        }

        //Cambio de día
        if (intHours ==24){
            intHours =0;
            intDay++;
            if (intDay ==8){
                intDay =1;}
        }

        //Actualiza las variables str<day,hours,minutes> con respecto a int<intDay, intHours, intMinutes>
        updateDay();
        updateHours();
        updateMinutes();
    }

    // Actualiza el String day con respecto a intDays
    private static void updateDay(){
        ArrayList<String> weekDays = new ArrayList<>();
        weekDays.add("Lunes");
        weekDays.add("Martes");
        weekDays.add("Miercoles");
        weekDays.add("Jueves");
        weekDays.add("Viernes");
        weekDays.add("Sabado");
        weekDays.add("Domingo");

        day = weekDays.get(intDay-1);
    }

    // Actualiza el String hours con respecto a intHours
    private static void updateHours(){
        hours = intHours >9?""+ intHours :"0"+ intHours;
    }

    // Actualiza el String minutes con respecto a intMinutes
    private static void updateMinutes(){
        minutes = intMinutes >9?""+ intMinutes :"0"+ intMinutes;
    }
}