package DataSources;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class PrizeEnrollInformation {
    private SimpleStringProperty fioWorker;
    private SimpleStringProperty descriptionPrize;
    private SimpleFloatProperty prizeEnroll;
    private LocalDate dateEnroll;

    public PrizeEnrollInformation(SimpleStringProperty fioWorker, SimpleStringProperty descriptionPrize, SimpleFloatProperty prizeEnroll, LocalDate dateEnroll) {
        this.fioWorker = fioWorker;
        this.descriptionPrize = descriptionPrize;
        this.prizeEnroll = prizeEnroll;
        this.dateEnroll = dateEnroll;
    }

    public String getFioWorker() {
        return fioWorker.get();
    }

    public SimpleStringProperty fioWorkerProperty() {
        return fioWorker;
    }

    public void setFioWorker(String fioWorker) {
        this.fioWorker.set(fioWorker);
    }

    public String getDescriptionPrize() {
        return descriptionPrize.get();
    }

    public SimpleStringProperty descriptionPrizeProperty() {
        return descriptionPrize;
    }

    public void setDescriptionPrize(String descriptionPrize) {
        this.descriptionPrize.set(descriptionPrize);
    }

    public float getPrizeEnroll() {
        return prizeEnroll.get();
    }

    public SimpleFloatProperty prizeEnrollProperty() {
        return prizeEnroll;
    }

    public void setPrizeEnroll(float prizeEnroll) {
        this.prizeEnroll.set(prizeEnroll);
    }

    public LocalDate getDateEnroll() {
        return dateEnroll;
    }

    public void setDateEnroll(LocalDate dateEnroll) {
        this.dateEnroll = dateEnroll;
    }

    public PrizeEnrollInformation (String fio, String desc, float sum, LocalDate data) {
        this.fioWorker = new SimpleStringProperty(fio);
        this.descriptionPrize = new SimpleStringProperty(desc);
        this.prizeEnroll = new SimpleFloatProperty(sum);
        this.dateEnroll = data;
    }
}
