package ch.cloudlabx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ActorPhysics {
    
    public static void initPhysics(ActorBase actor){
        actor.volume = actor.sizeBody.x*actor.sizeBody.y;
        actor.mass = actor.density * actor.volume;
        //actor.density = actor.mass / actor.volume; we look for density and volume is given
        actor.weight = actor.mass * actor.gravityConstant; // weight + direction = force vector http://hyperphysics.phy-astr.gsu.edu/hbase/mass.html#wgt
    }

    public static void applyPhysicsAttraction(ActorBase actor, Vector2 attractorPosition){
        Vector2 distance = attractorPosition.cpy().sub(actor.pos).scl(0.005f);
        applyPhysicsForce(actor, distance);
    }

    // pointing down like gravity = new Vector2(0,-1);
    public static void applyPhysicsGravityWorld(ActorBase actor){
        //TODO check because (0,1) should point down: https://github.com/libgdx/libgdx/wiki/Coordinate-systems 
        Vector2 directionUnitVector = new Vector2(0,-1); //pointing down
        applyPhysicsGravity(actor, directionUnitVector);
    }

    // pointing down like gravity = new Vector2(0,-1);
    public static void applyPhysicsGravityRandom(ActorBase actor){
        Vector2 direction = new Vector2(MathUtils.random(-1,1), MathUtils.random(-1,1));
        applyPhysicsGravity(actor, direction);
    }

    public static void applyPhysicsGravity(ActorBase actor, Vector2 gravityDirection){
        /*
        //this.gravityConstant  = 9.81f; // m/s2
        //this.sizeBody = new Vector2(width, height);
        //this.mass = Constants.PHYSIC_MATERIAL_MASS_DEFAULT; // body weight in grams
        actor.volume = actor.sizeBody.x*actor.sizeBody.y;
        actor.mass = actor.density * actor.volume;
        //actor.density = actor.mass / actor.volume; we look for density and volume is given
        actor.weight = actor.mass * actor.gravityConstant; // weight + direction = force vector http://hyperphysics.phy-astr.gsu.edu/hbase/mass.html#wgt
        */
        actor.force = gravityDirection.cpy().nor().scl(actor.weight); //unit vector in direction, multiplied scalar of weight
        applyPhysicsForce(actor, actor.force); // pointing down like gravity
    }

    public static void applyPhysicsForce(ActorBase actor, Vector2 force){
        actor.acc.add(force); // accelerate depending how strong the force is
        //actor.acc.limit(actor.maxAcc);
        actor.vel.add(actor.acc);
        //actor.vel.limit(actor.maxVel);
        actor.pos.add(actor.vel);
        actor.acc.scl(0f);
    }
    
}
