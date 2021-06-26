package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("it Should Find All Visits And Check Size Of List Happy Case")
    void itShouldFindAllVisitsAndCheckSizeOfListHappyCase() {
        //given
        Visit visit = new Visit();
        Visit visit2 = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        visits.add(visit2);
        given(visitRepository.findAll()).willReturn(visits);

        //when
        Set<Visit> visitsFounded = service.findAll();

        //then
        then(visitRepository).should().findAll();
        assertEquals(2, visitsFounded.size(),"Not is a equal size of this List");
    }
    @Test
    @DisplayName("id Should Find Visit By Id Happy Case")
    void idShouldFindVisitByIdHappyCase() {
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        //when
        Visit visitFound = service.findById(anyLong());

        //then
        then(visitRepository).should().findById(anyLong());
        assertNotNull(visitFound);
    }

    @Test
    @DisplayName("it Should Save Visit Happy Case")
    void itShouldSaveVisitHappyCase() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(new Visit());

        //when
        Visit savedVisit = service.save(visit);

        //then
        then(visitRepository).should().save(any(Visit.class));
        then(visitRepository).shouldHaveNoMoreInteractions();
        assertNotNull(savedVisit);
    }


    @Test
    @DisplayName("it Should Delete By Object Happy Case")
    void itShouldDeleteByObjectHappyCase() {
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(visitRepository).should().delete(any(Visit.class));
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    @DisplayName("it Should Delete By Id Happy Case")
    void itShouldDeleteByIdHappyCase() {
        //when
        service.deleteById(anyLong());

        //then
        then(visitRepository).should().deleteById(anyLong());
        then(visitRepository).shouldHaveNoMoreInteractions();
    }
}