import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)


/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kolling
 * @version 1.1
 */
public class Space extends World
{
    private Counter scoreCounter;
    private Counter levelCounter;
    private Counter protonCounter;
    private Counter asteroidSpawnCounter;
    private int startAsteroids = 1;
    

    public Space() 
    {
        super(600, 400, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(300);
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        addAsteroids(startAsteroids);
        
        protonCounter = new Counter("Proton: ");
        addObject(protonCounter, 550, 20);
        
        levelCounter = new Counter("Nivå: ");
        levelCounter.add(1);
        addObject(levelCounter, 60, 20);
        
        scoreCounter = new Counter("Poäng: ");
        addObject(scoreCounter, 60, 380);
        
        asteroidSpawnCounter = new Counter("Asteroid_Spawn_Time: ");
        addObject(asteroidSpawnCounter, 550, 380);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
    }
    
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }

    /**
     * Crete a given number of stars in space.
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for(int i=0; i < number; i++) 
        {
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 120 - Greenfoot.getRandomNumber(100);
             background.setColor(new Color(color,color,color));
             background.fillOval(x, y, 2, 2);
        }
    }
    
    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        Greenfoot.playSound("Defeat.mp3");
        addObject(new ScoreBoard(scoreCounter.getValue()), getWidth()/2, getHeight()/2);
    }
    
    public void countScore (int amount){
        scoreCounter.add(amount);
    }
    
    public void levelCounter (){
        if( getObjects(Asteroid.class).size() < 2){
            levelCounter.add(1);
            addAsteroids(levelCounter.getValue() + startAsteroids);
            Greenfoot.playSound("Victory.mp3");
        }
        
    }
    
    public void activationTrackers(int pValue, int aValue ){
        protonCounter.add(pValue-protonCounter.getValue());
        asteroidSpawnCounter.add(aValue- asteroidSpawnCounter.getValue());
    }
    
    public void asteroidSpawn (){
        addAsteroids(1);
    }
    

}