package mypackage;

// File I/O
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

// Processing
import processing.core.PApplet;
import processing.core.PImage;

// Collections
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main game sketch controlling all game states,
 * rendering, input handling, and game logic.
 */
public class Sketch extends PApplet {

    // Player character
    private Shennong shennong;

    // Current game stage
    private int stage = 0;

    // UI / background images
    private PImage menuBackground;
    private PImage backgroundMap;
    private PImage startpage;
    private PImage endpage;
    private PImage collisionMap;
    private PImage book;

    // Map size constants
    private final int MAP_WIDTH = 1200;
    private final int MAP_HEIGHT = 800;

    // Camera position
    private int cameraX;
    private int cameraY;

    // Game objects
    private ArrayList<Herb> herbs;
    private ArrayList<WildBoar> boars;
    private ArrayList<Snake> snakes;

    // Journal image after collecting herb
    private PImage currentJournalImage;

    // Book interaction state
    private int collectedCount = 0;
    private boolean showBookMessage = false;
    private boolean nearBook = false;

    // Book position
    private final int BOOK_X = 1040;
    private final int BOOK_Y = 41;

    // Global score
    public static int score;

    // Explored map grid
    private int[][] explored;

    // Score save flag
    private boolean scoreSaved = false;

    // Key states
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    // Damage effect timer
    private int damageTimer = 0;

    // Enemy freeze timer
    private int enemyFreezeTimer = 0;

    /**
     * Set window size.
     */
    public void settings() {
        size(400, 350);
    }

    /**
     * Initialize game assets and variables.
     */
    public void setup() {

        // Set FPS
        frameRate(60);

        // Text alignment
        textAlign(CENTER);
        textSize(30);

        // Create player
        shennong = new Shennong(this, 115, 650, "Shennong", 50, "image/Shennong-right.png");

        // Load background map
        backgroundMap = loadImage("image/Background.png");

        // Create explored grid
        explored = new int[12][8];

        // Load collision map
        collisionMap = loadImage("image/Collision.png");

        // Load UI screens
        startpage = loadImage("image/startpage.png");
        endpage = loadImage("image/endpage.png");

        // Initialize herb list
        herbs = new ArrayList<>();

        // Add medicinal herbs
        herbs.add(new MedicinalHerb(this,120,115,30,30,"Ginseng",20,"image/Ginseng.png"));
        herbs.add(new MedicinalHerb(this,190,365,30,30,"Ginger",20,"image/Ginger.png"));
        herbs.add(new MedicinalHerb(this,514,577,30,30,"Goji Berry",20,"image/Goji Berry.png"));
        herbs.add(new MedicinalHerb(this,532,393,30,30,"Lingzhi",20,"image/Lingzhi.png"));
        herbs.add(new MedicinalHerb(this,667,77,30,30,"Mint",20,"image/Mint.png"));

        // Add poisonous herbs
        herbs.add(new PoisonHerb(this,754,434,30,30,"Nightshade",20,"image/Nightshade.png"));
        herbs.add(new PoisonHerb(this,966,661,30,30,"Toxic Mushroom",20,"image/Toxic Mushroom.png"));
        herbs.add(new PoisonHerb(this,1017,312,30,30,"Poison Ivy",20,"image/Poison Ivy.png"));

        // No journal image initially
        currentJournalImage = null;

        // Initialize boars list
        boars = new ArrayList<>();

        // Add wild boars
        boars.add(new WildBoar(this,195,200,"Boar1","image/Wild Boar-right.png",0,0,150,250));
        boars.add(new WildBoar(this,460,570,"Boar2","image/Wild Boar-right.png",0,0,510,570));
        boars.add(new WildBoar(this,580,500,"Boar3","image/Wild Boar-right.png",520,620,0,0));
        boars.add(new WildBoar(this,656,170,"Boar4","image/Wild Boar-right.png",620,710,0,0));
        boars.add(new WildBoar(this,750,480,"Boar5","image/Wild Boar-right.png",690,800,0,0));
        boars.add(new WildBoar(this,1000,620,"Boar6","image/Wild Boar-right.png",940,1180,0,0));

        // Initialize snakes list
        snakes = new ArrayList<>();

        // Add snakes
        snakes.add(new Snake(this,1000,270,"snake1","image/Snake-right.png",971,1074,0,0));
        snakes.add(new Snake(this,967,353,"snake2","image/Snake-left.png",0,0,300,370));
        snakes.add(new Snake(this,967,391,"snake3","image/Snake-right.png",980,1060,0,0));

        // Load book image
        book = loadImage("image/Book.png");
    }

