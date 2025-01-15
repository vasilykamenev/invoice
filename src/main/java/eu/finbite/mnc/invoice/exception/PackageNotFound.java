package eu.finbite.mnc.invoice.exception;

public class PackageNotFound extends RuntimeException {
    public PackageNotFound(String message) {
        super(message);
    }
}
