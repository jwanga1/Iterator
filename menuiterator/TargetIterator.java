import java.util.LinkedList;

public abstract class TargetIterator implements MenuIterator{
    public int current = 0;
    public boolean last = true;
    public TargetIterator iterator = null;
    public LinkedList<Menu.MenuItem> root;

    public TargetIterator(Menu obj) {
        iterator = null;
        last = true;
        root = obj.root;
    }

    public TargetIterator(TargetIterator obj) {
        last = true;
        iterator = obj;
        obj.setLastToFalse();
        root = iterator.getRoot();
    }

    public LinkedList<Menu.MenuItem> getRoot() {
        return root;
    }

    public void setLastToFalse() {
        last = false;
    }

    public abstract boolean compareTo(Menu.MenuItem obj);
        // implement

    @Override
    public Object hasNext() {
        int tmp = current;
        boolean found = false;
        while (!found && tmp < root.size()) {  // and tmp still in range
            if (iterator == null) {
                found = this.compareTo(root.get(tmp));
                if (!found)
                    tmp = tmp + 1;
            } else {
                tmp = (int) iterator.hasNext();
                if (tmp < root.size()) {
                    found = this.compareTo(root.get(tmp));
                    if (!found)
                        this.iterator.current++;   // i know it's cheating... but really
                }
            }
        }
        return last ? found : tmp;
    }

    @Override
    public Object next() {
        boolean found = false;

        while (!found) {
            if (iterator == null) { // bottom layer iterator - searching over LinkedList
                found = this.compareTo(root.get(current));
                if (!found)
                    current = current + 1;
            } else { // upper layer iterator - obj here (hasNext found it) return first match
                current = (int) iterator.next();
                found = this.compareTo(root.get(current));
            }
        }
        return last ? root.get(current++) : current++; // if returning to main, return record, else return index
    }

    @Override
    public void remove() {
        root.remove(--current);
    }
}
