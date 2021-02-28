package ch.cloudlabx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameStage extends Stage {

    public GameStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();
    }
    
    public static PerspectiveCamera createPerspectiveCamera() {
        PerspectiveCamera camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(10f, 10f, 10f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far  = 300f;
        camera.update();

        return camera;
    }
}

