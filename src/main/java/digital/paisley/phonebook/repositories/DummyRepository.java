package digital.paisley.phonebook.repositories;

import digital.paisley.phonebook.entities.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DummyRepository  extends JpaRepository<Dummy, Long>, JpaSpecificationExecutor<Dummy> {

	String getByDummy(String dummy);

}
