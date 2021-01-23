package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputAdapter;
    // TODO implement keyboard input
    /*
     * private boolean KEY_DOWN_UP = false; private boolean KEY_DOWN_DOWN = false;
     * private boolean KEY_DOWN_LEFT = false; private boolean KEY_DOWN_RIGHT =
     * false;
     */

public abstract class ActorBase extends Actor {
    
    public Vector2 pos = new Vector2();
    public Vector2 size = new Vector2();
    protected float maxAcc, maxVel;
    protected Vector2 center = new Vector2();
    protected Vector2 vel = new Vector2();
    protected Vector2 acc = new Vector2();
    protected float mass;
    protected static ShapeRenderer shapeRenderer;

    private ActorBase(){
        shapeRenderer = new ShapeRenderer();
        this.maxAcc = 2f;
        this.maxVel = 30f;
    }

    public ActorBase(float width, float height, float mass){
        this();
        this.mass = mass;
        setWidth(width);
        setHeight(height);
    }

    public ActorBase(float radius){
        this();
        setWidth(radius*2);
        setHeight(radius*2);
    }

    public static Texture getTextureScaled(String filename, int resizeX, int resizeY){
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal(filename+".png"));
        Pixmap pixmap100 = new Pixmap(resizeX, resizeY, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        Texture texture = new Texture(pixmap100);
        pixmap200.dispose();
        pixmap100.dispose();

        return texture;
    }

    /*
     * public void initInput(){
     * 
     * if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 *
     * Gdx.graphics.getDeltaTime(); if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
     * bucket.x += 200 * Gdx.graphics.getDeltaTime();
     * 
     * Gdx.input.setInputProcessor(new InputAdapter() {
     * 
     * @Override public boolean keyDown (int keycode) { switch (keycode) { case
     * Input.Keys.W: KEY_DOWN_UP=true; break; case Input.Keys.S: KEY_DOWN_DOWN=true;
     * break; case Input.Keys.A: KEY_DOWN_LEFT=true; break; case Input.Keys.D:
     * KEY_DOWN_RIGHT=true; break; default: return false; } return true; }
     * 
     * @Override public boolean keyUp (int keycode) { switch (keycode) { case
     * Input.Keys.W: KEY_DOWN_UP=false; break; case Input.Keys.S:
     * KEY_DOWN_DOWN=false; break; case Input.Keys.A: KEY_DOWN_LEFT=false; break;
     * case Input.Keys.D: KEY_DOWN_RIGHT=false; break; default: return false; }
     * return true; }
     * 
     * 
     * if(KEY_DOWN_UP) acc.add(new Vector2(0,maxAcc)); if(KEY_DOWN_DOWN) acc.sub(new
     * Vector2(0,maxAcc)); if(KEY_DOWN_LEFT) acc.add(new Vector2(-maxAcc,0));
     * if(KEY_DOWN_RIGHT) acc.sub(new Vector2(-maxAcc,0));
     * 
     * });
     * 
     * }
     */

    public static void drawLine(Vector2 v1, Vector2 v2, Color c) {
        Gdx.gl.glLineWidth(2);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(c);
        shapeRenderer.line(new Vector3(v1, 0), new Vector3(v2, 0));
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    public static boolean isCollisionActor(ActorBase actorA, ActorBase actorB) {
        Vector2 dist = actorB.pos.cpy().sub(actorA.pos);
        if (Constants.DEBUG){
            drawLine(actorA.pos, actorB.pos, Color.WHITE);
            drawLine(actorA.pos, actorB.pos, Color.GREEN);
        }
        
        return dist.len() <= (actorA.getWidth());
    }

    public boolean isCollisionScreen(){
        boolean isCollisionScreen = false;
        if (pos.x < 0) {
            pos.x = 0;
            isCollisionScreen = true;
        }
        if (pos.x > Gdx.app.getGraphics().getWidth() - center.x) {
            pos.x = Gdx.app.getGraphics().getWidth() - center.x;
            isCollisionScreen = true;
        }
        if (pos.y < 0) {
            pos.y = 0;
            isCollisionScreen = true;
        }
        if (pos.y > Gdx.app.getGraphics().getHeight() - center.y) {
            pos.y = Gdx.app.getGraphics().getHeight() - center.y;
        }

        return isCollisionScreen;
    }

    public void applyPhysicsAttraction(Vector2 attractorPosition){
        Vector2 distance = attractorPosition.cpy().sub(pos).nor();
        //distance.nor();
        acc = distance.cpy(); // accelerate depending how far the attractorPosition is away from the object
        acc.limit(maxAcc);
        vel.add(acc);
        vel.limit(maxVel);
        pos.add(vel);
    }

    
    public void applyPhysicsGravityWorld(){
        Vector2 gravity = new Vector2(0, 0.1f);
        Vector2 force = gravity.scl(mass).cpy();
        applyPhysicsForce(force); // pointing down like gravity
    }


    // pointing down like gravity = new Vector2(0,-1);
    public void applyPhysicsGravityRandom(){
        Vector2 gravity = new Vector2(0, MathUtils.random(0,100)/65);
        gravity.scl(mass);
        applyPhysicsForce(gravity);
    }

    // pointing down like gravity = new Vector2(0,-1);
    public void applyPhysicsForce(Vector2 force){
        acc.sub(force); // accelerate depending how strong the force is
        acc.limit(maxAcc);
        vel.add(acc);
        vel.limit(maxVel);
        pos.add(vel);
        acc.scl(0f);
    }

    public void drawDebugLine(Vector2 mousePosition){
        if (Constants.DEBUG) {
            drawLine(pos, mousePosition, Color.BLUE);
            drawLine(new Vector2(10, 0), new Vector2(10, mousePosition.cpy().sub(pos).len() * 10), Color.WHITE);
            drawLine(new Vector2(20, 0), new Vector2(20, acc.len() * 10), Color.WHITE);
            drawLine(new Vector2(30, 0), new Vector2(30, vel.len() * 10), Color.WHITE);
            // System.out.println(mouse_dst.len() + " : " + acc.len() + " : " + vel.len());
        }
    }

    @Override
    public abstract void draw(Batch batch, float parentAlpha);

}
