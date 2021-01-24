package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;


public class ActorBucket extends ActorBase {
    
    public int fillRate;
    public int rainDropsNr;
    private Texture texture;

    public ActorBucket(float density, float width, float height){
        super(density, width, height);
        this.texture = getTextureScaled(Constants.TEXTURE_BUCKET, (int)width, (int)height);
    }

    public ActorBucket(float density, float posx, float posy, float width, float height) {
        this(density, width, height);
        pos.x = posx;
        pos.y = posy;
    }

    //influence behaviour
    public void influenceRainCollision(float weight){
        this.weight += weight;
        //System.out.println(this.weight);
    }

    public void draw(Batch batch, float parentAlpha, Vector2 mousePosition) {
        draw(batch, parentAlpha);
        drawDebugLine(batch, pos, mousePosition);;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, pos.x, pos.y);
    }

}
