package behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 *
 * @author Lee
 */
public class ConcreteMediator extends Mediator {

    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    void register(Colleague colleague) {
        if (!colleagues.contains(colleagues)) {
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    void relay(Colleague colleague) {
        for (Colleague c: colleagues) {
            if (!c.equals(colleague)) {
                c.receive();
            }
        }
    }
}