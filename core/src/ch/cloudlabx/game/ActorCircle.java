package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ActorCircle extends ActorBase {

    Color color = Color.MAGENTA;

    public ActorCircle(float radius) {
        super(radius);
        this.center.x = radius;
        this.center.y = radius;
    }

    public ActorCircle(float posx, float posy, float radius) {
        this(radius);
        pos.x = posx;
        pos.y = posy;
    }

    public void draw(Batch batch, float parentAlpha, Vector2 centerPosition) {
        pos.x = centerPosition.x;
        pos.y = centerPosition.y;
        this.draw(batch, parentAlpha);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
        Gdx.gl.glLineWidth(2);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(this.color);
        shapeRenderer.circle(pos.x, pos.y, center.x);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);

        batch.begin();
    }

}
