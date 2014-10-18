 import info.gridworld.actor.Critter;
 import java.util.ArrayList;
 import info.gridworld.grid.*;
 
 public class ZigZagCritter extends Critter
    {
    private boolean goingDiagUp;  
    private boolean facingRight;
    
    public ZigZagCritter()
       {
       goingDiagUp = true;
       facingRight = true;
       } //end ZigZagCritter()
       
    public Location selectMoveLocation( ArrayList<Location> locs )
       {
       if( !this.getGrid().isValid( this.getLocation().getAdjacentLocation( Location.EAST ) ) )
          {
          facingRight = false;  
          } //end if
          
       else
          if( !this.getGrid().isValid( this.getLocation().getAdjacentLocation( Location.WEST ) ) )
             {
             facingRight = true;
             } //end else if
             
       if( !this.getGrid().isValid( this.getLocation().getAdjacentLocation( Location.NORTH ) ) &&
            goingDiagUp )
          {
          return this.getLocation().getAdjacentLocation( Location.SOUTH );
          } //end if
          
       else
          if( !this.getGrid().isValid( this.getLocation().getAdjacentLocation( Location.SOUTH ) )
              && !goingDiagUp )
             {
             return this.getLocation().getAdjacentLocation( Location.NORTH );
             } //end else if
          
       if( goingDiagUp && facingRight )
          {
          goingDiagUp = false;
          return this.getLocation().getAdjacentLocation( Location.NORTHEAST );  
          } //end else if
          
       else
          if( !goingDiagUp && facingRight )
             {
             goingDiagUp = true;
             return this.getLocation().getAdjacentLocation( Location.SOUTHEAST );
             } //end else if
             
       else
          if( goingDiagUp && !facingRight )
             {
             goingDiagUp = false;
             return this.getLocation().getAdjacentLocation( Location.NORTHWEST );
             } //end else if
             
       else
          if( !goingDiagUp && !facingRight )
             {
             goingDiagUp = true;
             return this.getLocation().getAdjacentLocation( Location.SOUTHWEST );
             } //end else
             
       else
          {
          return this.getLocation();
          } //end else
       } //end selectMoveLocations
    } //end ZigZagCritter