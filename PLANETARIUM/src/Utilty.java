import java.lang.System;
import java.util.ArrayList;

public class Utilty {
    // MENU message
    private String msgA = "[A]: Add a celestian body";
    private String msgD = "[D]: Delete a celestian body";
    private String msgE = "[E]: End process";
    private String msgF = "[F]: Find a celestian body";
    private String msgI = "[I]: Info about a celestian body";
    private String msgC = "[C]: Calculate system mass middle";
    private String msgR = "[R]: Calculate road";
    //ADD message
    private String msgAM = "[M]: Add a moon";
    private String msgAP = "[P]: Add a planet";
    //DELETE message
    private String msgDM = "[M]: Delete a moon";
    private String msgDS = "[S]: Delete a star";
    private String msgDP = "[P]: Delete a planet";
    //Default message
    private String msg1 = "Select one of the following method";
    private String msg2 = "Insert ... ";
    //State Initialize
    private State state = State.NULL;
    private TypeOfCelestianBody typeOfCelestianBody = TypeOfCelestianBody.NULL;
    private TypeOfCelestianBody typeOfCelestianBody1 = TypeOfCelestianBody.NULL;
    private TypeOfCelestianBody typeOfCelestianBody2 = TypeOfCelestianBody.NULL;

    private boolean bool = true;

    SystemStar systemStar = new SystemStar();


    public boolean menu(){//metod print menù - setting state of program
        System.out.println(msg1 + "\n" + msgA + "\n" + msgD + "\n" + msgF + "\n" + msgI + "\n" + msgC + "\n"+ msgR +"\n" + msgE);
        boolean endProcess = false;

        switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n"+ msg2).toUpperCase()){
            case "A":
                System.out.println(msg1);
                if(bool){
                    String msgAS = "[S]: Add a star";
                    System.out.print("\n" + msgAS);
                }
                System.out.print( "\n" + msgAP +"\n" +msgAM);
                switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n" + msg2).toUpperCase()) {
                    case "S":
                        state = State.ADDSTAR;
                        bool = false;
                        break;
                    case "P":
                        state = State.ADDPLANET;
                        break;
                    case "M":
                        state = State.ADDMOON;
                        break;
                }
                break;

            case "D":
                System.out.println(msg1 + "\n" + msgDS + "\n" + msgDP + "\n" + msgDM);
                switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n" + msg2).toUpperCase()) {
                    case "S":
                        state = State.DELETESTAR;
                        break;
                    case "P":
                        state = State.DELETEPLANET;
                        break;
                    case "M":
                        state = State.DELETEMOON;
                        break;
                }
                break;

            case "F":
                state = State.FIND;
                break;

            case "I":
                state = State.INFO;
                break;

            case "E":
                System.out.println("End process");
                endProcess = true;
                break;

            case "C":
                state=State.CALCULATEMIDDLE;
                break;

            case "R":
                state=State.CALCULATEROAD;
                break;
            default:
                state = State.NULL;
                break;
        }
        return endProcess;
    }

    public void process(){

        switch (state) {
            case ADDSTAR:
                systemStar.addCelestialBody( "Star", TypeOfCelestianBody.STAR);
                break;

            case ADDPLANET:
                systemStar.addCelestialBody( "Planet",TypeOfCelestianBody.PLANET);
                break;

            case ADDMOON:
                systemStar.addCelestialBody("Moon",TypeOfCelestianBody.MOON);
                break;

            case DELETEPLANET:
                if(! systemStar.deleteCelestianBody("planet",TypeOfCelestianBody.PLANET)){
                    System.out.println("Planet not found");
                }
                break;

            case DELETEMOON:
                if(!systemStar.deleteCelestianBody("moon",TypeOfCelestianBody.MOON)){
                    System.out.println("Moon not found");
                }
                break;

            case FIND:
                System.out.println("Select a type of celestial body you want to find:" + "\n" + "[P]: planet"+ "\n" + "[M]: moon");
                String strType1 = "";
                switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n" + msg2).toUpperCase()) {
                    case "P":
                        typeOfCelestianBody=TypeOfCelestianBody.PLANET;
                        strType1="planet";
                        break;
                    case "M":
                        typeOfCelestianBody=TypeOfCelestianBody.MOON;
                        strType1="moon";
                        break;
                }

                if(! systemStar.findCelestialBody(strType1,typeOfCelestianBody)){
                    System.out.println(strType1 + "not found");
                }
                break;

            case INFO:
                System.out.println("Select a type of celestial body you want to print the info about:" + "\n" + "[P]: planet"+ "\n" + "[M]: moon");
                String strType2 = "";
                switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n" + msg2).toUpperCase()) {
                    case "P":
                        typeOfCelestianBody = TypeOfCelestianBody.PLANET;
                        strType2="planet";
                        break;
                    case "M":
                        typeOfCelestianBody = TypeOfCelestianBody.MOON;
                        strType2="moon";
                        break;
                }

                if(! systemStar.printInfoBody(strType2,typeOfCelestianBody)) {
                    System.out.println(strType2 + "not found");
                }
                break;

            case CALCULATEMIDDLE:
                systemStar.setMiddle(systemStar.calculateMiddle());
                System.out.println( "centre is");
                System.out.println( "x: " + systemStar.getMiddle().getX());
                System.out.println( "y: " + systemStar.getMiddle().getY() + "\n");
                break;

            case CALCULATEROAD:
                System.out.println("Select a type of celestial body you want star:" + "\n" + "[P]: planet"+ "\n" + "[M]: moon");
                String strType4 = "";
                switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n" + msg2).toUpperCase()) {
                    case "P":
                        strType4="planet";
                        break;
                    case "M":
                        strType4="moon";
                        break;
                }
                System.out.println("Select a type of celestial body you want to move:" + "\n" + "[P]: planet"+ "\n" + "[M]: moon");
                String strType3 = "";
                switch (it.unibs.fp.mylib.InputDati.leggiStringa("\n" + msg2).toUpperCase()) {
                    case "P":
                        strType3="planet";
                        break;
                    case "M":
                        strType3="moon";
                        break;
                }
                systemStar.calculateRoad(strType4,strType3);
                break;
            default:
                System.out.println("NULL");
                break;
        }
    }
}
