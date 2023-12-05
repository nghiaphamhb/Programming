public class ZeroGravityDevice{
    private SttDevice state;
    public ZeroGravityDevice(){
        state =SttDevice.OFF;
    }
    public void turnOn(Person person ){
        state = SttDevice.ON;
        state.getDescription();
        person.setSpeed(0);
    }
    public void turnOff(Person person){
        state = SttDevice.OFF;
        state.getDescription();
        person.setSpeed(person.planet.getSpeed());
    }
    public SttDevice getState(){
        return state;
    }
}