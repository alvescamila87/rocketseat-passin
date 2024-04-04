package rocketseat.com.passin.dto.attendee;

import lombok.Getter;

import java.util.List;

//@Getter
public record AttendesListResponseDTO(List<AttendeeDetails> attendees) {
    //List<AttendeeDetails> attendees;
}
