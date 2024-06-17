//1. CodeNamePair 클래스
import java.io.Serializable;

public class CodeNamePair implements Serializable {
    private String code;
    private String name;

    public CodeNamePair(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
