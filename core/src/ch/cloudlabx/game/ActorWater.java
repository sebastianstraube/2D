package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;


public class ActorWater extends ActorBase {
    
    public int fillRate;
    public int rainDropsNr=0;

    public ActorWater(float width, float height) {
        super(width, height, 0);
        this.center.x = width / 2;
        this.center.y = height / 2;

    }

    public ActorWater(float posx, float posy, float width, float height) {
        this(width, height);
        pos.x = posx;
        pos.y = posy;
    }

    public ActorWater(float x, float y, float sideLength) {
        this(x, y, sideLength, sideLength);
    }

    public void influenceRainCollision(){
        //influence behaviour
        //System.out.println(rainDropsNr++);

        if(maxVel > 0 || maxAcc > 0) {
            maxVel -= (0.001);
            maxVel -= (0.001);
            density += (0.001);
        }
        
        //System.out.println(maxVel);
    }

    public void draw(Batch batch, float parentAlpha, Vector2 mousePosition) {
        this.draw(batch, parentAlpha);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        
    }

}
