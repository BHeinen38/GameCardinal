package coms309.RoundTrip.demo.Repository;

import coms309.RoundTrip.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);

    User findByEmail(String email);
}
