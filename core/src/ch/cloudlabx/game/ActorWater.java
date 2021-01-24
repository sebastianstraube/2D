package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ActorWater extends ActorBase {
    
    public int fillRate;
    public int rainDropsNr=0;

    public ActorWater(float density, float width, float height) {
        super(density, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
        Gdx.gl.glLineWidth(2);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(100, 50, 20, 100);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);

        batch.begin();
    }

}
