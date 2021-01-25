package ch.cloudlabx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ActorPhysics {
    
    //https://www.grc.nasa.gov/www/k-12/VirtualAero/BottleRocket/airplane/shaped.html
    //https://www.grc.nasa.gov/www/k-12/airplane/sized.html
    //Drag = DragCoefficient(Cd) * density * ((velocity*velocity)/2) * (attack surface)
    //  float density, float bodyAttackSurface, float velocity
    //  return Cd * density * ((velocity*velocity)/2) * bodyAttackSurface;
    //acceleration = (Weight - Drag)/Mass ??
    public static void addDrag(ActorBase actor){
        Vector2 dragVector = actor.vel.cpy().scl(-0.5f);
        dragVector.setLength(actor.dragCd * (actor.vel.len() * actor.vel.len()));
        actor.vel.add(dragVector);
    }

    public static void addGravityAttraction(ActorBase actor, ActorBase attractor){
        addGravity(actor, attractor.posCenter.sub(actor.posCenter));
    }

    public static void addGravityAttraction(ActorBase actor, Vector2 attractorPosition){
        addGravity(actor, attractorPosition.sub(actor.posCenter));
    }

    // pointing down like gravity = new Vector2(0,-1);
    public static void addGravityWorld(ActorBase actor){
        addGravity(actor, new Vector2(0,-1));//pointing down
    }

    // pointing down like gravity = new Vector2(0,-1);
    public static void addGravityRandom(ActorBase actor){
        addGravity(actor, new Vector2(MathUtils.random(-1,1), MathUtils.random(-1,1)));
    }

    /*
    //this.gravityConstant  = 9.81f; // m/s2
    //this.sizeBody = new Vector2(width, height);
    //this.mass = Constants.PHYSIC_MATERIAL_MASS_DEFAULT; // body weight in grams
    actor.volume = actor.sizeBody.x*actor.sizeBody.y;
    actor.mass = actor.density * actor.volume;
    //actor.density = actor.mass / actor.volume; we look for density and volume is given
    actor.weight = actor.mass * actor.gravityConstant; // weight + direction = force vector http://hyperphysics.phy-astr.gsu.edu/hbase/mass.html#wgt
    */
    public static void addGravity(ActorBase actor, Vector2 force){
        actor.force = force.cpy().setLength(actor.weight);
        applyPhysicsForce(actor, actor.force);
    }

    public static void addEnvironment(ActorBase actor, float dragCoefficient){
        addDrag(actor);
    }


    public static void applyPhysicsForce(ActorBase actor, Vector2 force){
        actor.volume = actor.sizeBody.x*actor.sizeBody.y;
        actor.mass = actor.density * actor.volume;
        //actor.density = actor.mass / actor.volume; we look for mass volume can be calculated and density is a constant
        actor.weight = actor.mass * actor.gravityConstant; // weight + direction = force vector http://hyperphysics.phy-astr.gsu.edu/hbase/mass.html#wgt
        actor.acc.add(force); // accelerate depending how strong the force is
        actor.vel.add(actor.acc);
        
        actor.posCenter.add(actor.vel);
        //System.out.println(actor + ":\tdrag: "+actor.drag + "\tvel: "+ actor.vel.len());// +"\tforceNegativeDrag: "+ actor.acc.cpy().add(forceNegativeDrag).len());
        actor.acc.scl(0f);
    }
    
}
