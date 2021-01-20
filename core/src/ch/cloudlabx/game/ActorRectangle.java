package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ActorRectangle extends Actor {

    float width, height;
    boolean debug;
    private ShapeRenderer shapeRenderer;
    
    float maxAcc, maxVel;
    Vector2 pos = new Vector2();
	Vector2 vel = new Vector2();
	Vector2 acc = new Vector2();
	Vector2 speed = new Vector2();
    
	boolean KEY_DOWN_UP = false;
	boolean KEY_DOWN_DOWN = false;
	boolean KEY_DOWN_LEFT = false;
    boolean KEY_DOWN_RIGHT = false;
    
    public ActorRectangle() {
        this.width = 10;
        this.height = 10;
        shapeRenderer = new ShapeRenderer();
        debug = false;
        maxAcc = 1f;
        maxVel = 1f;
    }

    public ActorRectangle(float x , float y , float width, float height) {
        this();
        this.width = width;
        this.height = height;
    }

    public ActorRectangle(float x , float y , float sideLength) {
        this(x , y , sideLength, sideLength);
    }   
    
    public void inputInit(){

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override public boolean keyDown (int keycode) {
				switch (keycode) {
					case Input.Keys.W: KEY_DOWN_UP=true; break;
					case Input.Keys.S: KEY_DOWN_DOWN=true; break;
					case Input.Keys.A: KEY_DOWN_LEFT=true; break;
					case Input.Keys.D: KEY_DOWN_RIGHT=true; break;
					default: return false;
				}
				return true;
			}

			@Override public boolean keyUp (int keycode) {
				switch (keycode) {
					case Input.Keys.W: KEY_DOWN_UP=false; break;
					case Input.Keys.S: KEY_DOWN_DOWN=false; break;
					case Input.Keys.A: KEY_DOWN_LEFT=false; break;
					case Input.Keys.D: KEY_DOWN_RIGHT=false; break;
					default: return false;
				}
				return true;
			}
		});

    }
        
    private void drawLine(Vector2 v1, Vector2 v2, Color c){
        Gdx.gl.glLineWidth(2);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(c);
        shapeRenderer.line(new Vector3(v1,0), new Vector3(v2,0));
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    public void draw(Batch batch, float parentAlpha, Vector2 mousePosition){
        
        Vector2 mouse_dst= mousePosition.cpy();
        mouse_dst.sub(pos);
        //mouse_dst.nor();
        acc = mouse_dst.cpy(); // accelerate depending how far the mouse is away from the object
        acc.limit(maxAcc);
        vel.add(acc);
        vel.limit(maxVel);
        pos.add(vel);


        drawLine(pos, mousePosition, Color.BLUE);
        drawLine(new Vector2(10,0), new Vector2(10, mouse_dst.len()*10), Color.WHITE);
        drawLine(new Vector2(20,0), new Vector2(20, acc.len()*10), Color.WHITE);
        drawLine(new Vector2(30,0), new Vector2(30, vel.len()*10), Color.WHITE);

        System.out.println(mouse_dst.len() + " : " + acc.len() + " : " + vel.len());
        
        this.draw(batch, parentAlpha);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        
		if(KEY_DOWN_UP) acc.add(new Vector2(0,maxAcc));
		if(KEY_DOWN_DOWN) acc.sub(new Vector2(0,maxAcc));
		if(KEY_DOWN_LEFT) acc.add(new Vector2(-maxAcc,0));
        if(KEY_DOWN_RIGHT) acc.sub(new Vector2(-maxAcc,0));

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(pos.x, pos.y, width, height);
        shapeRenderer.end();
    }

}
