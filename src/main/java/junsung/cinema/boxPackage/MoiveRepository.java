package junsung.cinema.boxPackage;

import junsung.cinema.boxPackage.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoiveRepository extends JpaRepository<Movie,Long> {
}
