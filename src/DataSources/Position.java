package DataSources;

import java.io.Serializable;

public class Position implements Serializable {
    private String code;
    private String namePosition;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public Position(String code, String namePosition) {
        this.code = code;
        this.namePosition = namePosition;
    }
}
