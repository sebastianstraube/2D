package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;



public abstract class ActorBase extends Actor {
    
    protected Vector2 posCenter;
    protected Vector2 vel;
    protected Vector2 acc;

    //constant
    protected float gravityConstant; // m/s2
    public float dragCd;
    //input
    protected Vector2 sizeBody; // size of the body in cm3 !! is not a vector body, just holds the size data
    
    // calculation
    protected float mass; // weight is dependent on gravity, needs to be calculated (in kg)
    protected float volume;
    protected float density; // amount of matter within a certain volume; density = mass(g) / volume(cm3)

    //Weight is the force the earth exerts on the body. Force of gravity is the force per unit mass exert by earth or gravitational field strength.
    protected float weight; //Weight(Newton) = Mass(Kg) * Acceleration(N/kg or gravity earth = 9.81 m/s2; moon = 1.6 m/s2; jupiter = 25 m/s2)
    protected Vector2 force; // Force(newtons) = mass(kg)*acceleration(m/s2)

    //protected float work = Force dot Distance
    /*
    The dot product is often used to find the work, W, performed by a force F (in Newtons,
    N) acting on an object, moving it a distance D in meters. That is, = = > ∙ ?. Note that
    work W is a scalar. Often, the force is applied at an angle to the direction that the object is
    moving. Furthermore, the force and distance are stated as scalar values and the angle  is
    given, so that when finding > ∙ ?, we often use the formula |F||D| cos theta. 
    */

    /**
     * The cross product is used to find the torque, denoted @ (the Greek letter tau), formed by
    the combined action of two vectors  and. We can think of a force  “pushing” against a vector, where 
    ’s foot acts as a pivot, much like the hinges of a door. In a broad sense, vectors  and 
    combine to form a “twisting” action at a point. This twisting is
    torque and is calculated by the cross product. 
     */

    public ActorBase(float density, float shapeType){
        force = new Vector2();
        vel = new Vector2();
        acc = new Vector2();

        dragCd = shapeType;
        this.gravityConstant = Constants.PHYSIC_FORCE_GRAVITY_EARTH; // m/s2
        this.density = density;
    }

    public ActorBase(float density, float shapeType, float width, float height){
        this(density, shapeType);
        this.setSize(width, height);
    }

    public void setSize(float width, float height){
        setWidth(width);
        setHeight(height);
        this.posCenter = new Vector2(width/2, height/2);
        this.sizeBody = new Vector2(width, height);
    }

    //position is in the center of the body
    @Override
    public void setPosition(float x, float y){
        super.setPosition(x,y);
        this.posCenter.x = x;
        this.posCenter.y = y;
    }

    public void setDensity(float d){
        this.density=d;
    }

    public void setDragCoefficient(float dragCoefficient){
        this.dragCd = dragCoefficient;
    }

    public static boolean isCollisionActor(Batch batch, ActorBase actorA, ActorBase actorB) {
        Vector2 dist = actorB.posCenter.cpy().sub(actorA.posCenter);
        return dist.len() <= (actorA.getWidth());
    }

    public boolean isCollisionScreen(){
        boolean isCollisionScreen = false;
        if (posCenter.x < 0) isCollisionScreen = true;
        if (posCenter.x > Gdx.app.getGraphics().getWidth()) isCollisionScreen = true;
        if (posCenter.y < 0) isCollisionScreen = true;
        if (posCenter.y > Gdx.app.getGraphics().getHeight()) isCollisionScreen = true;

        return isCollisionScreen;
    }

    @Override
    public String toString(){
        String s=
        super.toString()+
        "\tvel: "+ vel.len() +
        "\tacc: "+ acc.len()+
        "\tsize: "+sizeBody.x+":"+sizeBody.y+
        "\tdensity: " + density +
        "\tvolume:" + volume;
        return s;
    }

    @Override
    public abstract void draw(Batch batch, float parentAlpha);

    // TODO implement keyboard input
    //import com.badlogic.gdx.Input;
    //import com.badlogic.gdx.InputAdapter;
    /* private boolean KEY_DOWN_UP = false; private boolean KEY_DOWN_DOWN = false;
     * private boolean KEY_DOWN_LEFT = false; private boolean KEY_DOWN_RIGHT =
     * false;
     *
     *
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
}