    /**
     * Main game loop controlling different stages.
     */
    public void draw() {

        // Clear background each frame
        background(220);

        // Menu stage
        if (stage == 0) {
            drawMenu();
        }
        // Story stage
        else if (stage == 1) {
            drawStory();
        }
        // Game stage
        else if (stage == 2) {
            drawGame();
        }
        // Journal stage
        else if (stage == 3) {
            drawJournalScreen();
        }
        // Ending stage
        else if (stage == 4) {
            drawEnding();
        }
        // Game over stage
        else if (stage == 5) {
            drawGameOver();
        }
    }

    /**
     * Draw menu screen.
     */
    public void drawMenu() {

        // Draw background
        image(startpage, 0, 0);

        // Title text
        fill(0);
        textSize(30);
        text("SHENNONG", 240, 150);

        textSize(23);
        text("Tasting", 240, 175);
        text("the Hundred Herbs", 240, 200);

        // Start hint
        textSize(15);
        text("Press ENTER to Start", 250, 280);
    }

    /**
     * Draw story screen.
     */
    public void drawStory() {

        // Background color
        background(170, 220, 170);

        // Title
        fill(0);
        textSize(25);
        text("The Story of Shennong", width / 2, 50);

        // Story text
        textSize(13);
        text("Long ago in ancient China,\n\n"
                + "Shennong wanted to help people cure diseases.\n\n"
                + "He travelled through forests and mountains\n"
                + "and tasted hundreds of herbs.\n\n"
                + "Some herbs were useful medicines,\n"
                + "while others were poisonous.\n\n"
                + "Your mission is to discover medicinal herbs\n"
                + "and complete the Herb Journal.",
                width / 2, 90);

        // Continue hint
        text("Press ENTER to Begin Your Quest", width / 2, 320);
    }

    /**
     * Draw main gameplay.
     */
    public void drawGame() {

        // Update explored grid
        int col = shennong.getX() / 100;
        int row = shennong.getY() / 100;
        explored[col][row] = 1;

        // Camera follows player
        cameraX = shennong.getX() - width / 2;
        cameraY = shennong.getY() - height / 2;

        // Clamp camera inside map
        cameraX = constrain(cameraX, 0, MAP_WIDTH - width);
        cameraY = constrain(cameraY, 0, MAP_HEIGHT - height);

        // Screen shake when damaged
        if (damageTimer > 0) {
            cameraX += (int) random(-3, 3);
            cameraY += (int) random(-3, 3);
        }

        // Draw background map
        image(backgroundMap, -cameraX, -cameraY);

        // Draw herbs
        for (Herb h : herbs) {
            image(h.image, h.getX() - cameraX, h.getY() - cameraY, 30, 30);
        }

        // Move player
        movePlayer();

        // Draw player
        image(shennong.getImage(),
                shennong.getX() - cameraX,
                shennong.getY() - cameraY,
                32, 32);

        // Draw UI box
        fill(255);
        rect(5, 5, 80, 25);

        // HP text
        fill(0);
        textAlign(LEFT);
        textSize(14);
        text("HP: " + shennong.getHP(), 10, 22);

        // Exploration progress
        text("Explored: " + getExploredCount() + "/96", 10, 45);

        // Collision checks
        checkHerbCollisions();
        checkBoarCollisions();
        checkSnakeCollisions();

        // Draw enemies
        drawBoars();
        drawSnakes();

        // Book collision check
        checkBookCollision();

        // Draw book
        image(book, BOOK_X - cameraX, BOOK_Y - cameraY, 100, 100);

        // Warning message
        if (showBookMessage) {
            fill(0, 180);
            rect(50, 120, 300, 80);
            fill(255);
            textAlign(CENTER);
            text("You need all 8 herbs\nbefore completing the Herbal Book!",
                    width / 2, 155);
        }

        // Game over check
        if (shennong.getHP() <= 0) {
            stage = 5;
        }

        // Mini map
        drawMiniMap();

        // Damage overlay
        if (damageTimer > 0) {
            fill(255, 0, 0, damageTimer * 5);
            rect(0, 0, width, height);
            damageTimer--;
        }

        // Freeze timer
        if (enemyFreezeTimer > 0) {
            enemyFreezeTimer--;
        }
    }
    
