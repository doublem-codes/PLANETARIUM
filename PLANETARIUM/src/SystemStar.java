import it.unibs.fp.mylib.InputDati;
import java.util.ArrayList;

public class SystemStar {
    ArrayList<CelestialBody> arrayListCelestianBody = new ArrayList<>();
    Point middle = new Point(0, 0);
    private static int maxNumberplanet = 26000;
    private static int maxNumberMoon = 5000;

    public Point getMiddle() {
        return middle;
    }

    public void setMiddle(Point middle) {
        this.middle = middle;
    }

    private static int numPlanet = 0;
    //metod use to get a celestial body from arrayList
    public CelestialBody getCelestialBody(int index) {
        return arrayListCelestianBody.get(index);
    }

    //metod used to add element at arrayList
    public void addCelestialBody(String strType, TypeOfCelestianBody typeOfCelestianBody) {

        if (typeOfCelestianBody == TypeOfCelestianBody.PLANET && numPlanet > maxNumberplanet) {//check number max planet
            System.out.println("! ! ! ERROR reached maximum number of planets ! ! ! ");
            return;
        }

        Point pointPass = new Point(0, 0);
        boolean pos;
        String strName = InputDati.leggiStringa("insert the name of " + strType + ":"); //input name of celestialbody

        int weight = InputDati.leggiIntero("insert weight of " + strType + ":"); //input weight of celestialbody
        // make choose of insert position

        if (typeOfCelestianBody == TypeOfCelestianBody.STAR) {// control of position in case of type of celestyalbody
            System.out.println(" insert a absolute position of " + strType + ":");
            pos = false;
        } else {
            System.out.println(" [A]:insert a absolute position ");
            System.out.println(" [R]:insert a relative position ");
            switch (InputDati.leggiStringa("\nInsert ...\n").toUpperCase()) {
                case "A":
                    pos = false;
                    break;
                case "R":
                    pos = true;
                    break;
                default:
                    pos = false;
                    System.out.println("NUll");
            }
        }
        //input point of celestilbody
        pointPass.setY(InputDati.leggiIntero("Insert the x of " + strType + ":"));
        pointPass.setX(InputDati.leggiIntero("Insert the y of " + strType + ":"));

        switch (typeOfCelestianBody) {
            case STAR:
                Id idS = new Id(strName, typeOfCelestianBody); //generate ID
                CelestialBody cbS = new CelestialBody(strName, weight, 0, pointPass.getX(), pointPass.getY(), idS);
                arrayListCelestianBody.add(0, cbS);//add star at arrayList
                break;

            case PLANET:
                if (pos) { // convertion position if point is relative
                    float x = pointPass.getX() + getCelestialBody(0).getPositionAbs().getX();
                    float y = pointPass.getY() + getCelestialBody(0).getPositionAbs().getY();
                    pointPass.setY(y);
                    pointPass.setX(x);
                }
                Id idP = new Id(strName, typeOfCelestianBody);//generate ID
                CelestialBody cbP = new CelestialBody(strName, weight, 0, pointPass.getX(), pointPass.getY(), idP);
                arrayListCelestianBody.add(cbP);//add plane at arrayList
                numPlanet++; //increment number of planet
                break;

            case MOON:
                String strNamePlanet ;
                strNamePlanet = InputDati.leggiStringa("Insert the planet: "); // input name planet of planet
                int indexPlanet = findIndexCelestianBody(strNamePlanet);// extract index of planet
                if (getCelestialBody(indexPlanet).getNumMoon() > maxNumberMoon) {//check number max moon
                    System.out.println("! ! ! ERROR reached maximum number of moon for this planet ! ! ! ");
                    return;
                }
                int num = getCelestialBody(indexPlanet).getNumMoon() + 1;//change value of moon of planet current
                getCelestialBody(indexPlanet).setNumMoon(num);

                if (pos) { // convertion position if point is relative
                    float xm = pointPass.getX() + getCelestialBody(indexPlanet).getPositionAbs().getX();
                    float ym = pointPass.getY() + getCelestialBody(indexPlanet).getPositionAbs().getY();
                    pointPass.setX(xm);
                    pointPass.setY(ym);
                }
                Id idM = new Id(strName, typeOfCelestianBody);//generate ID
                CelestialBody cbM = new CelestialBody(strName, weight, 0, pointPass.getX(), pointPass.getY(), idM);
                arrayListCelestianBody.add(indexPlanet + 1, cbM);//add moon at arrayList
                break;
        }
    }

