import java.util.Objects;

public class Person implements IPersonRun, IPerson, IPersonFly {
    protected String name;
    protected Profession profession;
    protected Coordinate coord;
    protected Planet planet;
    protected double speed;
    protected Rocket rocket;
    protected Status status;
    public Person (String name, Profession profession, Coordinate coord, Planet planet, Rocket rocket){
        this.name = name;
        this.profession = profession;
        this.coord = coord;
        this.planet = planet;
        this.speed = planet.getSpeed();
        this.status = Status.WEIGHTED;
        this.rocket = rocket;
    }
    public Person (String name, Profession profession, Planet planet, Rocket rocket){
        this.name = name;
        this.profession = profession;
        this.planet = planet;
        this.speed = planet.getSpeed();
        this.status = Status.WEIGHTED;
        this.rocket = rocket;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Profession getProfession(){
        return profession;
    }
    public void setProfession (Profession profession){
        this.profession = profession;
    }
    public double getAbscissa(){
        return  coord.getAbs();
    }
    public double getOrdinate(){
        return coord.getOrd();
    }
    public Planet getPlanet(){
        return planet;
    }
    public void setPlanet(Planet planet){
        this.planet = planet;
    }
    public String getRocket(){
        return rocket.getModel();
    }
    public double getSpeed(){
        return this.speed;
    }
    public void setRocket(Rocket rocket){
        this.rocket = rocket;
    }



    @Override
    public void jump() {
        System.out.printf("%s is jumping out of the rocket", name);
    }

    @Override
    public void notice() {
        System.out.printf("%s is seeing the fantastic jump", name);
    }

    @Override
    public void stretchOut() {
        System.out.printf("%s stretch out", name);
    }

    @Override
    public void soarUp() {
        System.out.printf("%s soar up", name);
    }

    @Override
    public void flyDown() {
        System.out.printf("%s fly down", name);
    }
    @Override
    public void run(Coordinate coord) {
        System.out.printf("%s is still running.\n",name);
        this.coord = coord;

    }

    @Override
    public void stop() {
        System.out.printf("%s stop.\n",name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return name.equals(person.name) &&
                profession == person.profession &&
                coord.equals(person.coord) &&
                planet.equals(person.planet) &&
                Double.compare(person.speed, speed) == 0 &&
                Objects.equals(rocket, person.rocket) &&
                status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profession, coord, planet, speed, rocket, status);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", profession=" + profession +
                ", abs=" + this.coord.getAbs() +
                ", ord=" + this.coord.getOrd() +
                ", planet=" + this.planet.getName() +
                ", speed=" + speed +
                ", rocket=" + this.rocket.getModel()+
                ", status=" + status +
                '}';
    }
}