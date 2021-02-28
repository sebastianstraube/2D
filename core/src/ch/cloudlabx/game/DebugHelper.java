package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class DebugHelper {

    private static float DEBUG_ALPHA = 0.5f;

    public static void drawLine(Batch batch, ShapeRenderer shapeRenderer, Color c, Vector2 v1, Vector2 v2){

        batch.end();

        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(c.r, c.g, c.b, DEBUG_ALPHA));
        shapeRenderer.line(v1.x, v1.y, v2.x, v2.y);
        shapeRenderer.end();

        batch.begin();

        
    }

    public static void drawRectangle(Batch batch, ShapeRenderer shapeRenderer, Color c, float x, float y, float width, float height){

        batch.end();

        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(c.r, c.g, c.b, DEBUG_ALPHA));
        shapeRenderer.rect(x,y,width,height);
        shapeRenderer.end();

        batch.begin();

        
    }

    public static void drawActorHelper(Batch batch, ShapeRenderer shapeRenderer, ActorBase a){
        float height = 250f;
        drawLine(batch, shapeRenderer, Color.WHITE, new Vector2(10, height), new Vector2(10, height+a.acc.len()*100));
        drawLine(batch, shapeRenderer, Color.GREEN, new Vector2(20, height), new Vector2(20, height+a.vel.len()*100));
        drawLine(batch, shapeRenderer, Color.RED, new Vector2(30, height), new Vector2(30, height+a.density));
        drawLine(batch, shapeRenderer, Color.MAGENTA, new Vector2(40, height), new Vector2(40, height+a.weight));
        System.out.println(
            "\tacc: " + a.acc.len() + 
            "\tvel: " + a.vel.len() + 
            "\tdensity: " + a.density + 
            "\tweight: " + a.weight
        );
    }

    public static void drawLineBetweenActor(Batch batch, ShapeRenderer shapeRenderer, Color c, ActorBase a, ActorBase b){
        drawLine(batch, shapeRenderer, c, b.posCenter, a.posCenter);
    }
    
}
