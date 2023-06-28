package behavior.mediator;

/**
 * 中介者模式
 *
 * @author Lee
 */
public class MediatorPattern {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague colleagueA = new ConcreteColleagueA();
        Colleague colleagueB = new ConcreteColleagueB();
        Colleague colleagueC = new ConcreteColleagueC();

        mediator.register(colleagueA);
        mediator.register(colleagueB);
        mediator.register(colleagueC);

        colleagueA.send();
        colleagueB.send();
    }
}