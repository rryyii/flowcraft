package server.model;

public enum Title {
    ADMIN(1),
    MANAGER(2),
    MEMBER(3);

    private int title;

    private Title(int title) {
        this.title = title;
    }
}
