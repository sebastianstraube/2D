package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ActorCircle extends ActorBase {

    Color color = Color.MAGENTA;
    private float radius;

    public ActorCircle(float density, float radius) {
        super(density, radius*2, radius*2);
        this.radius = radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
        Gdx.gl.glLineWidth(2);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.color);
        shapeRenderer.circle(pos.x, pos.y, radius);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);

        batch.begin();
    }

}
