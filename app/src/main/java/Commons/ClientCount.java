package Commons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by idanciu on 9/20/2017.
 */

public class ClientCount implements Parcelable {
    private String nume;
    private Integer nrFacturi;


    protected ClientCount(Parcel in) {
        nume = in.readString();
        nrFacturi = in.readInt();
    }

    public static final Creator<ClientCount> CREATOR = new Creator<ClientCount>() {
        @Override
        public ClientCount createFromParcel(Parcel in) { return new ClientCount(in); }

        @Override
        public ClientCount[] newArray(int size) { return new ClientCount[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeInt(nrFacturi);
    }

    public ClientCount(String nume, Integer nrFacturi) {
        this.nume = nume;
        this.nrFacturi = nrFacturi;
    }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

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
        return nume + " (" + nrFacturi +  " " + facturi + ")";
    }
}
