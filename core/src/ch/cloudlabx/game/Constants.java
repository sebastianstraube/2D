package ch.cloudlabx.game;

public class Constants{

    public static int WIDTH=100;
    public static int HEIGHT=100;
    public static int UNIT=1;
    public static boolean DEBUG = true;
    public static String TEXTURE_RAINDROP = "drop";
    public static String TEXTURE_BUCKET = "bucket";

    // PHYSIC
    public static float PHYSIC_SCALE = 0.1f;
    public static float PHYSIC_FORCE_GRAVITY_EARTH = 9.81f * PHYSIC_SCALE;

    //MASS in g
    

    //DENSITY in g/ccm
    public static float PHYSIC_MATERIAL_DENSITY_AIR = 0.001185f * PHYSIC_SCALE;
    public static float PHYSIC_MATERIAL_DENSITY_METAL = 8f * PHYSIC_SCALE; //STEEL
    public static float PHYSIC_MATERIAL_DENSITY_WATER = 1f * PHYSIC_SCALE;
    public static float PHYSIC_MATERIAL_DENSITY_BLACKHOLE = 200f * PHYSIC_SCALE; //public static float PHYSIC_MATERIAL_MASS_BLACKHOLE = Float.POSITIVE_INFINITY * PHYSIC_SCALE;
    public static float PHYSIC_MATERIAL_DENSITY_WOOD = 0.6f * PHYSIC_SCALE;
    
    //DRAG COEFFICIENT
    //https://www.engineeringtoolbox.com/drag-coefficient-d_627.html
    //https://www.simscale.com/blog/2016/07/how-dimples-affect-golf-ball/
    //https://www.grc.nasa.gov/www/k-12/airplane/socdrag.html
    //https://www.grc.nasa.gov/www/k-12/airplane/sized.html
    public static float PHYSIC_DRAG_SHAPE_STREAMLINED = 0.04f * PHYSIC_SCALE;
    public static float PHYSIC_DRAG_SHAPE_CIRCLE = 0.5f * PHYSIC_SCALE;
    public static float PHYSIC_DRAG_SHAPE_RECTANGLE = 2.1f * PHYSIC_SCALE;

    //ENVIRONMENT RAIN
    public static float ENV_RAINDROP_SIZE_MAX = 100 * PHYSIC_SCALE; // between 1 and 100
    public static int ENV_RAINDROP_SIZE_VARIATION = 10; // how much smaller is the smalles drop depending on ENV_RAINDROP_SIZE_MAX
    public static int ENV_RAINDROP_INTENSITY = 5; // between 0 and 10
       


}
