package co.edu.eam.ingesoft.products_ms.util;

/**
 * Class to map error in a unified way.
 *
 * @author caferrerb
 *
 */
public class ErrorDTO {
  /**
   * error message.
   */
  private String message;

  /**
   * error code.
   */
  private Integer code;

  public ErrorDTO(String message, Integer code) {
    super();
    this.message = message;
    this.code = code;
  }

  /**
   * @return the message
   */
  public final String getMessage() {
    return message;
  }

  /**
   * @return the code
   */
  public final Integer getCode() {
    return code;
  }
}
