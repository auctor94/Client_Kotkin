package DataSources;

import java.io.Serializable;

public class Surcharge implements Serializable {
    private String code;
    private String nameSurcharge;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameSurcharge() {
        return nameSurcharge;
    }

    public void setNameSurcharge(String nameSurcharge) {
        this.nameSurcharge = nameSurcharge;
    }

    public Surcharge(String code, String nameSurcharge) {
        this.code = code;
        this.nameSurcharge = nameSurcharge;
    }


}
