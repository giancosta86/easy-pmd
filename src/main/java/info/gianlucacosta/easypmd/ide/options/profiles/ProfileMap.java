package info.gianlucacosta.easypmd.ide.options.profiles;

import info.gianlucacosta.helios.beans.events.TriggerListener;

import java.util.Collection;

/**
 * A dedicated map-like object for enumerating profiles.
 */
public interface ProfileMap {

    Collection<String> getProfileNames();

    void setProfile(String profileName, Profile profile) throws ProfileException;

    boolean profileNameExists(String profileName);

    Profile getProfile(String profileName);

    void duplicateProfile(String sourceName, String targetName) throws ProfileException;

    void renameProfile(String oldName, String newName) throws ProfileException;

    void removeProfile(String name) throws ProfileException;

    void addProfileNamesChangedListener(TriggerListener listener);

    void removeProfileNamesChangedListener(TriggerListener listener);
}
