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
        this.mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
        this.getTileImage();
        this.loadMap("/maps/map0.txt");
    }

    public void getTileImage(){
        try{
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            this.tiles[1] = new Tile();
            this.tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            this.tiles[2] = new Tile();
            this.tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            this.tiles[3] = new Tile();
            this.tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            this.tiles[4] = new Tile();
            this.tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            this.tiles[5] = new Tile();
            this.tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW){
                String line = br.readLine();
                while(col < gp.MAX_WORLD_COL){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.MAX_WORLD_COL){
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
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
            int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

            if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
                    worldX - gp.TILE_SIZE< gp.player.worldX + gp.player.SCREEN_X &&
                    worldY + gp.TILE_SIZE> gp.player.worldY - gp.player.SCREEN_Y &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y){

                g2d.drawImage(this.tiles[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
            worldCol++;

            if(worldCol == gp.MAX_WORLD_COL){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
