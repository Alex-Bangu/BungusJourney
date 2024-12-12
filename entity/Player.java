package entity;

import BungusJourney.GamePanel;
import BungusJourney.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler KeyH;

    public Player(GamePanel gp, KeyHandler KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        this.setDefaultValues();
        this.getPlayerImage();

    }

    public void setDefaultValues(){
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        this.direction = "down";
    }

    public void getPlayerImage(){
        try{
            this.up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            this.up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            this.left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            this.left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            this.right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            this.right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if(KeyH.upPressed == true || KeyH.downPressed == true || KeyH.leftPressed == true || KeyH.rightPressed == true){
            spriteCounter++;
        }

        if(KeyH.upPressed == true) {
             this.direction = "up";
            this.y -= this.speed;
        }if(KeyH.downPressed == true) {
            this.direction = "down";
            this.y += this.speed;
        }if(KeyH.leftPressed == true) {
            this.direction = "left";
            this.x -= this.speed;
        }if(KeyH.rightPressed == true) {
            this.direction = "right";
            this.x += this.speed;
        }

        spriteCounter++;

        if(this.spriteCounter >= 40){
            if(this.spriteNumber == 1){
                this.spriteNumber = 2;
            }else if(this.spriteNumber == 2){
                this.spriteNumber = 1;
            }
            this.spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.white);
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(this.spriteNumber == 1) {
                    image = up1;
                }else if(this.spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(this.spriteNumber == 1) {
                    image = down1;
                }else if(this.spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(this.spriteNumber == 1) {
                    image = left1;
                }else if(this.spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(this.spriteNumber == 1) {
                    image = right1;
                }else if(this.spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}