package info.gianlucacosta.easypmd.ide.options;

import java.util.function.BiConsumer;

/**
 * Provides methods to get/set options, to validate them and to monitor their
 * changes
 */
public interface OptionsService {

    void addOptionsSetListener(BiConsumer<Options, OptionsChanges> listener);

    void removeOptionsSetListener(BiConsumer<Options, OptionsChanges> listener);

    Options getOptions();

    void setOptions(Options options);

    void validateOptions(Options options) throws InvalidOptionsException;
}
