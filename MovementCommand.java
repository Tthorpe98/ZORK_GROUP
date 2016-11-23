class MovementCommand extends Command {

    private String dir;

    /**
     * This command is created when the user input is a direction the users to desires to move.
     * @param dir  direction that the user has entered
     */
    MovementCommand(String dir) {
        this.dir = dir;
    }

    /**
     * Finds the adventurer's current room and checks to see if they can leave current room in desired direction.
     * If movement is possible, adventurer's room is changed. Returns accordingly.
     * @return either new Room description (if movement was possible) or a message saying movement is not possible in desired direction.
     */
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
