package digital.paisley.phonebook.repositories;

import digital.paisley.phonebook.entities.Contact;
import digital.paisley.phonebook.util.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactSpecificationsBuilder {
    private final List<SearchCriteria> params;

    public ContactSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public ContactSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Contact> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs;
        specs = params.stream()
                .map(ContactSpecification::new)
                .collect(Collectors.toList());

        Specification result;
        result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
