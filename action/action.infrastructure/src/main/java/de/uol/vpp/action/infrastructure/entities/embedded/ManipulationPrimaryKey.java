package de.uol.vpp.action.infrastructure.entities.embedded;

import de.uol.vpp.action.infrastructure.entities.ActionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManipulationPrimaryKey implements Serializable {

    // many to one relationship with ActionRequest
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_request_id", referencedColumnName = "actionRequestId")
    private ActionRequest actionRequest;

    // column to store start timestamp
    @Column(nullable = false)
    private ZonedDateTime startTimestamp;

    // column to store end timestamp
    @Column(nullable = false)
    private ZonedDateTime endTimestamp;


    // compare two ManipulationPrimaryKey objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManipulationPrimaryKey that = (ManipulationPrimaryKey) o;
        return Objects.equals(actionRequest.getActionRequestId(), that.actionRequest.getActionRequestId()) &&
                Objects.equals(startTimestamp, that.startTimestamp) &&
                Objects.equals(endTimestamp, that.endTimestamp);
    }

    // generate hash code for the ManipulationPrimaryKey object
    @Override
    public int hashCode() {
        return Objects.hash(actionRequest.getActionRequestId(), startTimestamp, endTimestamp);
    }
}