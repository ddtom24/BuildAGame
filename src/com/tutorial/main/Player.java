package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 37);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 60);
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.magenta, 32,32, 0.04f, handler));
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy) {
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=2;
				}
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d =  (Graphics2D) g;

		g.setColor(Color.magenta);
		g2d.draw(getBounds());
		
		if(id == ID.Player) g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
		
//		else if(id == ID.Player2) g.setColor(Color.blue);
		
	}
    
	
	
}
