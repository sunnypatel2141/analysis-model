package edu.hm.hafner.analysis;

import java.util.Arrays;
import java.util.List;

/**
 * Provides convenient methods to determine the package or namespace names of a file.
 *
 * @author Ullrich Hafner
 */
public final class PackageDetectors {
    /** If no package could be assigned this value is used as package name. */
    public static final String UNDEFINED_PACKAGE = "-";

    private static final List<AbstractPackageDetector> DETECTORS = Arrays.asList(
            new JavaPackageDetector(), new CsharpNamespaceDetector());

    /**
     * Detects the package name of the specified file based on several detector strategies.
     *
     * @param fileName
     *         the filename of the file to scan
     *
     * @return the package name or an empty string
     */
    public static String detectPackageName(final String fileName) {
        for (AbstractPackageDetector detector : DETECTORS) {
            if (detector.accepts(fileName)) {
                return detector.detectPackageName(fileName);
            }
        }
        return UNDEFINED_PACKAGE;
    }

    private PackageDetectors() {
        // prevents instantiation
    }
}

