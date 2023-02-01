package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>
{

}
