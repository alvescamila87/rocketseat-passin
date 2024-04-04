package rocketseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocketseat.com.passin.domain.checkin.CheckIn;

import java.util.Optional;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
