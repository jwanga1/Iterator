public class CategoryIterator extends TargetIterator{

    int category;

    public CategoryIterator(Menu obj, int category) {
        super(obj);
        this.category = category;
    }

    public CategoryIterator(TargetIterator obj, int category) {
        super(obj);
        this.category = category;
    }

    public boolean compareTo(Menu.MenuItem obj) {
        return category == obj.category;
    }
}
