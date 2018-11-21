package DataSources;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Percent {
    private SimpleStringProperty FIO;
    private SimpleFloatProperty percent;

    public Percent(SimpleStringProperty FIO, SimpleFloatProperty percent) {
        this.FIO = FIO;
        this.percent = percent;
    }

    public String getFIO() {
        return FIO.get();
    }

    public SimpleStringProperty FIOProperty() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO.set(FIO);
    }

    public float getPercent() {
        return percent.get();
    }

    public SimpleFloatProperty percentProperty() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent.set(percent);
    }

    public Percent(String FIO, float percent) {
        this.FIO = new SimpleStringProperty(FIO);
        this.percent = new SimpleFloatProperty(percent);
    }
}
