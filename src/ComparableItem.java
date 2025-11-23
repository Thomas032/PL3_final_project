/*
 * A class that represents an item with a long value and tracks the number of comparisons made.
 */
public class ComparableItem implements Comparable<ComparableItem> {
    private final long value;
    private static long comparisonCount = 0;

    public ComparableItem(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public static long getComparisonCount() {
        return comparisonCount;
    }

    public static void resetComparisonCount() {
        comparisonCount = 0;
    }

    @Override
    public int compareTo(ComparableItem other) {
        comparisonCount++;
        return Long.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

