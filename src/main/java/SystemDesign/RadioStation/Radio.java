package SystemDesign.RadioStation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 7/22/17.
 */
public class Radio {
    double currentStation;
    List<RadioStation> radioStationList;

    public Radio() {
        radioStationList = new ArrayList<>();
    }

    public List<RadioStation> getRadioStations() {
        return radioStationList;
    }

    public void setRadioStations(List<RadioStation> radioStations) {
        radioStationList = radioStations;
    }

    public double getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(double currentStation) {
        this.currentStation = currentStation;
    }
}
