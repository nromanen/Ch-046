package ua.cv.tim.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class UuidEntity {
    @Id
    private String uuid;

    private Date lastModified;

    @PrePersist
    protected void prePersist() {
        uuid = UUID.randomUUID().toString();
        lastModified = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        lastModified = new Date();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UuidEntity == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final UuidEntity otherObject = (UuidEntity) obj;

        return this.uuid.equals(otherObject.uuid);
    }

}
