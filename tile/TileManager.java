package tile;

import BungusJourney.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];
        this.mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
        this.getTileImage();
        this.loadMap();
    }

    public void getTileImage(){
        try{
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            this.tiles[1] = new Tile();
            this.tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            this.tiles[2] = new Tile();
            this.tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map0.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW){
                String line = br.readLine();
                while(col < gp.MAX_SCREEN_COL){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.MAX_SCREEN_COL){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW){

            int tileNum = mapTileNum[col][row];

            g2d.drawImage(this.tiles[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if(col == gp.MAX_SCREEN_COL){
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
