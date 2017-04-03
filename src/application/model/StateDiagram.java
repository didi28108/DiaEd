package application.model;

import application.Store;
import application.history.DiagramState;
import application.history.Memento;
import application.history.Originator;
import application.viewModel.StateDiagramViewModel;

public class StateDiagram extends DiagramElement implements Iterable<DiagramElement>, Originator {
    // 狀態圖的狀態（子元素）
    private DiagramState children = new DiagramState();

    // 新增子元素
    public void add(DiagramElement element) {
        children.add(element);
    }

    // 移除子元素
    public void remove(DiagramElement element) {
        children.remove(element);
    }

    // 取得子元素（以索引）
    public DiagramElement get(int index) {
        return children.get(index);
    }

    // 取得子元素（以 ref）
    public DiagramElement get(DiagramElement element) {
        int index = children.indexOf(element);
        if (index < 0) {
            return null;
        }
        return children.get(index);
    }

    @Override
    public void draw(Store store) {
        if (viewModel == null) {
            viewModel = new StateDiagramViewModel(this);
        }
        viewModel.draw(store);
    }

    // 以 Memento 儲存目前狀態
    @Override
    public Memento save() {
        Memento memento = new Memento();
        DiagramState backup = new DiagramState();

        Iterator<DiagramElement> iter = iterator();
        while (iter.hasNext()) {
            DiagramElement element = iter.next();
            // deep copying
            backup.add(element.clone());
        }

        memento.setState(backup);
        return memento;
    }

    // 以 Memento 還原狀態
    @Override
    public void restore(Memento memento) {
        children = memento.getState();
    }



    /* Iterator of State Diagram */


    // 建立 Iterator
    @Override
    public Iterator<DiagramElement> iterator() {
        return new StateDiagramIterator();
    }


    public class StateDiagramIterator implements Iterator<DiagramElement> {
        private int index = 0;

        // 當 index 小於子元素數量代表可繼續迭代
        @Override
        public boolean hasNext() {
            return (index < children.size());
        }

        // 取得下一個子元素
        @Override
        public DiagramElement next() {
            if (hasNext()) {
                return children.get(index++);
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }


}
