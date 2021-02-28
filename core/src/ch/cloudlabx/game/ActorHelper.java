package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ActorHelper {
    
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
}
