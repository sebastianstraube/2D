package ch.cloudlabx.game;

import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Game extends ApplicationAdapter {
	
	
	//assets
	public static Sound dropSound;
	public static Music rainMusic;

	private Batch batch;
	private ActorBucket bucket;
	private ActorBall circle;
	private Vector3 mouse_position;
	//private GameStage gameStage;
	private OrthographicCamera cam;
	private ArrayList<ActorRainDrop> raindrops;
	private float rainIntensity;
	//Animation<TextureRegion> animation;

	private void spawnRaindrop() {
		float dropSize = MathUtils.random(6,64);
		ActorRainDrop raindrop = new ActorRainDrop(MathUtils.random(0, Gdx.app.getGraphics().getWidth()-64), Gdx.app.getGraphics().getHeight(), dropSize, dropSize);
		raindrops.add(raindrop);
	 }

	@Override
	public void create () {

		//animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("throbber.gif").read());

		//raindrops list
		raindrops = new ArrayList<ActorRainDrop>();
		rainIntensity = 10;
		//render batch
		batch = new SpriteBatch();

		//TODO implemet stages
		//gameStage = new GameStage();
		
		//input
		mouse_position = new Vector3(0,0,0);

		//actor
		bucket = new ActorBucket(64,64);

		//ball actor
		circle = new ActorBall(64);

		//cam
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//cam.position.x = Gdx.graphics.getWidth()/2; 
		//cam.position.y = Gdx.graphics.getHeight()/2;

		//Asset Loading
		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
  
		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	@Override
	public void render () {
		Gdx.graphics.setTitle(String.valueOf(Gdx.graphics.getFramesPerSecond()));
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mouse_position);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		//draw actor
		bucket.draw(batch, 1f, new Vector2(mouse_position.x, mouse_position.y));
		circle.draw(batch, 1f, new Vector2(mouse_position.x, mouse_position.y));
		//draw Rain
		if(MathUtils.random(0, 100) <= MathUtils.random(0, rainIntensity)) {
			spawnRaindrop();
		}

		for (Iterator<ActorRainDrop> iter = raindrops.iterator(); iter.hasNext(); ) {

			ActorRainDrop raindrop = iter.next();
			raindrop.draw(batch, 0.5f, new Vector2(mouse_position.x, mouse_position.y));
			boolean isCollisionActor = ActorBase.isCollisionActor(bucket, raindrop);
			boolean isCollisionScreen = raindrop.isCollisionScreen();

			if(isCollisionScreen || isCollisionActor) {
				if(isCollisionActor) {
					dropSound.play();
					bucket.influenceRainCollision(raindrop.mass);
				}
				iter.remove();
			}
		}

		batch.end();		
	}
	
	@Override
	public void dispose () {
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}


}
