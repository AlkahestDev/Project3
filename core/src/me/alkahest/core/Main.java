package me.alkahest.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.alkahest.guns.Weapon;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Weapon.Gun exampleGun;
	@Override
	public void create () {
		exampleGun = new Weapon.Gun(0,0);
		exampleGun.autoGenerateParts(5);
		System.out.println(exampleGun);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
