package com.ss.schedule.model;

import com.ss.schedule.dao.PairsDao;

public enum Pair {
FIRST, SECOND, THIRD, FORTH, FIFTH, SIXTH, SEVENTH, EIGHTS, NINTH, TENTH;
    long id;

     Pair(){
        this.id=new PairsDao().getPairIdByName(this.name());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