        /**
     * Draws all wild boars and updates them if not frozen.
     */
    public void drawBoars() {

        // Loop through all boars
        for (WildBoar b : boars) {

            // Only update movement if not frozen
            if (enemyFreezeTimer <= 0) {
                b.update();
            }

            // Draw boar with camera offset
            image(b.getImage(),
                    b.getX() - cameraX,
                    b.getY() - cameraY,
                    40, 40);
        }
    }

    /**
     * Draws all snakes and updates them if not frozen.
     */
    public void drawSnakes() {

        // Loop through all snakes
        for (Snake s : snakes) {

            // Only update movement if not frozen
            if (enemyFreezeTimer <= 0) {
                s.update();
            }

            // Draw snake with camera offset
            image(s.getImage(),
                    s.getX() - cameraX,
                    s.getY() - cameraY,
                    25, 25);
        }
    }

    /**
     * Handles keyboard press events.
     */
    public void keyPressed() {

        // Restart game from game over screen
        if (stage == 5 && keyCode == ENTER) {
            restartGame();
            return;
        }

        // Close book message popup
        if (showBookMessage) {
            if (keyCode == ENTER) {
                showBookMessage = false;
            }
            return;
        }

        // Move from menu/story stages
        if (keyCode == ENTER) {
            if (stage < 2) {
                stage++;
            }
        }

        // Movement input handling
        if (stage == 2) {

            if (keyCode == LEFT) {
                leftPressed = true;
            }

            if (keyCode == RIGHT) {
                rightPressed = true;
            }

            if (keyCode == UP) {
                upPressed = true;
            }

            if (keyCode == DOWN) {
                downPressed = true;
            }
        }

        // Journal screen return
        if (stage == 3) {

            if (keyCode == ENTER) {
                stage = 2;

                // Brief freeze after returning
                enemyFreezeTimer = 30;
            }

            return;
        }
    }

    /**
     * Handles keyboard release events.
     */
    public void keyReleased() {

        // Stop left movement
        if (keyCode == LEFT) {
            leftPressed = false;
        }

        // Stop right movement
        if (keyCode == RIGHT) {
            rightPressed = false;
        }

        // Stop up movement
        if (keyCode == UP) {
            upPressed = false;
        }

        // Stop down movement
        if (keyCode == DOWN) {
            downPressed = false;
        }
    }

    /**
     * Updates player movement based on input and collision.
     */
    public void movePlayer() {

        // Movement delta
        int dx = 0;
        int dy = 0;

        // Move left
        if (leftPressed) {
            dx -= 2;
            shennong.faceLeft();
        }

        // Move right
        if (rightPressed) {
            dx += 2;
            shennong.faceRight();
        }

        // Move up
        if (upPressed) {
            dy -= 2;
        }

        // Move down
        if (downPressed) {
            dy += 2;
        }

        // Check collision before moving
        if (canMoveTo(
                shennong.getX() + dx,
                shennong.getY() + dy)) {

            // Apply movement
            shennong.move(dx, dy);
        }

        // Play animation if moving
        if (dx != 0 || dy != 0) {
            shennong.animate();
        } else {
            // Idle frame if not moving
            shennong.idle();
        }
    }

    /**
     * Checks if player can move to a position.
     *
     * @param x target x
     * @param y target y
     * @return true if movement is valid
     */
    public boolean canMoveTo(int x, int y) {

        // Player hitbox width
        int w = 22;

        // Player hitbox height
        int h = 28;

        // Check all corners
        return isWalkable(x, y)
                && isWalkable(x + w, y)
                && isWalkable(x, y + h)
                && isWalkable(x + w, y + h);
    }

