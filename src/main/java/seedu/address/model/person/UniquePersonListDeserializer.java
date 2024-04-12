package seedu.address.model.person;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Implements a custom deserializer for UniquePersonList,
 * as Jackson does not support JavaFX collections.
 */
public class UniquePersonListDeserializer extends StdDeserializer<UniquePersonList> {

    public UniquePersonListDeserializer() {
        this(null);
    }

    public UniquePersonListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UniquePersonList deserialize(JsonParser parser,
                              DeserializationContext context) throws IOException {
        // read the JSON content and deserialize it
        List<Person> persons = context.readValue(parser,
                context.getTypeFactory().constructCollectionType(List.class, Person.class));

        // initialize and update the UniquePersonList
        UniquePersonList upl = new UniquePersonList();
        upl.setPersons(persons);
        return upl;
    }

}
