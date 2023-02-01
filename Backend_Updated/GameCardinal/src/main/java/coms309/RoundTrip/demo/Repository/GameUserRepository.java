package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameUserRepository extends JpaRepository<GameUser, Integer>
{
}
