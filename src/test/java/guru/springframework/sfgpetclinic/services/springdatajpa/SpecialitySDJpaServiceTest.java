package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void findByIdTest() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(anyLong())).willReturn(Optional.of(speciality));

        //when
        Speciality specialityFound = service.findById(anyLong());

        //then
        assertNotNull(specialityFound);
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void findByIdBddTest() {
        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(anyLong())).willReturn(Optional.of(speciality));

        //when
        Speciality specialityFound = service.findById(1L);

        //then
        assertNotNull(specialityFound);
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        //given
        //with in setup

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByObject() {
        //given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrow() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

        assertThrows(RuntimeException.class, () -> service.findById(1L));

        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void itShouldDoesThrowWhenDeleteByObject() {
        //given
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

        //when
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }
}