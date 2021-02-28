package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;


public class ActorBucket extends ActorBase {
    
    public int fillRate;
    public int rainDropsNr;
    private Texture texture;

    public ActorBucket(float shapeType, float density, float width, float height){
        super(shapeType, density, width, height);
        this.texture = ActorHelper.getTextureScaled(Constants.TEXTURE_BUCKET, (int)width, (int)height);
    }

    //influence behaviour
    public void influenceRainCollision(ActorBase a){
        this.density += a.density; //should be mass but density * volume is the starting point of physic calculation
        System.out.println(this.density);
    }

    public void draw(Batch batch, float parentAlpha, Vector2 mousePosition) {
        draw(batch, parentAlpha);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, posCenter.x-(sizeBody.x/2), posCenter.y-(sizeBody.y/2));
    }

}
