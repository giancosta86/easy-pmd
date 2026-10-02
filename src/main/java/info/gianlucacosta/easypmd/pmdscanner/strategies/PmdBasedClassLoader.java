package info.gianlucacosta.easypmd.pmdscanner.strategies;

import net.sourceforge.pmd.PMD;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;

/**
 * ClassLoader that firstly tries to load classes and resources using PMD's
 * class loader as its parent
 */
class PmdBasedClassLoader extends URLClassLoader {

    public static PmdBasedClassLoader create(Collection<URL> additionalUrls) {
        ClassLoader pmdClassLoader = PMD.class.getClassLoader();

        return new PmdBasedClassLoader(
                additionalUrls
                        .stream()
                        .toArray(URL[]::new),
                pmdClassLoader
        );
    }

    private PmdBasedClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
