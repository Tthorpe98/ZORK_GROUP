/**
 *(Description)
 *
 * @author(Collin Mason)
 * @version(11/16/2016)
 */

class ScoreCommand extends Command{
    private int score;

    ScoreCommand(int score){
        this.score = score;
    }

    public String execute(){
        String scoreString = String.valueOf(score);
        return "You have " + scoreString + " points.";
    }
}