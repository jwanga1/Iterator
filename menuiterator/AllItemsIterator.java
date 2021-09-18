public class AllItemsIterator extends CategoryIterator {

    public AllItemsIterator(Menu obj) {
        super(obj, 0);
    }

    public AllItemsIterator(TargetIterator obj) { super(obj, 0); }

    public boolean compareTo(Menu.MenuItem obj) {
        return true;
    }
}
