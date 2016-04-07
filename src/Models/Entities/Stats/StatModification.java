package Models.Entities.Stats;

/**
 * Created by josh on 4/6/16.
 */
public class StatModification {
    private Stat stat;
    private int delta;

    public void apply(Stats stats) {
        stats.setStat(stat, delta);
    }

    public void remove(Stats stats) {
        stats.setStat(stat, -delta);
    }

    public StatModification(Stat stat, int delta) {
        this.stat = stat;
        this.delta = delta;
    }

    public Stat getStat() {
        return stat;
    }

    public int getDelta() {
        return delta;
    }

    public String toString() {
        String sign = delta > 0 ? "+" : "";
        return sign + Integer.toString(delta) + " " + stat.getDescriptor();
    }
}


