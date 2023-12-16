package oncall.domain.crew;

public class CrewName {
    public static final int MAX_NAME_LEN = 5;

    private final String name;

    public CrewName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 null이거나 빈 문자열일 수 없습니다.");
        }

        if (name.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
