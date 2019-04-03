
import java.lang.System;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Utilty utility = new Utilty();
        while( !utility.menu() ) {//ciclo while infinite until end command
            utility.process();
        }
    }
}
