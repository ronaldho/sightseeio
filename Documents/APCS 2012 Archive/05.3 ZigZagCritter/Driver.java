 import info.gridworld.actor.ActorWorld;
 
 public class Driver
    {
    public static void main( String[] args )
       {
       ActorWorld world = new ActorWorld();
       
       world.add( new ZigZagCritter() );
       world.show();
       } //end main()
    } //end Driver