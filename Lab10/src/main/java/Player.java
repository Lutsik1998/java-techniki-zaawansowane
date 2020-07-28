public enum Player {
    Puste(0),
    Gracz(1),
    Komputer(2);
    private int value;

    public int getValue() {
        return this.value;
    }

    private Player(int value)
    {
        this.value = value;
    }
}
