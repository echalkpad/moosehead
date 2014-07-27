package no.java.moosehead.controller;

import jdk.nashorn.internal.ir.annotations.Ignore;
import no.java.moosehead.aggregate.WorkshopAggregate;
import no.java.moosehead.api.WorkshopInfo;
import no.java.moosehead.commands.AddReservationCommand;
import no.java.moosehead.eventstore.ReservationAddedByUser;
import no.java.moosehead.projections.Workshop;
import no.java.moosehead.projections.WorkshopListProjection;
import no.java.moosehead.repository.WorkshopData;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkshopControllerTest {
    @Test
    public void shouldReturnWorkshopList() throws Exception {
        SystemSetup systemSetup = mock(SystemSetup.class);
        WorkshopListProjection workshopListProjection = mock(WorkshopListProjection.class);
        WorkshopController workshopController = new WorkshopController();

        when(systemSetup.workshopListProjection()).thenReturn(workshopListProjection);
        when(workshopListProjection.getWorkshops()).thenReturn(Arrays.asList(new Workshop(new WorkshopData("one","title","description"),30)));

        SystemSetup.setSetup(systemSetup);

        List<WorkshopInfo> workshops = workshopController.workshops();

        assertThat(workshops).hasSize(1);

        WorkshopInfo workshopInfo = workshops.get(0);

        assertThat(workshopInfo.getId()).isEqualTo("one");
        assertThat(workshopInfo.getTitle()).isEqualTo("title");
        assertThat(workshopInfo.getDescription()).isEqualTo("description");
    }

    @Test
    @Ignore
    public void shouldHandleRegistration() throws Exception {
        SystemSetup systemSetup = mock(SystemSetup.class);
        WorkshopAggregate workshopAggregate = mock(WorkshopAggregate.class);
        when(systemSetup.workshopAggregate()).thenReturn(workshopAggregate);

        WorkshopController workshopController = new WorkshopController();

        workshopController.reservation("one","darth@deathstar.com","Darth Vader");

        ReservationAddedByUser rad = null;
        when(workshopAggregate.createEvent(any(AddReservationCommand.class))).thenReturn(rad);

    }
}
