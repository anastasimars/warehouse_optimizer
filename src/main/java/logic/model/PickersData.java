package logic.model;

import java.time.LocalTime;
import java.util.List;

public class PickersData {
    private List<String> pickers;
    private LocalTime pickingStartTime;
    private LocalTime pickingEndTime;

    public PickersData(List<String> pickers, LocalTime pickingStartTIme, LocalTime pickingEndTime) {
        this.pickers = pickers;
        this.pickingStartTime = pickingStartTIme;
        this.pickingEndTime = pickingEndTime;
    }

    public PickersData() {
    }

    public List<String> getPickers() {
        return pickers;
    }

    public void setPickers(List<String> pickers) {
        this.pickers = pickers;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(LocalTime pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public LocalTime getPickingEndTime() {
        return pickingEndTime;
    }

    public void setPickingEndTime(LocalTime pickingEndTime) {
        this.pickingEndTime = pickingEndTime;
    }

    @Override
    public String toString() {
        return "pickers: " + pickers +
                ", pickingStartTIme: " + pickingStartTime +
                ", pickingEndTime: " + pickingEndTime;
    }
}
