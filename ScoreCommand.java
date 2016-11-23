/**
 *(Description)
 *
 * @author(Collin Mason)
 * @version(11/16/2016)
 */

class ScoreCommand extends Command{
    private int score = GameState.instance().getScore();

    ScoreCommand(){

    }

    public String execute(){
        String scoreString = String.valueOf(score);
        return "You have " + scoreString + " points.";
    }
}