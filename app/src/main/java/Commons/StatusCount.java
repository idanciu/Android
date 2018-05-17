package Commons;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by idanciu on 9/12/2017.
 */

public class StatusCount implements Parcelable{
    private String status;
    private Integer nrFacturi;
    private Integer sorter;

    public Integer getSorter() { return sorter; }
    public void setSorter(Integer sorter) { this.sorter = sorter; }

    public StatusCount(String status, Integer nrFacturi) {
        this.status = status;
        this.nrFacturi = nrFacturi;
    }

    protected StatusCount(Parcel in) {
        status = in.readString();
        nrFacturi = in.readInt();
    }

    public static final Creator<StatusCount> CREATOR = new Creator<StatusCount>() {
        @Override
        public StatusCount createFromParcel(Parcel in) { return new StatusCount(in); }

        @Override
        public StatusCount[] newArray(int size) { return new StatusCount[size]; }
    };

    public String getStatus() { return status; }
    public StatusCount setStatus(String status) { this.status = status; return this;}

    public Integer getNrFacturi() { return nrFacturi; }
    public void setNrFacturi(Integer nrFacturi) { this.nrFacturi = nrFacturi; }

    @Override
    public String toString() {
        String facturi = "";
        if(nrFacturi == 1) {
            facturi = "factura";
        } else {
            facturi = "facturi";
        }
        return status + " (" + nrFacturi +  " " + facturi + ")";
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(nrFacturi);
    }


    public static final Comparator<StatusCount> DESCENDING_COMPARATOR = new Comparator<StatusCount>() {
        public int compare(StatusCount s1, StatusCount s2) {
            return s1.getSorter() - s2.getSorter();
        }
    };
}
