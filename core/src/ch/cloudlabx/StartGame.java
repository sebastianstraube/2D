package ch.cloudlabx;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


import static com.badlogic.gdx.Application.LOG_DEBUG;

public class StartGame extends Game {

    @Override
    public void create() {
        Gdx.app.setLogLevel(LOG_DEBUG);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getScreen().render(Gdx.graphics.getDeltaTime());
    }


}