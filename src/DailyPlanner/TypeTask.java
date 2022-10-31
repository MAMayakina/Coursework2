package DailyPlanner;

public enum TypeTask {
    WORKING("рабочая"),
    PERSONAL("личная");

    private String type;

    TypeTask(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(int type) {
        try {
            switch (type) {
                case 1:
                    this.type = String.valueOf(WORKING);
                    break;
                case 2:
                    this.type = String.valueOf(PERSONAL);
                    break;
                default:
                    throw new InvalidParametrException();
            }
        } catch (InvalidParametrException e) {
            System.out.println("Некорректно введен тип задачи - по умолчанию проставлен личный тип");
            this.type = String.valueOf(PERSONAL);
        }
    }
}