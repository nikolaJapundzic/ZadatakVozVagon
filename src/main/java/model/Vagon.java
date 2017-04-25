package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 25.4.17..
 */
@DatabaseTable(tableName = "vagon")
public class Vagon {
    public static final String POLJE_OZNAKA = "oznaka";
    public static final String POLJE_OPIS = "opis";
    public static final String POLJE_NOSIVOST = "nosivost";
    public static final String POLJE_TERET = "teret";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = POLJE_OZNAKA, canBeNull = false)
    private String oznaka;

    @DatabaseField(columnName = POLJE_OPIS, canBeNull = false)
    private String opis;

    @DatabaseField(columnName = POLJE_NOSIVOST, canBeNull = false)
    private double nosivost;

    @DatabaseField(columnName = POLJE_TERET, canBeNull = false)
    private double teret = 0.0;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "voz")
    Voz voz;

    public Vagon() {
    }

    public Vagon(String opis, double nosivost) {
        this.opis = opis;
        this.nosivost = nosivost;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getNosivost() {
        return nosivost;
    }

    public void setNosivost(double nosivost) {
        this.nosivost = nosivost;
    }

    public double getTeret() {
        return teret;
    }

    public void setTeret(double teret) {
        this.teret = teret;
    }

    public Voz getVoz() {
        return voz;
    }

    public void setVoz(Voz voz) {
        this.voz = voz;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vagon{" +
                "id=" + id +
                ", oznaka='" + oznaka + '\'' +
                ", opis='" + opis + '\'' +
                ", nosivost=" + nosivost +
                ", teret=" + teret +
                '}';
    }
}
