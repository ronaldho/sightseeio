 import info.gridworld.actor.ActorWorld;
 import info.gridworld.actor.*;
 import info.gridworld.grid.Location;
 import java.util.ArrayList;
 
 public class BugRunner
   {
   public static void main( String[] args )
      {
      ActorWorld world = new ActorWorld();
      ArrayList< Location > locs = new ArrayList< Location >();
      
      locs.add( new Location( 5, 5 ) );
      locs.add( new Location( 5, 6 ) );
      locs.add( new Location( 5, 7 ) );
      locs.add( new Location( 4, 5 ) );
      locs.add( new Location( 4, 6 ) );
      locs.add( new Location( 4, 7 ) );
      locs.add( new Location( 1, 0 ) );
      locs.add( new Location( 5, 9 ) );
      
      world.add( locs.get( 6 ), new Character() );
      world.add( locs.get( 7 ), new Goal() );
      world.add( locs.get( 0 ), new Rock() );
      world.add( locs.get( 1 ), new Rock() );
      world.add( locs.get( 2 ), new Rock() );
      world.add( locs.get( 3 ), new Rock() );
      world.add( locs.get( 4 ), new Rock() );
      world.add( locs.get( 5 ), new Rock() );
      world.show();
      } //end main()
   } //end BugRunner