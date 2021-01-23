package ch.cloudlabx.game;

public class Constants{

    public static int WIDTH=100;
    public static int HEIGHT=100;
    public static int UNIT=1;
    public static boolean DEBUG = true;
    public static String TEXTURE_RAINDROP = "drop";
    public static String TEXTURE_BUCKET = "bucket";

    // PHYSIC
    public static float PHYSIC_DEFAULT_MAX_GRAVITY = 9.81f;
    public static float PHYSIC_DEFAULT_MAX_VELOCITY = 10000f; // m/s2
    public static float PHYSIC_DEFAULT_MAX_ACCELERATION = 10000f;
    public static float PHYSIC_DEFAULT_DENSITY = 1f;

    public static float PHYSIC_BUCKET_MAX_ACCELERATION = 1f;
    public static float PHYSIC_MATERIAL_DENSITY_METAL = 1f;

    public static float PHYSIC_RAINDROP_MAX_ACCELERATION = 1f;
    public static float PHYSIC_MATERIAL_DENSITY_WATER = 0.1f;


}
