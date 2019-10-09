package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Itinerary;
import seedu.address.model.ReadOnlyItinerary;

/**
 * Represents a storage for {@link Itinerary}.
 */
public interface ItineraryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns Itinerary data as a {@link ReadOnlyItinerary}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItinerary> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyItinerary> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyItinerary} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyItinerary addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyItinerary)
     */
    void saveAddressBook(ReadOnlyItinerary addressBook, Path filePath) throws IOException;

}