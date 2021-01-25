package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ActorRectangle extends ActorBase {

    public Vector2 posStart;

    public ActorRectangle(float shapeType, float density) {
        super(shapeType, density);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        Gdx.gl.glEnable(GL30.GL_ARRAY_BUFFER_BINDING);
        Gdx.gl.glLineWidth(2);
        Game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.shapeRenderer.setColor(Color.BLUE);
        Game.shapeRenderer.rect(posStart.x, posStart.y, sizeBody.x, sizeBody.y);
        Game.shapeRenderer.end();
        Gdx.gl.glLineWidth(1);

        batch.begin();
    }

}
