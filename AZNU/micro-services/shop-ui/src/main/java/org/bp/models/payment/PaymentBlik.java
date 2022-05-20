/*
 * Payment service
 * Service for payment
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.bp.models.payment;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * PaymentBlik
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-28T16:06:58.840+01:00[Europe/Belgrade]")
public class PaymentBlik {
  @SerializedName("blinkCode")
  private Integer blinkCode = null;

  public PaymentBlik blinkCode(Integer blinkCode) {
    this.blinkCode = blinkCode;
    return this;
  }

   /**
   * Get blinkCode
   * @return blinkCode
  **/
  
  public Integer getBlinkCode() {
    return blinkCode;
  }

  public void setBlinkCode(Integer blinkCode) {
    this.blinkCode = blinkCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentBlik paymentBlik = (PaymentBlik) o;
    return Objects.equals(this.blinkCode, paymentBlik.blinkCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blinkCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentBlik {\n");
    
    sb.append("    blinkCode: ").append(toIndentedString(blinkCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}