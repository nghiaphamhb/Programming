public interface IPersonRun {
    public void run(Coordinate coord, int steps);
    public void stop();
    public void chase(Person person);
}