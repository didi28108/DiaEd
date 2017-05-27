package diaed.builder;

import diaed.model.DiagramElement;
import diaed.model.StateDiagram;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Template1 implements DiagramTemplate {
    StateDiagram diagram = new StateDiagram();

    public Template1(){
        construct();
    }

    public void construct(){
        DiagramElement state1 = new StateBuilder()
                .setName("State1")
                .setPosition(200, 300)
                .build();

        DiagramElement state2 = new StateBuilder()
                .setName("State2")
                .setPosition(600, 300)
                .build();

        DiagramElement trans2 = new TransitionBuilder()
                .setName("trans2")
                .setPosition(260, 300)
                .setDestination(540, 300)
                .build();

        diagram.add(state1);
        diagram.add(state2);
        diagram.add(trans2);
    }

    @Override
    public StateDiagram getDiagram() {
        return diagram;
    }
}
