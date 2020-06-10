package io.kubesure.multistream.datatypes;

import java.io.Serializable;

public interface Event extends Serializable{
    
    public long getEventTime();
}