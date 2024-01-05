package de.uol.vpp.action.infrastructure.entities;

import de.uol.vpp.action.domain.enums.ActionTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class Action {

    //@EmbeddedId annotation to indicate that the Action class has an embedded id
    @EmbeddedId
    private ActionPrimaryKey actionPrimaryKey;

    @Column(nullable = false)
    private Boolean isStorage;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Double hours;

    //@Embeddable annotation to indicate that the ActionPrimaryKey class is an embeddable
    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    //ActionPrimaryKey class is a static class
    public static class ActionPrimaryKey implements Serializable {

        //@ManyToOne annotation to indicate that the ActionPrimaryKey class has a many to one relationship
        @ManyToOne(fetch = FetchType.LAZY)
        //@JoinColumns annotation to indicate that the ActionPrimaryKey class has multiple join columns
        @JoinColumns({
                @JoinColumn(name = "action_request_id", referencedColumnName = "action_request_id"),
                @JoinColumn(name = "start_timestamp", referencedColumnName = "startTimestamp"),
                @JoinColumn(name = "end_timestamp", referencedColumnName = "endTimestamp")
        })
        //actionCatalog is a private field of type ActionCatalog
        private ActionCatalog actionCatalog;

        //@Column annotation to indicate that the ActionPrimaryKey class has a column
        @Column(nullable = false)
        //actionType is a private field of type ActionTypeEnum
        @Enumerated(EnumType.ORDINAL)
        private ActionTypeEnum actionType;

        //@Column annotation to indicate that the ActionPrimaryKey class has a column
        @Column(nullable = false)
        //producerOrStorageId is a private field of type String
        private String producerOrStorageId;

        //equals method to compare two ActionPrimaryKey objects
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActionPrimaryKey that = (ActionPrimaryKey) o;
            return Objects.equals(actionCatalog.getActionCatalogPrimaryKey().getActionRequest().getActionRequestId(), that.actionCatalog.getActionCatalogPrimaryKey().getActionRequest().getActionRequestId()) &&
                    Objects.equals(actionCatalog.getActionCatalogPrimaryKey().getStartTimestamp(), that.actionCatalog.getActionCatalogPrimaryKey().getStartTimestamp()) &&
                    Objects.equals(actionCatalog.getActionCatalogPrimaryKey().getEndTimestamp(), that.actionCatalog.getActionCatalogPrimaryKey().getEndTimestamp()) &&
                    actionType == that.actionType &&
                    Objects.equals(producerOrStorageId, that.producerOrStorageId);
        }

        //hashCode method to generate a hash code for the ActionPrimaryKey class
        @Override
        public int hashCode() {
            return Objects.hash(actionCatalog.getActionCatalogPrimaryKey().getActionRequest().getActionRequestId(), actionCatalog.getActionCatalogPrimaryKey().getStartTimestamp(), actionCatalog.getActionCatalogPrimaryKey().getEndTimestamp(), actionType, producerOrStorageId);
        }
    }
}