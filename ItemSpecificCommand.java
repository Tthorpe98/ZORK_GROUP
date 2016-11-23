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

        System.out.println(verb + "VERB BEFORE PRINT");

        if (itemReferredTo.getEventsForVerb(verb) != null)//if there IS at least one event for this verb
        {
            System.out.println(verb + "THIS IS VERB");
            String firstEvent = itemReferredTo.getEventsForVerb(verb).get(0);
            if(itemReferredTo.getEventsForVerb(verb).size() > 1) {
                secondEvent = itemReferredTo.getEventsForVerb(verb).get(1);
                String firstThreeLetter2 = secondEvent.substring(0,3);
            }
            String firstThreeLetters = firstEvent.substring(1, 4);
            //String firstThreeLetters2 = secondEvent.substring(0, 3);
            System.out.println(firstThreeLetters);
            //System.out.println(firstThreeLetters2);

            //7-tier if-else statement
            if (firstThreeLetters.equals("Die") || firstThreeLetters2.equals("Die")) {
                Event.die(true);
            }  if (firstThreeLetters.equals("Win") || firstThreeLetters2.equals("Win")) {
                Event.win(true);
            }  if (firstThreeLetters.equals("Dis") || firstThreeLetters2.equals("Dis")) {
                Event.disappear(itemReferredTo);
            }  if (firstThreeLetters.equals("Tra") || firstThreeLetters2.equals("Tra")) {
                String effect = null;
                ArrayList<String> effects = itemReferredTo.getEffect(verb);
                for(int i = 0; i < effects.size(); i++){
                    System.out.println("THIS IS I" + effects.get(i));
                    if(effects.get(i).contains("Tra")){
                        effect = effects.get(i);
                    }
                }
                String[] newItem2 = effect.split("\\(");
                String newItem1 = newItem2[1].substring(0, newItem2[1].length() - 1);
                Item newItem;

                try {
                    newItem = game.getDungeon().getItem(newItem1);
                    System.out.println("NEW ITEM IS" + newItem);
                } catch (Item.NoItemException ex) {
                    newItem = null;
                }
                Event.transform(itemReferredTo, newItem);
            } if (firstThreeLetters.equals("Tel") || firstThreeLetters2.equals("Tel")){
                Event.teleport();
            } if (firstThreeLetters.equals("Sco") || firstThreeLetters2.equals("Sco")){
                String effect = null;
                ArrayList<String> effects = itemReferredTo.getEffect(verb);
                for(int i = 0; i < effects.size(); i++){
                    if(effects.get(i).contains("Sco")){
                        effect = effects.get(i);
                    }
                }

                int sco = Integer.valueOf(effect.substring(effect.indexOf("(") + 1, effect.indexOf(")")));
                System.out.println("SCO" + sco);
                Event.score(sco);
            }  if (firstThreeLetters.equals("Wou") || firstThreeLetters2.equals("Wou") || firstThreeLetters.equals("[Wo") || firstThreeLetters2.equals("[Wo")){
                String effect = null;
                ArrayList<String> effects = itemReferredTo.getEffect(verb);
                for(int i = 0; i < effects.size(); i++){
                    if(effects.get(i).contains("Wou")){
                        effect = effects.get(i);
                    }
                }


                int dam = Integer.valueOf(effect.substring(effect.indexOf("(") + 1, effect.indexOf(")")));
                Event.wound(dam);
            }

            //call the method
            //do the same thing for the second event(if there is one)
        }

        return (msg == null
                ? "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";

    }
}