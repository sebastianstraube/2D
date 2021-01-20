package ch.cloudlabx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {
	Batch batch;
	ActorRectangle actor;
	Vector3 mouse_position;
	
	public GameStage gameStage;
	
	OrthographicCamera cam;
	Rectangle rect1 = new Rectangle(50, 50, 100, 100);
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		//stages
		gameStage = new GameStage();
		
		//input
		mouse_position = new Vector3(0,0,0);

		//actor
		actor = new ActorRectangle();

		//cam
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.x = Gdx.graphics.getWidth()/2; 
		cam.position.y = Gdx.graphics.getHeight()/2;
	}

	

	@Override
	public void render () {
		Gdx.graphics.setTitle(String.valueOf(Gdx.graphics.getFramesPerSecond()));
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mouse_position);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		//draw
		batch.begin();

		actor.draw(batch, 1f, new Vector2(mouse_position.x, mouse_position.y));

		//actor.draw(batch, 0.5f);

		batch.end();		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
