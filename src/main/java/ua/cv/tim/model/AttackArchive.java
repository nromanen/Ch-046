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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AttackArchive that = (AttackArchive) o;

        return archiveData != null ? archiveData.equals(that.archiveData) : that.archiveData == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (archiveData != null ? archiveData.hashCode() : 0);
        return result;
    }
}
