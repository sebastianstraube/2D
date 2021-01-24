package ch.cloudlabx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ActorRainDrop extends ActorBase {
    
    private Texture texture;
    
    public ActorRainDrop(float density, float width, float height) {
        super(density, width, height);
        this.texture = getTextureScaled(Constants.TEXTURE_RAINDROP, (int)width, (int)height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, pos.x, pos.y);
    }

}