    /**
     * Checks if a pixel is walkable (no collision).
     *
     * @param px x position
     * @param py y position
     * @return true if tile is walkable
     */
    public boolean isWalkable(int px, int py) {

        // Check map boundary X
        if (px < 0 || px >= MAP_WIDTH) {
            return false;
        }

        // Check map boundary Y
        if (py < 0 || py >= MAP_HEIGHT) {
            return false;
        }

        // Get collision pixel
        int c = collisionMap.get(px, py);

        // Transparent = walkable
        return alpha(c) == 0;
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {

        // Start Processing sketch
        PApplet.main("mypackage.Sketch");

        // Line counter (unused)
        int line = 0;

        try {

            // Read attempt file
            Scanner fileInput = new Scanner(new File("attempt.txt"));

            // Process each line
            while (fileInput.hasNext()) {

                // Read line
                String output = fileInput.nextLine();

                // Split data
                String[] info = output.split(",");

                // Extract name
                String name = info[0].trim();

                // Extract attempt value
                String attempt = info[1].trim();

                // Count lines
                line++;
            }

            // Close file
            fileInput.close();

        } catch (IOException e) {

            // Error message
            System.err.println("Error");
        }
    }

    /**
     * Checks collision between player and herbs.
     */
    public void checkHerbCollisions() {

        // Loop backwards to safely remove items
        for (int i = herbs.size() - 1; i >= 0; i--) {

            // Get herb
            Herb h = herbs.get(i);

            // Check collision
            if (shennong.isCollidingWith(h)) {

                // Medicinal herb effect
                if (h instanceof MedicinalHerb) {

                    MedicinalHerb mh = (MedicinalHerb) h;

                    // Heal player
                    shennong.heal(mh.getHealAmount());

                    // Load journal image
                    currentJournalImage =
                            loadImage("image/" + h.getName() + "Collection.png");

                    // Open journal screen
                    stage = 3;

                    // Increase count
                    collectedCount++;
                }

                // Poison herb effect
                else if (h instanceof PoisonHerb) {

                    PoisonHerb ph = (PoisonHerb) h;

                    // Damage player
                    shennong.damage(ph.getDamage());

                    // Load journal image
                    currentJournalImage =
                            loadImage("image/" + h.getName() + "Collection.png");

                    // Open journal screen
                    stage = 3;

                    // Increase count
                    collectedCount++;
                }

                // Remove herb from map
                herbs.remove(i);

                // Update score
                score = collectedCount;
            }
        }
    }

    /**
     * Checks collision between player and boars.
     */
    public void checkBoarCollisions() {

        // Loop through boars
        for (WildBoar b : boars) {

            // AABB collision check
            boolean collide =
                    shennong.getX() < b.getX() + b.getWidth()
                            &&
                            shennong.getX() + 32 > b.getX()
                            &&
                            shennong.getY() < b.getY() + b.getHeight()
                            &&
                            shennong.getY() + 32 > b.getY();

            // If collision happens
            if (collide) {

                // Damage player
                shennong.damage(10);

                // Start screen shake
                damageTimer = 40;

                // Knockback effect
                if (shennong.getX() < b.getX()) {
                    shennong.move(-20, 0);
                } else {
                    shennong.move(20, 0);
                }
            }
        }
    }

    /**
     * Checks collision between player and snakes.
     */
    public void checkSnakeCollisions() {

        // Loop through snakes
        for (Snake s : snakes) {

            // AABB collision check
            boolean collide =
                    shennong.getX() < s.getX() + s.getWidth()
                            &&
                            shennong.getX() + 32 > s.getX()
                            &&
                            shennong.getY() < s.getY() + s.getHeight()
                            &&
                            shennong.getY() + 32 > s.getY();

            // If collision happens
            if (collide) {

                // Damage player
                shennong.damage(10);

                // Start screen shake
                damageTimer = 40;

                // Knockback effect
                if (shennong.getX() < s.getX()) {
                    shennong.move(-20, 0);
                } else {
                    shennong.move(20, 0);
                }
            }
        }
    }

    /**
     * Draws journal screen after collecting herb.
     */
    public void drawJournalScreen() {

        // Center camera on player
        cameraX = shennong.getX() - width / 2;
        cameraY = shennong.getY() - height / 2;

        // Clamp camera
        cameraX = constrain(cameraX, 0, MAP_WIDTH - width);
        cameraY = constrain(cameraY, 0, MAP_HEIGHT - height);

        // Draw background
        image(backgroundMap, -cameraX, -cameraY);

        // Draw remaining herbs
        for (Herb h : herbs) {
            image(h.image,
                    h.getX() - cameraX,
                    h.getY() - cameraY,
                    30, 30);
        }

        // Draw player
        image(shennong.getImage(),
                shennong.getX() - cameraX,
                shennong.getY() - cameraY,
                32, 32);

        // Dark overlay
        fill(0, 150);
        rect(0, 0, width, height);

        // Show journal image
        if (currentJournalImage != null) {
            image(currentJournalImage, 0, 125, 400, 100);
        }

        // Hint text
        fill(255);
        textAlign(CENTER);
        textSize(16);
        text("Press ENTER to continue", width / 2, 250);
    }

    /**
     * Checks collision with book.
     */
    public void checkBookCollision() {

        // Detect collision with book area
        boolean collide =
                shennong.getX() < BOOK_X + 100
                        &&
                        shennong.getX() + 32 > BOOK_X
                        &&
                        shennong.getY() < BOOK_Y + 100
                        &&
                        shennong.getY() + 32 > BOOK_Y;

        // If touching book
        if (collide) {

            // First entry only
            if (!nearBook) {

                // Enough herbs collected
                if (collectedCount >= 8) {
                    stage = 4;
                } else {
                    showBookMessage = true;
                }
            }

            nearBook = true;

        } else {
            nearBook = false;
        }
    }

    /**
     * Draws ending screen and saves score.
     */
    public void drawEnding() {

        // Save score once
        if (!scoreSaved) {

            try {

                // Append score file
                FileWriter writer = new FileWriter("score.txt", true);
                PrintWriter output = new PrintWriter(writer);

                // Write score
                output.println("Score got:" + score);

                // Close file
                output.close();

                scoreSaved = true;

            } catch (IOException e) {

                // Error output
                System.err.println("Java Exception: " + e);
            }
        }

        // Draw ending screen
        image(endpage, 0, 0);

        // Title
        fill(0);
        textAlign(CENTER);
        textSize(28);
        text("Congratulations!", width / 2, 70);

        // Story text
        textSize(15);
        text("After tasting many herbs,\n\n"
                + "Shennong compiled a book\n"
                + "of medicinal knowledge.\n\n"
                + "His discoveries helped\n"
                + "future generations stay healthy.\n\n",
                width / 2, 100);

        // End label
        textSize(10);
        text("THE END", width / 2, 240);
    }

    /**
     * Returns number of explored tiles.
     */
    public int getExploredCount() {

        // Counter
        int count = 0;

        // Loop rows and columns
        for (int c = 0; c < 12; c++) {
            for (int r = 0; r < 8; r++) {

                // Count explored tiles
                if (explored[c][r] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Draws game over screen.
     */
    public void drawGameOver() {

        // Dark background
        background(50);

        // Game over text
        fill(255, 0, 0);
        textAlign(CENTER);
        textSize(36);
        text("GAME OVER", width / 2, 100);

        // Info text
        fill(255);
        textSize(18);
        text("Shennong was defeated\nby the dangers of nature.", width / 2, 160);

        // Restart hint
        textSize(14);
        text("Press ENTER to restart", width / 2, 240);
    }

    /**
     * Restarts game by reinitializing setup.
     */
    public void restartGame() {

        // Re-run setup
        setup();

        // Return to game stage
        stage = 2;
    }

    /**
     * Draws minimap showing all objects.
     */
    public void drawMiniMap() {

        // Minimap position and size
        int miniX = 280;
        int miniY = 10;
        int miniW = 100;
        int miniH = 70;

        // Background
        fill(255, 220);
        rect(miniX, miniY, miniW, miniH);

        // Herbs on minimap
        fill(0, 255, 0);
        for (Herb h : herbs) {
            float hx = miniX + h.getX() * miniW / MAP_WIDTH;
            float hy = miniY + h.getY() * miniH / MAP_HEIGHT;
            ellipse(hx, hy, 4, 4);
        }

        // Boars on minimap
        fill(150, 75, 0);
        for (WildBoar b : boars) {
            float bx = miniX + b.getX() * miniW / MAP_WIDTH;
            float by = miniY + b.getY() * miniH / MAP_HEIGHT;
            ellipse(bx, by, 4, 4);
        }

        // Snakes on minimap
        fill(180, 0, 255);
        for (Snake s : snakes) {
            float sx = miniX + s.getX() * miniW / MAP_WIDTH;
            float sy = miniY + s.getY() * miniH / MAP_HEIGHT;
            ellipse(sx, sy, 4, 4);
        }

        // Player position
        fill(255, 0, 0);
        float px = miniX + shennong.getX() * miniW / MAP_WIDTH;
        float py = miniY + shennong.getY() * miniH / MAP_HEIGHT;
        ellipse(px, py, 6, 6);

        // Book position
        fill(0, 0, 255);
        float bookMiniX = miniX + BOOK_X * miniW / MAP_WIDTH;
        float bookMiniY = miniY + BOOK_Y * miniH / MAP_HEIGHT;
        rect(bookMiniX, bookMiniY, 4, 5);
    }
}