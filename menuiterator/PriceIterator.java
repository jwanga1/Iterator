public class PriceIterator extends TargetIterator {

    String price;

    public PriceIterator(Menu obj, String price) {
        super(obj);
        this.price = price;
    }

    public PriceIterator(TargetIterator obj, String price) {
        super(obj);
        this.price = price;
    }

    public boolean compareTo(Menu.MenuItem obj) {
        return (getPrice(price) >= getPrice(obj.price));
    }

    private static int getPrice(String price) {
        return (int) (Double.parseDouble(price) * 100);
    }
}