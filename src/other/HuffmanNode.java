package other;

public class HuffmanNode {

    String c;
    int frequency;

    HuffmanNode right, left;

    public HuffmanNode(String c, int frequency, HuffmanNode right, HuffmanNode left) {
        this.c = c;
        this.frequency = frequency;
        this.right = right;
        this.left = left;
    }

    public HuffmanNode(String c, int frequency) {
        this.c = c;
        this.frequency = frequency;
        right = null;
        left = null;
    }

    public String getChar() {
        return c;
    }

    public int getFrequency() {
        return frequency;
    }

}
