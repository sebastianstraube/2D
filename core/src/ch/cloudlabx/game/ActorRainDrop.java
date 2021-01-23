package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class ActorRainDrop extends ActorBase {
    
    public float raindropSpeed = 100;
    private Texture texture;
    
    public ActorRainDrop(float width, float height) {
        super(width, height, width/10);
        this.center.x = width / 2;
        this.center.y = height / 2;
    }

    public ActorRainDrop(float posx, float posy, float width, float height) {
        this(width, height);
        this.texture = getTextureScaled(Constants.TEXTURE_RAINDROP, (int)width, (int)height);
        pos.x = posx;
        pos.y = posy;
    }

    public ActorRainDrop(float x, float y, float sideLength) {
        this(x, y, sideLength, sideLength);
    }

    public void draw(Batch batch, float parentAlpha, Vector2 mousePosition) {
        this.draw(batch, parentAlpha);
        isCollisionScreen();
        //calculatePhysicsAttraction(mousePosition);
        applyPhysicsGravityWorld();
        //drawDebugLine(mousePosition);;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, pos.x, pos.y);
    }

}
