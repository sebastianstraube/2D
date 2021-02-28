package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ActorCircle extends ActorBase {

    private float radius;

    public ActorCircle(float shapeType, float density, float radius) {
        super(shapeType, density, radius, radius);
        this.radius = radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        Gdx.gl.glEnable(GL30.GL_ARRAY_BUFFER_BINDING);
        Gdx.gl.glLineWidth(2);
        Game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.shapeRenderer.setColor(Color.MAGENTA);
        Game.shapeRenderer.circle(posCenter.x, posCenter.y, radius);
        Game.shapeRenderer.end();
        Gdx.gl.glLineWidth(1);

        batch.begin();
    }

}
