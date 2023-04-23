package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
   private Game game;
   private Handler handler;
   private HUD hud;
   private Random r = new Random();
   

    public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
    
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 225, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player((Game.WIDTH/2)-32, (Game.HEIGHT/2)-32, ID.Player, handler));	
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			
			}
			//help button
			if(mouseOver(mx, my, 225, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			
			//quit button
			if(mouseOver(mx, my, 225, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
		
		//back button for help
				if(game.gameState == STATE.Help){		
			       if(mouseOver(mx, my,225, 350, 200, 64)){
					   game.gameState = STATE.Menu;	
					   return;
				  }
				}
				if(game.gameState == STATE.End){		
				       if(mouseOver(mx, my,225, 350, 200, 64)){
						   game.gameState = STATE.Game;	
						   hud.setLevel(1);
						   hud.setScore(0);
						   handler.addObject(new Player((Game.WIDTH/2)-32, (Game.HEIGHT/2)-32, ID.Player, handler));	
						   handler.clearEnemies();
						   handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						
						
					  }
					}		
				

	}
	public void mouseReleased(MouseEvent e) {
	
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height ) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height ) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	public void render (Graphics g) {
		if(game.gameState == STATE.Menu) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("WAVE", 250, 100);	
		
		g.setFont(fnt2);
		g.drawRect(225, 150, 200, 64);
		g.drawString("Play", 295, 190);

		g.drawRect(225, 250, 200, 64);
		g.drawString("Help", 295, 290);

		g.drawRect(225, 350, 200, 64);
		g.drawString("Quit", 295, 390);
		
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HELP", 255, 100);
			
			g.setFont(fnt2);
			g.drawString("Use WASD keys to move around", 100, 200);
			g.setFont(fnt2);
			g.drawString("and dodge enemies", 190, 250);
			
			g.setFont(fnt2);
			g.drawRect(225, 350, 200, 64);
			g.drawString("Back", 290, 390);
			
		}else if(game.gameState == STATE.End) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Game Over", 195, 100);
		
		g.setFont(fnt2);
		g.drawString("You lost with a score of: ", 165, 200);
		g.setFont(fnt2);
		g.drawString("" + hud.getScore(), 300, 250);
		
		g.setFont(fnt2);
		g.drawRect(225, 350, 200, 64);
		g.drawString("Try Again", 260, 390);
	}
		
		
		
	}
}
