package diaed.scene;

import diaed.Store;
import diaed.model.StateDiagram;
import diaed.view.Canvas;
import diaed.view.EditorView;
import diaed.view.Toolbar;
import diaed.viewModel.StateDiagramViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Created by ucfan on 2017/5/26.
 */
public class EditorScene {
    private Store store;
    private EditorView view;
    private StateDiagramViewModel stateDiagramVM;
    private StateDiagram diagram;

    public void setModel(StateDiagram diagram) {
        stateDiagramVM.setModel(diagram);
    }

    public StateDiagram getModel() {
        return stateDiagramVM.getModel();
    }

    public ObjectProperty<StateDiagram> modelProperty() {
        return stateDiagramVM.modelProperty();
    }

    public EditorScene(Store store, Stage primaryStage) {
        this.store = store;
        store.setRoot(this);

        this.view = new EditorView(primaryStage);
        view.setCanvas(new Canvas(store));
        view.setToolbar(new Toolbar(store));
    }


    public void clear() {
        view.getCanvas().clear();
    }

    public void add(Node node) {
        view.getCanvas().getChildren().add(node);
    }


    // 重新繪製畫面
    // NOTE: 可優化成只重新繪製有差異的部分
    public void redraw() {
        clear();
        stateDiagramVM.update();
    }

    public void initialize() {
        if (diagram == null) {
            diagram = store.getSelectedTemplate().getDiagram();
        }

        diagram.addListener(c -> redraw());
        stateDiagramVM = new StateDiagramViewModel(store, diagram);
        stateDiagramVM.modelProperty().addListener(((observable, oldValue, newValue) -> {
            newValue.addListener(c -> redraw());
            redraw();
        }));
        store.setDiagram(stateDiagramVM.getModel());
        store.resetHistory();
    }

    public void load(StateDiagram diagram) {
        this.diagram = diagram;
        initialize();
    }
}
