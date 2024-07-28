//207270521 Denis Mogilevsky

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Denis Mogilevsky.
 * runs the game.
 */
public class Ass6Game {

    /**
     * main.
     * @param args none.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner("Arkanoid", 800, 600, 60);
        List<LevelInformation> levelList =
                new ArrayList<>(Arrays.asList(new DirectHit(), new WideEasy(), new LevelThree()));
        GameFlow game = new GameFlow(runner);
        game.runLevels(levelList);
    }
}
