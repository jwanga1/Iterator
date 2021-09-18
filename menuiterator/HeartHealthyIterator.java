public class HeartHealthyIterator extends TargetIterator {

    boolean heartHealthy;

    public HeartHealthyIterator(Menu obj, boolean heartHealthy) {
        super(obj);
        this.heartHealthy = heartHealthy;
    }

    public HeartHealthyIterator(TargetIterator obj, boolean heartHealthy) {
        super(obj);
        this.heartHealthy = heartHealthy;
    }

    public boolean compareTo(Menu.MenuItem obj) {
        return this.heartHealthy == obj.heartHealthy;
    }
}


