package Commons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by idanciu on 9/20/2017.
 */

public class StatusFactura implements Parcelable{

    private String status;

    protected StatusFactura(Parcel in) { status = in.readString(); }

    @Override
    public void writeToParcel(Parcel dest, int flags) { dest.writeString(status); }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<StatusFactura> CREATOR = new Creator<StatusFactura>() {
        @Override
        public StatusFactura createFromParcel(Parcel in) { return new StatusFactura(in); }

        @Override
        public StatusFactura[] newArray(int size) { return new StatusFactura[size]; }
    };

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public StatusFactura(String status) { this.status = status; }
}