    //metod used to delete element at arrayList
    public boolean deleteCelestianBody(String strType, TypeOfCelestianBody typeOfCelestianBody) {
        String str2 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the" + strType + " name you want to delete: ");//name of element to remove
        arrayListCelestianBody.remove(findIndexCelestianBody(str2));// delete celstialbody
        switch (typeOfCelestianBody) {
            case STAR:
                break;
            case PLANET:
                --numPlanet;//decrement number of planet
                break;
            case MOON:
                String strNamePlanet = InputDati.leggiStringa("Insert the planet: ");//input name plane of moon
                int indexPlanet = findIndexCelestianBody(strNamePlanet);// extract index of planet
                int num = getCelestialBody(indexPlanet).getNumMoon() - 1;//change value of moon of planet current
                getCelestialBody(indexPlanet).setNumMoon(num);
                break;
        }
        return true;
    }

    //metod used to find element at arrayList
    public int findIndexCelestianBody(String strCelestianbody) {
        int i;
        for (i = 0; i < arrayListCelestianBody.size(); i++) {//used for scroll array to chek and find index of celestyalbody
            if (strCelestianbody.equals(getCelestialBody(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    //metod used to calculate the middle
    public Point calculateMiddle() {//calculate weighted average
        Point point = new Point(0, 0);
        float x = 0, y = 0;
        float sommaMasse = 0;
        for (int i = 0; i < arrayListCelestianBody.size(); i++) {
            sommaMasse += getCelestialBody(i).getMassa(); //sum weight of all celestyalbody
            x += (getCelestialBody(i).getMassa() * getCelestialBody(i).getPositionAbs().getX()); //partialx sum weight*position
            y += (getCelestialBody(i).getMassa() * getCelestialBody(i).getPositionAbs().getY());//partialy sum weight*position
        }
        point.setX(x / sommaMasse);//generate right point of middle centeer weigth
        point.setY(y / sommaMasse);
        return point;
    }

    //metod used to print element at arrayList
    public boolean printInfoBody(String strType, TypeOfCelestianBody typeOfCelestianBody) {

        switch (typeOfCelestianBody) {
            case PLANET:
                String str2;
                do {
                    str2 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the  " + strType + "name you want to print the info about");//input name
                }while (findIndexCelestianBody(str2) == -1 );

                if (findIndexCelestianBody(str2) != -1) { //if it doesn't found return -1
                    int indexCelestianBody = findIndexCelestianBody(str2); //find index of planet
                    int i;
                    boolean flag = false;
                    i = indexCelestianBody + 1;
                    do {
                        if (i == arrayListCelestianBody.size()) {// check index in bounds
                            break;
                        }
                        if (getCelestialBody(i).getIdCEl().getTypeOfCelestianBody() != typeOfCelestianBody) {
                            System.out.println(getCelestialBody(i).getName());// print all moon of planet
                        } else {
                            flag = true;
                        }
                        i++;
                    } while (!flag);
                    return true;
                }
                break;

            case MOON:
                String str3;
                do {
                    str3 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the  " + strType + "name you want to print the info about");//input name
                }while (findIndexCelestianBody(str3) == -1 );

                if (findIndexCelestianBody(str3) != -1) {//if it doesn't found return -1
                    int indexMoon = findIndexCelestianBody(str3);

                    int newindex = findIndexPlanet(indexMoon,typeOfCelestianBody);
                    System.out.println(getCelestialBody(0).getName() + "->" + getCelestialBody(newindex+1).getName() + "->"
                            + getCelestialBody(indexMoon).getName());
                    return true;
                }
                break;
        }
        return false;
    }

    //metod used to find element at arrayList
    public boolean findCelestialBody(String strType, TypeOfCelestianBody typeOfCelestianBody) {
        //metod used for find celestyalbody in arrayList
        switch (typeOfCelestianBody) {
            case PLANET:
                String str2;
                do {
                    str2 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the name of " + strType + "to move:");//input name
                }while (findIndexCelestianBody(str2) == -1 );
                if (findIndexCelestianBody(str2) != -1) {//if don't found return -1
                    System.out.println("planet found");
                    return true;
                }
                break;

            case MOON:
                String str3;
                do {
                    str3 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the name of " + strType + "to move:");//input name
                }while (findIndexCelestianBody(str3) == -1 );
                if (findIndexCelestianBody(str3) != -1) {//if don't found return -1
                    System.out.println("moon planet found");
                    boolean flag = false;
                    for (int i = findIndexCelestianBody(str3); flag; i--) {//check name of planet of moon
                        if (getCelestialBody(i).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.PLANET) {
                            System.out.println("The planet is:" + getCelestialBody(i).getName());
                            flag = true;
                        }
                    }
                    return true;
                }
                break;
        }
        return false;
    }

    //metod use to  calculateRoad
    public boolean calculateRoad(String strType1 ,String strType2 ){
        int state = 0;
        String str2;
        do {
            str2 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the name of " + strType1 + " to start:");//input name
        }while (findIndexCelestianBody(str2) == -1) ;
        int index1 = findIndexCelestianBody(str2);

        String str3;
        do {
            str3 = it.unibs.fp.mylib.InputDati.leggiStringa("Insert the name of " + strType2 + " to move:");//input name
        }while (findIndexCelestianBody(str3) == -1 );
        int index2 = findIndexCelestianBody(str3);
        //setting diffrent case of road
        if (getCelestialBody(index1).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.PLANET && getCelestialBody(index2).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.PLANET) {
            state = 1;
        } else if (getCelestialBody(index1).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.MOON && getCelestialBody(index2).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.MOON) {
            state = 2;
        } else if (getCelestialBody(index1).getIdCEl().getTypeOfCelestianBody()== TypeOfCelestianBody.PLANET && getCelestialBody(index2).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.MOON) {
            state = 3;
        } else if (getCelestialBody(index1).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.MOON && getCelestialBody(index2).getIdCEl().getTypeOfCelestianBody() == TypeOfCelestianBody.PLANET) {
            state = 4;
        }
        int indexPlanet1 ;
        int indexPlanet2 ;
        switch (state) {
            case 1:
                System.out.println(getCelestialBody(index1).getName() + "->" +
                        getCelestialBody(0).getName() + "->" +
                        getCelestialBody(index2).getName());
                double road1 = calculateRoadDistance(index1,0)+calculateRoadDistance(index2,0);
                System.out.print("total distance is " + road1);
                break;

            case 2:
                indexPlanet2 = findIndexPlanet(index2,getCelestialBody(index2).getIdCEl().getTypeOfCelestianBody());
                indexPlanet1 = findIndexPlanet(index1,getCelestialBody(index1).getIdCEl().getTypeOfCelestianBody());
                if (indexPlanet1 != indexPlanet2) {
                    double road2 = calculateRoadDistance(index1,indexPlanet1)+calculateRoadDistance(index2,indexPlanet2)+calculateRoadDistance(indexPlanet1,0)+
                            calculateRoadDistance(0,indexPlanet2);
                    System.out.println(getCelestialBody(index1).getName() + "->" +
                            getCelestialBody(indexPlanet1).getName() + "->" +
                            getCelestialBody(0).getName() + "->" +
                            getCelestialBody(indexPlanet2).getName() + "->" +
                            getCelestialBody(index2).getName());
                    System.out.println("total distance is " + road2);
                }else{
                    double road2 = calculateRoadDistance(index1,indexPlanet1)*2;
                    System.out.println(getCelestialBody(index1).getName() + "->" +
                            getCelestialBody(indexPlanet1).getName() + "->" +
                            getCelestialBody(index2).getName());
                    System.out.println("total distance is " + road2);
                }
                break;

            case 3:
                indexPlanet2 = findIndexPlanet(index2,getCelestialBody(index2).getIdCEl().getTypeOfCelestianBody());
                System.out.println(getCelestialBody(index1).getName() + "->" +
                        getCelestialBody(0).getName() + "->" +
                        getCelestialBody(indexPlanet2).getName() + "->" +
                        getCelestialBody(index2).getName());
                double road3 = calculateRoadDistance(index1,0)+calculateRoadDistance(0,index2)+calculateRoadDistance(index2,indexPlanet2);
                System.out.print("total distance is " + road3);
                break;

            case 4:
                indexPlanet1 = findIndexPlanet(index1,getCelestialBody(index1).getIdCEl().getTypeOfCelestianBody());
                System.out.println(getCelestialBody(index1).getName() + "->" +
                        getCelestialBody(indexPlanet1).getName() + "->" +
                        getCelestialBody(0).getName() + "->" +
                        getCelestialBody(index2).getName());
                double road4 = calculateRoadDistance(index1,0)+calculateRoadDistance(0,index2)+calculateRoadDistance(index1,indexPlanet1);
                System.out.println("total distance is " + road4);
                break;
        }
        return true;
    }

    //metod use to find index of planet
    private int findIndexPlanet(int index,TypeOfCelestianBody typeOfCelestianBody){
        boolean flag = false;
        int i = index - 1;
        do {
            if (i == 0) {// check index in bounds
                break;
            }
            TypeOfCelestianBody c = getCelestialBody(i).getIdCEl().getTypeOfCelestianBody();
            if (c != typeOfCelestianBody) {
                flag = true;
            }
            i--;
        } while (!flag);
        return i;
    }

    //metod use to calculate the distance between 2 celestialbody
    private double calculateRoadDistance(int index1,int index2){
        double a2 = Math.pow(getCelestialBody(index2).getPositionAbs().getX()-getCelestialBody(index1).getPositionAbs().getX(), 2);
        double b2 = Math.pow(getCelestialBody(index2).getPositionAbs().getY()-getCelestialBody(index1).getPositionAbs().getY(), 2);
        return Math.sqrt( a2 + b2);
    }
}