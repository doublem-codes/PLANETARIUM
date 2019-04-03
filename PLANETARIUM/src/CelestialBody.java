public class CelestialBody {
    private int numMoon = 0;
    private int massa;
    private String name;
    private Id idCel;
    private Point positionAbs ;

    public CelestialBody(String name, int massa, int numMoon , float x, float y, Id idCel){
        this.name = name;
        this.massa = massa;
        this.numMoon = numMoon;
        this.idCel = idCel;
        this.positionAbs = new Point (x , y );
    }

    public int getNumMoon() {
        return numMoon;
    }

    public void setNumMoon(Integer numMoon) {
        this.numMoon = numMoon;
    }

    public Id getIdCEl() {
        return idCel;
    }

    public void setIdCEL(Id idCel) {
        this.idCel = idCel;
    }

    public Point getPositionAbs() {
        return positionAbs;
    }

    public void setPositionAbs(Point positionAbs) {
        this.positionAbs = positionAbs;
    }

    public int getMassa() {
        return massa;
    }

    public void setMassa(int massa) {
        this.massa = massa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
