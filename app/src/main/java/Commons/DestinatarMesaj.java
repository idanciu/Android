package Commons;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by intern on 9/22/2017.
 */

public class DestinatarMesaj implements Parcelable {
    private Integer idMesaj;
    private Integer idAngajat;

    public Integer getIdMesaj() { return idMesaj; }
    public DestinatarMesaj setIdMesaj(Integer idMesaj) { this.idMesaj = idMesaj; return this;}

    public Integer getIdAngajat() { return idAngajat; }
    public DestinatarMesaj setIdAngajat(Integer idAngajat) { this.idAngajat = idAngajat; return this;}

    public DestinatarMesaj(Integer idMesaj, Integer idAngajat) {
        this.idMesaj = idMesaj;
        this.idAngajat = idAngajat;
    }

    protected DestinatarMesaj(Parcel in) {
        idMesaj = in.readInt();
        idAngajat = in.readInt();
    }

    public static final Creator<DestinatarMesaj> CREATOR = new Creator<DestinatarMesaj>() {
        @Override
        public DestinatarMesaj createFromParcel(Parcel in) { return new DestinatarMesaj(in); }

        @Override
        public DestinatarMesaj[] newArray(int size) { return new DestinatarMesaj[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idMesaj);
        dest.writeInt(idAngajat);
    }
}