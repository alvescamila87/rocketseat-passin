package rocketseat.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.dto.event.EventIdDTO;
import rocketseat.com.passin.dto.event.EventRequestDTO;
import rocketseat.com.passin.dto.event.EventResponseDTO;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendeesList = this.attendeeRepository.findByEventId(eventId);
        return new EventResponseDTO(event, attendeesList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventRequestDTO){
        Event newEvent = new Event();

        newEvent.setTitle(eventRequestDTO.title());
        newEvent.setDetails(eventRequestDTO.details());
        newEvent.setMaximumAttendees(eventRequestDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventRequestDTO.title())); // usa o método auxiliar abaixo

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    // método auxiliar
    private String createSlug(String text){
        // decomposição canônica (aplicando regex para remoção de acentos)
        // exemplo 'São Paulo', ficaria 'Sa~o Paulo'

        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized
                .replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "") // selecionar acentos e substituir por string vazia
                .replaceAll("[^\\w\\s]", "") // selecionar caracteres que não forem alfanuméricos e remover da string
                .replaceAll("\\s+","") // substituir todos os espaços vazio por hífen
                .toLowerCase();
    }

}
