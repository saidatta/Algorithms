package SystemDesign.Command;

/**
 * Created by venkatamunnangi on 4/23/17.
 */
public interface SpellCommand {
    String incantation();

    boolean undo();

    boolean redo();
}
