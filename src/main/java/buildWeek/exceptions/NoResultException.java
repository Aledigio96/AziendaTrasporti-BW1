package buildWeek.exceptions;

public class NoResultException extends RuntimeException {
  public NoResultException(String message) {
    super("Non sono stati trovati risultati per la query: " + message);
  }
}
