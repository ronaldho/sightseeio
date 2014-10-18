 public abstract class Enemy extends Character
   {
   private static boolean collisionDetected;
   protected int spawnSide;
   private String name;
   
   /**
    * Constructor used for Kammy Koopa only
    */
   
   public Enemy()
      {
      collisionDetected = false;
      spawnSide = ( int ) ( Math.random() * 2 );
      
      } //end Enemy()
      
   /**
    * @param normal  represents a normal enemy, aka, one who is not invincible
    */   
      
   public Enemy( boolean normal )
      {
      spawnSide = ( int ) ( Math.random() * 2 );   
         
      
         if( spawnSide == 0 )
            {
            this.setX( ( int ) Map.getWorldBounds().getMinX() - 20 );
            this.setDirection( Character.facingRight() );
            } //end if
         
         else
            if( spawnSide == 1 )
               {
               this.setX( ( int ) Map.getWorldBounds().getMaxX() + 20 );
               this.setDirection( Character.facingLeft() );
               } //end else if
      } //end Enemy
   
   /*
    * returns a random enemy to draw to the screen. This excludes invincible
    * characters that like to have their own way :)
    */
   
   /**
    * used to retrieve which random enemy should be drawn to the screen at a specified time
    * 
    * @param characterNumber  a random integers that will indicate which enemy to draw to the screen
    */
   public static Enemy getEnemy( int characterNumber ) 
      {
      if( characterNumber == 0 )
         {
         return new Paratroopa( 56 );
         } //end if
      
      else
         if( characterNumber == 1 )
            {
            return new DonkeyKong( 48 );
            } //end else
         
      
      else
         {
         return new Ghost( 40 ); 
         } //end if
      } //end getEnemy()
      
   public static void setCollision( boolean c )
      {
      collisionDetected = c;
      } //end setCollision()
   
   public static boolean hasCollided()
      {
      return collisionDetected;
      } //end hasCollided()
      
   /**
    * A method to detect if an enemy has collided with the character
    * 
    * @param character a character object that represents the playing character
    */
      
   public boolean enemyCollision( Character character )
      {
      if( this.getObjectRectangle().intersects( character.getObjectRectangle() ) )
         {
         setCollision( true );
         return hasCollided();
         } //end if
         
      else
         {
         return false;
         } //end if
      } //end enemyCollision()
      
   /**
    * A method used to detect if a character's bullet has collided with the enemy
    * 
    * @param bullet  the bullet object's rectangle will be used to determine if there is a collision
    * 
    * @param character  the playing character used to fulfill who the bullet belongs to
    */
      
   public boolean enemyCollision( Bullet bullet, PlayableCharacter character )
      {
      if( this.getObjectRectangle().intersects( bullet.getObjectRectangle( character ) ) )
         {
         return true;
         } //end if
         
      else
         {
         return false;
         } //end else
      } //end enemyCollision()
      
   /**
    * enemy's version of move
    */
      
   public void move()
      {
      if( this.getDirection() == Character.facingRight() )
         {
         this.setX( getX() + 5 );   
         } //end if
         
      else
         if( this.getDirection() == Character.facingLeft() )
            {
            this.setX( getX() - 5 );
            } //end else if
         
      if( this.getObjectRectangle().intersectsLine( Map.getWorldBounds().getMinX(),
                                                    Map.getWorldBounds().getMaxY(),
                                                    Map.getWorldBounds().getMinX(),
                                                    Map.getWorldBounds().getMinY() ) )
         {
         this.setX( this.getX() + 5 );
         this.setDirection( Character.facingRight() );
         } //end if
         
      else
         if( this.getObjectRectangle().intersectsLine( Map.getWorldBounds().getMaxX(),
                                                       Map.getWorldBounds().getMaxY(),
                                                       Map.getWorldBounds().getMaxX(),
                                                       Map.getWorldBounds().getMinY() ) )
            {
            this.setX( this.getX() - 5 );
            this.setDirection( Character.facingLeft() );
            } //end else if
      } //end move()
      
   public String toString()
      {
      return name;
      } //end toString()
      
   /**
    * @return String that represents the character's name
    */
      
   abstract public String getName();
   } //end Enemy