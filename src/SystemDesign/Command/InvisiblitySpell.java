package SystemDesign.Command;

/**
 * Created by venkatamunnangi on 4/23/17.
 */
public class InvisiblitySpell implements SpellCommand{

    @Override
    public String incantation() {
        return "Invisiblio!";
    }

    @Override
    public boolean undo() {
        return true;
    }

    @Override
    public boolean redo() {
        return true;
    }
}
