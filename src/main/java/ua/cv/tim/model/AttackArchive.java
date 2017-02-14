package ua.cv.tim.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by rmochetc on 13.02.2017.
 */

@Entity
public class AttackArchive extends UuidEntity {

    @Column(columnDefinition="TEXT")
    private String archiveData;

    public AttackArchive() {

    }

    public AttackArchive(String archiveData) {
        this.archiveData = archiveData;
    }

    public String getArchiveData() {
        return archiveData;
    }

    public void setArchiveData(String archiveData) {
        this.archiveData = archiveData;
    }
}
