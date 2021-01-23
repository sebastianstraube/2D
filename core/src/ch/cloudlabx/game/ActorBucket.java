package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;


public class ActorBucket extends ActorBase {
    
    public int fillRate;
    public int rainDropsNr=0;
    private Texture texture;

    public ActorBucket(float width, float height){
        super(width, height, 1f);
        this.texture = getTextureScaled(Constants.TEXTURE_BUCKET, (int)width, (int)height);
        
        this.center.x = width / 2;
        this.center.y = height / 2;
        this.maxAcc = 1f;
        this.maxVel = 15f;
        this.fillRate = 0;
        this.mass = 1f;
    }

    public ActorBucket(float posx, float posy, float width, float height) {
        this(width, height);
        pos.x = posx;
        pos.y = posy;
    }

    public ActorBucket(float x, float y, float sideLength) {
        this(x, y, sideLength, sideLength);
    }

    public void influenceRainCollision(float mass){
        //influence behaviour
        if(this.maxVel > 0.001) this.maxVel -= mass*0.01f;
        if(this.maxAcc > 0.001) this.maxAcc -= mass*0.01f;
        this.mass += mass*0.01f;
        
        //System.out.println(maxVel);
    }

    public void draw(Batch batch, float parentAlpha, Vector2 mousePosition) {
        draw(batch, parentAlpha);
        isCollisionScreen();
        applyPhysicsAttraction(mousePosition);
        applyPhysicsGravityWorld();
        drawDebugLine(mousePosition);;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, pos.x, pos.y);
    }

}
