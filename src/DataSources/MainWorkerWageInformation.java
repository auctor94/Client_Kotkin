package DataSources;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainWorkerWageInformation {
    private SimpleStringProperty FIOWorker;
    private SimpleStringProperty positionWorker;
    private SimpleFloatProperty wagesWorker;
    private SimpleFloatProperty prizeWorker;

    public MainWorkerWageInformation(SimpleStringProperty FIOWorker, SimpleStringProperty positionWorker, SimpleFloatProperty wagesWorker, SimpleFloatProperty prizeWorker) {
        this.FIOWorker = FIOWorker;
        this.positionWorker = positionWorker;
        this.wagesWorker = wagesWorker;
        this.prizeWorker = prizeWorker;
    }

    public String getFIOWorker() {
        return FIOWorker.get();
    }

    public SimpleStringProperty FIOWorkerProperty() {
        return FIOWorker;
    }

    public String getPositionWorker() {
        return positionWorker.get();
    }

    public SimpleStringProperty positionWorkerProperty() {
        return positionWorker;
    }

    public float getWagesWorker() {
        return wagesWorker.get();
    }

    public SimpleFloatProperty wagesWorkerProperty() {
        return wagesWorker;
    }

    public float getPrizeWorker() {
        return prizeWorker.get();
    }

    public SimpleFloatProperty prizeWorkerProperty() {
        return prizeWorker;
    }

    public void setFIOWorker(String FIOWorker) {
        this.FIOWorker.set(FIOWorker);
    }

    public void setPositionWorker(String positionWorker) {
        this.positionWorker.set(positionWorker);
    }

    public void setWagesWorker(float wagesWorker) {
        this.wagesWorker.set(wagesWorker);
    }

    public void setPrizeWorker(float prizeWorker) {
        this.prizeWorker.set(prizeWorker);
    }

    public MainWorkerWageInformation (String FIOWorker, String positionWorker, float wages, float prize) {
        this.FIOWorker = new SimpleStringProperty(FIOWorker);
        this.positionWorker = new SimpleStringProperty(positionWorker);
        this.wagesWorker = new SimpleFloatProperty(wages);
        this.prizeWorker = new SimpleFloatProperty(prize);

    }
}
