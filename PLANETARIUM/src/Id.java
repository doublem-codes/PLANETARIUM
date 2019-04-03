public class Id {
    private String id;
    private TypeOfCelestianBody typeOfCelestianBody ;
    static int idNUmber =0;

    public Id(String strings , TypeOfCelestianBody types){
        idNUmber++;
        this.id = calculateId(strings);
        this.typeOfCelestianBody = types;

    }

    public String getId(){
        return id;
    }

    public TypeOfCelestianBody getTypeOfCelestianBody() {
        return typeOfCelestianBody;
    }

    public String calculateId(String strings ){
        int i=idNUmber;
        String app1;
        String stringTrim = strings.trim();//delete space from string
        String app = deleteVocal(stringTrim.toUpperCase());// deletevocal from string
        app1 = String.format("%d", i) + app.substring(0, 2);//create substring of 4 element
        return app1;
    }
//metod used for delete vocal from string s
    private static String deleteVocal(String s) {

        if (s.equals("")) return "";
        else if (s.charAt(0)=='A'||
                s.charAt(0)=='E'||
                s.charAt(0)=='I'||
                s.charAt(0)=='O'||
                s.charAt(0)=='U'
        ) return deleteVocal(s.substring(1));
        else return s.substring(0,1) + deleteVocal(s.substring(1));
    }

}
