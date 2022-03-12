package io.github.k4zoku.kzgraph.model.serialization;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;

public interface HumanReadableSerializer {

    void serialize(Writer writer) throws IOException;

    void deserialize(Reader reader) throws ParseException, IOException;

}
