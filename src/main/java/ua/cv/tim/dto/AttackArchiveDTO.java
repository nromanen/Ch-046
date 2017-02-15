package ua.cv.tim.dto;

/**
 * Created by rmochetc on 14.02.2017.
 */
public class AttackArchiveDTO {

    private String uuid;
    private String date;

    public AttackArchiveDTO() {

    }


    public AttackArchiveDTO(String uuid, String date) {
        this.uuid = uuid;
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
