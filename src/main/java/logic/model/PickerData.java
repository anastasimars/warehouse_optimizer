package logic.model;

import java.time.LocalTime;


public class PickerData {
    private String pickerName;
    private LocalTime pickingStartTime;
    private LocalTime pickingEndTime;

    public PickerData(String pickerName, LocalTime pickingStartTime, LocalTime pickingEndTime) {
        this.pickerName = pickerName;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }

    public String getPickerName() {
        return pickerName;
    }

    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
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
}
