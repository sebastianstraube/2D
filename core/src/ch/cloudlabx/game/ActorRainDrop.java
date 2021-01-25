package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ActorRainDrop extends ActorBase {
    
    private Texture texture;
    
    public ActorRainDrop(float shapeType, float density, float width, float height) {
        super(shapeType, density, width, height);
        this.texture = getTextureScaled(Constants.TEXTURE_RAINDROP, (int)width, (int)height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.enableBlending();
        batch.draw(texture, posCenter.x-(sizeBody.x/2), posCenter.y-(sizeBody.y/2));
    }

}
