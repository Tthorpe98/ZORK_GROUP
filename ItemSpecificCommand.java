import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
    private GameState game = GameState.instance();
    private String secondEvent;
    private String firstThreeLetters2;

    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
        secondEvent = "";
        firstThreeLetters2 = "";

    }

    public String execute() {

        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }

        String msg = itemReferredTo.getMessageForVerb(verb);

        if (itemReferredTo.getEventsForVerb(verb) != null)//if there IS at least one event for this verb
        {
            ArrayList<String> arrayEvent = new ArrayList<>(itemReferredTo.getEventsForVerb(verb));

            for (int j = 0; j < arrayEvent.size(); j++) {
                String firstThreeLetters = arrayEvent.get(j).substring(0, 3);

                //7-tier if-else statement
                if (firstThreeLetters.equals("Die") || firstThreeLetters.equals("[Di")) {
                    Event.die(true);
                }
                if (firstThreeLetters.equals("Win") || firstThreeLetters.equals("[Wi")) {
                    Event.win(true);
                }
                if (firstThreeLetters.equals("Dis") || firstThreeLetters.equals("[Di")) {
                    Event.disappear(itemReferredTo);
                }
                if (firstThreeLetters.equals("[Tr") || firstThreeLetters.equals("Tra")) {
                    String effect = null;
                    ArrayList<String> effects = itemReferredTo.getEffect(verb);
                    for (int i = 0; i < effects.size(); i++) {
                        if (effects.get(i).contains("Tra")) {
                            effect = effects.get(i);
                        }
                    }
                    String[] newItem2 = effect.split("\\(");
                    String newItem1 = newItem2[1].substring(0, newItem2[1].length() - 1);
                    Item newItem;

                    try {
                        newItem = game.getDungeon().getItem(newItem1);
                    } catch (Item.NoItemException ex) {
                        newItem = null;
                    }
                    Event.transform(itemReferredTo, newItem);
                }
                if (firstThreeLetters.equals("Tel") || firstThreeLetters.equals("[Te")) {
                    Event.teleport();
                }
                if (firstThreeLetters.equals("Sco") || firstThreeLetters.equals("[Sc")) {
                    String effect = null;
                    ArrayList<String> effects = itemReferredTo.getEffect(verb);
                    for (int i = 0; i < effects.size(); i++) {
                        if (effects.get(i).contains("Sco")) {
                            effect = effects.get(i);
                        }
                    }

                    int sco = Integer.valueOf(effect.substring(effect.indexOf("(") + 1, effect.indexOf(")")));
                    Event.score(sco);
                }
                if (firstThreeLetters.equals("Wou") || firstThreeLetters.equals("[Wo")) {
                    String effect = null;
                    ArrayList<String> effects = itemReferredTo.getEffect(verb);
                    for (int i = 0; i < effects.size(); i++) {
                        if (effects.get(i).contains("Wou")) {
                            effect = effects.get(i);
                        }
                    }


                    int dam = Integer.valueOf(effect.substring(effect.indexOf("(") + 1, effect.indexOf(")")));
                    Event.wound(dam);
                }
            }

            //call the method
            //do the same thing for the second event(if there is one)
        }

        return (msg == null
                ? "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";

    }
}