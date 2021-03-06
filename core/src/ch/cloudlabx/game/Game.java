package ch.cloudlabx.game;

import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Game extends ApplicationAdapter {
	
	public static Batch batch;
	public static ShapeRenderer shapeRenderer;
	
	//assets
	public static Sound dropSound;
	public static Sound dropSoundBucket;
	public static Music rainMusic;

	private ActorBucket bucket;
	private ActorCircle circle;
	private ActorCircle circleR;
	private ActorWater water;
	private Vector3 mouse_position;
	//private GameStage gameStage;
	private OrthographicCamera cam;
	private ArrayList<ActorRainDrop> raindrops;
	//Animation<TextureRegion> animation;

	private void spawnRaindrop() {
		float maxDropSize = Constants.ENV_RAINDROP_SIZE_MAX;
		float minDropSize = maxDropSize/Constants.ENV_RAINDROP_SIZE_VARIATION;
		float dropSize = MathUtils.random(minDropSize,maxDropSize);
		ActorRainDrop raindrop = new ActorRainDrop(Constants.PHYSIC_DRAG_SHAPE_STREAMLINED, Constants.PHYSIC_MATERIAL_DENSITY_WATER, dropSize, dropSize);
		raindrop.setPosition(MathUtils.random(0, Gdx.app.getGraphics().getWidth()-dropSize), Gdx.app.getGraphics().getHeight()-dropSize);
		raindrops.add(raindrop);
	 }

	@Override
	public void create () {

		//Camera
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//cam.position.x = Gdx.graphics.getWidth()/2; 
		//cam.position.y = Gdx.graphics.getHeight()/2;
		
		//render batch
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		//animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("throbber.gif").read());

		//raindrops list
		raindrops = new ArrayList<ActorRainDrop>();

		//TODO implemet stages
		//gameStage = new GameStage();
		
		//input
		mouse_position = new Vector3(0,0,0);

		//actor
		bucket = new ActorBucket(Constants.PHYSIC_DRAG_SHAPE_RECTANGLE, Constants.PHYSIC_MATERIAL_DENSITY_METAL, 32,32);

		//circle gravitational actor
		circle = new ActorCircle(Constants.PHYSIC_DRAG_SHAPE_CIRCLE, Constants.PHYSIC_MATERIAL_DENSITY_AIR, 32);
		circle.setPosition(250, 350);

		
		circleR = new ActorCircle(Constants.PHYSIC_DRAG_SHAPE_CIRCLE, Constants.PHYSIC_MATERIAL_DENSITY_WOOD, 32);
		circleR.setPosition(500, 350);

		//actor water 
		water = new ActorWater(Constants.PHYSIC_DRAG_SHAPE_CIRCLE, Constants.PHYSIC_MATERIAL_DENSITY_WATER, 800, 100);
		water.setPosition(0, 0);

		//Asset Loading
		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop2.wav"));
		dropSoundBucket = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
  
		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	@Override
	public void render () {
		Gdx.graphics.setTitle(String.valueOf(Gdx.graphics.getFramesPerSecond()));
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mouse_position);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		//Actor Circle
		circle.draw(batch, 1f);
		circle.setPosition(mouse_position.x, mouse_position.y);

		//bucket actor
		bucket.draw(batch, 1f, new Vector2(mouse_position.x, mouse_position.y));
		bucket.setPosition(mouse_position.x, mouse_position.y);
		ActorPhysics.addGravityAttraction(bucket, circle);

		//if(Constants.DEBUG) circle.draw(batch, 1f, new Vector2(mouse_position.x, mouse_position.y));

		//Rain
		if(MathUtils.random(100 / Constants.ENV_RAINDROP_INTENSITY) < Constants.ENV_RAINDROP_INTENSITY){
			spawnRaindrop();
		}

		//Actor Water
		water.draw(batch, 1f);

		for (Iterator<ActorRainDrop> iter = raindrops.iterator(); iter.hasNext(); ) {

			ActorRainDrop raindrop = iter.next();			
			ActorPhysics.addGravityWorld(raindrop);
			raindrop.draw(batch, 0.5f);
			DebugHelper.drawLineBetweenActor(batch, shapeRenderer, Color.GREEN, bucket, raindrop);

			boolean isCollisionActor = ActorPhysics.isCollisionActor(batch, bucket, raindrop);
			boolean isCollisionScreen = ActorPhysics.isCollisionScreen(raindrop);
			
			if(isCollisionScreen || isCollisionActor) {
				if(isCollisionActor) {
					bucket.influenceRainCollision(raindrop);
					dropSoundBucket.play();
				}
				
				//TODO find a better sound
				//if(isCollisionScreen) dropSound.play();
				iter.remove();
			}
		}

		//Draw over all graphics
		DebugHelper.drawActorHelper(batch, shapeRenderer, bucket);

		batch.end();		
	}
	
	@Override
	public void dispose () {
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}


}
