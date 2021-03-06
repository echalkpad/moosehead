package no.java.moosehead.api;

import no.java.moosehead.projections.Participant;

import java.util.List;

public class WorkshopInfo {
    private String id;
    private String title;
    private String description;
    private List<Participant> participants;
    private WorkshopStatus status;
    private long createdRevisionId;

    public WorkshopInfo(String id, String title, String description, List<Participant> participants, WorkshopStatus status,long createdRevisionId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.participants = participants;
        this.status = status;
        this.createdRevisionId = createdRevisionId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public WorkshopStatus getStatus() {
        return status;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public long getCreatedRevisionId() {
        return createdRevisionId;
    }
}
