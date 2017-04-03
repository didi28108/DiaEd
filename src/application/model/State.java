package application.model;

import application.Store;
import application.viewModel.StateViewModel;

/**
 * Created by ucfan on 2017/3/28.
 */

public class State extends DiagramElement {
    public State() {
        setPositionX(150);
        setPositionY(150);
    }

    @Override
    public void draw(Store store) {
        if (viewModel == null) {
            viewModel = new StateViewModel(this);
        }
        viewModel.draw(store);
    }
}



