/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.kubesure.multistream.datatypes;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** currency purchase details */
@org.apache.avro.specific.AvroGenerated
public class Purchase extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1183716479619749094L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Purchase\",\"namespace\":\"io.kubesure.multistream.datatypes\",\"doc\":\"currency purchase details\",\"fields\":[{\"name\":\"transactionID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"transactionDate\",\"type\":\"long\",\"logicalType\":\"local-timestamp-millis\"},{\"name\":\"purchaseCurrency\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"saleCurrency\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"purchaseAmount\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"saleAmount\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"rate\",\"type\":\"float\"},{\"name\":\"rateCode\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"buySell\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"clientID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"channel\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Purchase> ENCODER =
      new BinaryMessageEncoder<Purchase>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Purchase> DECODER =
      new BinaryMessageDecoder<Purchase>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Purchase> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Purchase> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Purchase> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Purchase>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Purchase to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Purchase from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Purchase instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Purchase fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.String transactionID;
   private long transactionDate;
   private java.lang.String purchaseCurrency;
   private java.lang.String saleCurrency;
   private java.lang.String purchaseAmount;
   private java.lang.String saleAmount;
   private float rate;
   private java.lang.String rateCode;
   private java.lang.String buySell;
   private java.lang.String clientID;
   private java.lang.String channel;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Purchase() {}

  /**
   * All-args constructor.
   * @param transactionID The new value for transactionID
   * @param transactionDate The new value for transactionDate
   * @param purchaseCurrency The new value for purchaseCurrency
   * @param saleCurrency The new value for saleCurrency
   * @param purchaseAmount The new value for purchaseAmount
   * @param saleAmount The new value for saleAmount
   * @param rate The new value for rate
   * @param rateCode The new value for rateCode
   * @param buySell The new value for buySell
   * @param clientID The new value for clientID
   * @param channel The new value for channel
   */
  public Purchase(java.lang.String transactionID, java.lang.Long transactionDate, java.lang.String purchaseCurrency, java.lang.String saleCurrency, java.lang.String purchaseAmount, java.lang.String saleAmount, java.lang.Float rate, java.lang.String rateCode, java.lang.String buySell, java.lang.String clientID, java.lang.String channel) {
    this.transactionID = transactionID;
    this.transactionDate = transactionDate;
    this.purchaseCurrency = purchaseCurrency;
    this.saleCurrency = saleCurrency;
    this.purchaseAmount = purchaseAmount;
    this.saleAmount = saleAmount;
    this.rate = rate;
    this.rateCode = rateCode;
    this.buySell = buySell;
    this.clientID = clientID;
    this.channel = channel;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return transactionID;
    case 1: return transactionDate;
    case 2: return purchaseCurrency;
    case 3: return saleCurrency;
    case 4: return purchaseAmount;
    case 5: return saleAmount;
    case 6: return rate;
    case 7: return rateCode;
    case 8: return buySell;
    case 9: return clientID;
    case 10: return channel;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: transactionID = (java.lang.String)value$; break;
    case 1: transactionDate = (java.lang.Long)value$; break;
    case 2: purchaseCurrency = (java.lang.String)value$; break;
    case 3: saleCurrency = (java.lang.String)value$; break;
    case 4: purchaseAmount = (java.lang.String)value$; break;
    case 5: saleAmount = (java.lang.String)value$; break;
    case 6: rate = (java.lang.Float)value$; break;
    case 7: rateCode = (java.lang.String)value$; break;
    case 8: buySell = (java.lang.String)value$; break;
    case 9: clientID = (java.lang.String)value$; break;
    case 10: channel = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'transactionID' field.
   * @return The value of the 'transactionID' field.
   */
  public java.lang.String getTransactionID() {
    return transactionID;
  }


  /**
   * Sets the value of the 'transactionID' field.
   * @param value the value to set.
   */
  public void setTransactionID(java.lang.String value) {
    this.transactionID = value;
  }

  /**
   * Gets the value of the 'transactionDate' field.
   * @return The value of the 'transactionDate' field.
   */
  public long getTransactionDate() {
    return transactionDate;
  }


  /**
   * Sets the value of the 'transactionDate' field.
   * @param value the value to set.
   */
  public void setTransactionDate(long value) {
    this.transactionDate = value;
  }

  /**
   * Gets the value of the 'purchaseCurrency' field.
   * @return The value of the 'purchaseCurrency' field.
   */
  public java.lang.String getPurchaseCurrency() {
    return purchaseCurrency;
  }


  /**
   * Sets the value of the 'purchaseCurrency' field.
   * @param value the value to set.
   */
  public void setPurchaseCurrency(java.lang.String value) {
    this.purchaseCurrency = value;
  }

  /**
   * Gets the value of the 'saleCurrency' field.
   * @return The value of the 'saleCurrency' field.
   */
  public java.lang.String getSaleCurrency() {
    return saleCurrency;
  }


  /**
   * Sets the value of the 'saleCurrency' field.
   * @param value the value to set.
   */
  public void setSaleCurrency(java.lang.String value) {
    this.saleCurrency = value;
  }

  /**
   * Gets the value of the 'purchaseAmount' field.
   * @return The value of the 'purchaseAmount' field.
   */
  public java.lang.String getPurchaseAmount() {
    return purchaseAmount;
  }


  /**
   * Sets the value of the 'purchaseAmount' field.
   * @param value the value to set.
   */
  public void setPurchaseAmount(java.lang.String value) {
    this.purchaseAmount = value;
  }

  /**
   * Gets the value of the 'saleAmount' field.
   * @return The value of the 'saleAmount' field.
   */
  public java.lang.String getSaleAmount() {
    return saleAmount;
  }


  /**
   * Sets the value of the 'saleAmount' field.
   * @param value the value to set.
   */
  public void setSaleAmount(java.lang.String value) {
    this.saleAmount = value;
  }

  /**
   * Gets the value of the 'rate' field.
   * @return The value of the 'rate' field.
   */
  public float getRate() {
    return rate;
  }


  /**
   * Sets the value of the 'rate' field.
   * @param value the value to set.
   */
  public void setRate(float value) {
    this.rate = value;
  }

  /**
   * Gets the value of the 'rateCode' field.
   * @return The value of the 'rateCode' field.
   */
  public java.lang.String getRateCode() {
    return rateCode;
  }


  /**
   * Sets the value of the 'rateCode' field.
   * @param value the value to set.
   */
  public void setRateCode(java.lang.String value) {
    this.rateCode = value;
  }

  /**
   * Gets the value of the 'buySell' field.
   * @return The value of the 'buySell' field.
   */
  public java.lang.String getBuySell() {
    return buySell;
  }


  /**
   * Sets the value of the 'buySell' field.
   * @param value the value to set.
   */
  public void setBuySell(java.lang.String value) {
    this.buySell = value;
  }

  /**
   * Gets the value of the 'clientID' field.
   * @return The value of the 'clientID' field.
   */
  public java.lang.String getClientID() {
    return clientID;
  }


  /**
   * Sets the value of the 'clientID' field.
   * @param value the value to set.
   */
  public void setClientID(java.lang.String value) {
    this.clientID = value;
  }

  /**
   * Gets the value of the 'channel' field.
   * @return The value of the 'channel' field.
   */
  public java.lang.String getChannel() {
    return channel;
  }


  /**
   * Sets the value of the 'channel' field.
   * @param value the value to set.
   */
  public void setChannel(java.lang.String value) {
    this.channel = value;
  }

  /**
   * Creates a new Purchase RecordBuilder.
   * @return A new Purchase RecordBuilder
   */
  public static io.kubesure.multistream.datatypes.Purchase.Builder newBuilder() {
    return new io.kubesure.multistream.datatypes.Purchase.Builder();
  }

  /**
   * Creates a new Purchase RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Purchase RecordBuilder
   */
  public static io.kubesure.multistream.datatypes.Purchase.Builder newBuilder(io.kubesure.multistream.datatypes.Purchase.Builder other) {
    if (other == null) {
      return new io.kubesure.multistream.datatypes.Purchase.Builder();
    } else {
      return new io.kubesure.multistream.datatypes.Purchase.Builder(other);
    }
  }

  /**
   * Creates a new Purchase RecordBuilder by copying an existing Purchase instance.
   * @param other The existing instance to copy.
   * @return A new Purchase RecordBuilder
   */
  public static io.kubesure.multistream.datatypes.Purchase.Builder newBuilder(io.kubesure.multistream.datatypes.Purchase other) {
    if (other == null) {
      return new io.kubesure.multistream.datatypes.Purchase.Builder();
    } else {
      return new io.kubesure.multistream.datatypes.Purchase.Builder(other);
    }
  }

  /**
   * RecordBuilder for Purchase instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Purchase>
    implements org.apache.avro.data.RecordBuilder<Purchase> {

    private java.lang.String transactionID;
    private long transactionDate;
    private java.lang.String purchaseCurrency;
    private java.lang.String saleCurrency;
    private java.lang.String purchaseAmount;
    private java.lang.String saleAmount;
    private float rate;
    private java.lang.String rateCode;
    private java.lang.String buySell;
    private java.lang.String clientID;
    private java.lang.String channel;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.kubesure.multistream.datatypes.Purchase.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.transactionID)) {
        this.transactionID = data().deepCopy(fields()[0].schema(), other.transactionID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.transactionDate)) {
        this.transactionDate = data().deepCopy(fields()[1].schema(), other.transactionDate);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.purchaseCurrency)) {
        this.purchaseCurrency = data().deepCopy(fields()[2].schema(), other.purchaseCurrency);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.saleCurrency)) {
        this.saleCurrency = data().deepCopy(fields()[3].schema(), other.saleCurrency);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.purchaseAmount)) {
        this.purchaseAmount = data().deepCopy(fields()[4].schema(), other.purchaseAmount);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.saleAmount)) {
        this.saleAmount = data().deepCopy(fields()[5].schema(), other.saleAmount);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.rate)) {
        this.rate = data().deepCopy(fields()[6].schema(), other.rate);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.rateCode)) {
        this.rateCode = data().deepCopy(fields()[7].schema(), other.rateCode);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.buySell)) {
        this.buySell = data().deepCopy(fields()[8].schema(), other.buySell);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
      if (isValidValue(fields()[9], other.clientID)) {
        this.clientID = data().deepCopy(fields()[9].schema(), other.clientID);
        fieldSetFlags()[9] = other.fieldSetFlags()[9];
      }
      if (isValidValue(fields()[10], other.channel)) {
        this.channel = data().deepCopy(fields()[10].schema(), other.channel);
        fieldSetFlags()[10] = other.fieldSetFlags()[10];
      }
    }

    /**
     * Creates a Builder by copying an existing Purchase instance
     * @param other The existing instance to copy.
     */
    private Builder(io.kubesure.multistream.datatypes.Purchase other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.transactionID)) {
        this.transactionID = data().deepCopy(fields()[0].schema(), other.transactionID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.transactionDate)) {
        this.transactionDate = data().deepCopy(fields()[1].schema(), other.transactionDate);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.purchaseCurrency)) {
        this.purchaseCurrency = data().deepCopy(fields()[2].schema(), other.purchaseCurrency);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.saleCurrency)) {
        this.saleCurrency = data().deepCopy(fields()[3].schema(), other.saleCurrency);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.purchaseAmount)) {
        this.purchaseAmount = data().deepCopy(fields()[4].schema(), other.purchaseAmount);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.saleAmount)) {
        this.saleAmount = data().deepCopy(fields()[5].schema(), other.saleAmount);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.rate)) {
        this.rate = data().deepCopy(fields()[6].schema(), other.rate);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.rateCode)) {
        this.rateCode = data().deepCopy(fields()[7].schema(), other.rateCode);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.buySell)) {
        this.buySell = data().deepCopy(fields()[8].schema(), other.buySell);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.clientID)) {
        this.clientID = data().deepCopy(fields()[9].schema(), other.clientID);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.channel)) {
        this.channel = data().deepCopy(fields()[10].schema(), other.channel);
        fieldSetFlags()[10] = true;
      }
    }

    /**
      * Gets the value of the 'transactionID' field.
      * @return The value.
      */
    public java.lang.String getTransactionID() {
      return transactionID;
    }


    /**
      * Sets the value of the 'transactionID' field.
      * @param value The value of 'transactionID'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setTransactionID(java.lang.String value) {
      validate(fields()[0], value);
      this.transactionID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'transactionID' field has been set.
      * @return True if the 'transactionID' field has been set, false otherwise.
      */
    public boolean hasTransactionID() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'transactionID' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearTransactionID() {
      transactionID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'transactionDate' field.
      * @return The value.
      */
    public long getTransactionDate() {
      return transactionDate;
    }


    /**
      * Sets the value of the 'transactionDate' field.
      * @param value The value of 'transactionDate'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setTransactionDate(long value) {
      validate(fields()[1], value);
      this.transactionDate = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'transactionDate' field has been set.
      * @return True if the 'transactionDate' field has been set, false otherwise.
      */
    public boolean hasTransactionDate() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'transactionDate' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearTransactionDate() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'purchaseCurrency' field.
      * @return The value.
      */
    public java.lang.String getPurchaseCurrency() {
      return purchaseCurrency;
    }


    /**
      * Sets the value of the 'purchaseCurrency' field.
      * @param value The value of 'purchaseCurrency'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setPurchaseCurrency(java.lang.String value) {
      validate(fields()[2], value);
      this.purchaseCurrency = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'purchaseCurrency' field has been set.
      * @return True if the 'purchaseCurrency' field has been set, false otherwise.
      */
    public boolean hasPurchaseCurrency() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'purchaseCurrency' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearPurchaseCurrency() {
      purchaseCurrency = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'saleCurrency' field.
      * @return The value.
      */
    public java.lang.String getSaleCurrency() {
      return saleCurrency;
    }


    /**
      * Sets the value of the 'saleCurrency' field.
      * @param value The value of 'saleCurrency'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setSaleCurrency(java.lang.String value) {
      validate(fields()[3], value);
      this.saleCurrency = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'saleCurrency' field has been set.
      * @return True if the 'saleCurrency' field has been set, false otherwise.
      */
    public boolean hasSaleCurrency() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'saleCurrency' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearSaleCurrency() {
      saleCurrency = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'purchaseAmount' field.
      * @return The value.
      */
    public java.lang.String getPurchaseAmount() {
      return purchaseAmount;
    }


    /**
      * Sets the value of the 'purchaseAmount' field.
      * @param value The value of 'purchaseAmount'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setPurchaseAmount(java.lang.String value) {
      validate(fields()[4], value);
      this.purchaseAmount = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'purchaseAmount' field has been set.
      * @return True if the 'purchaseAmount' field has been set, false otherwise.
      */
    public boolean hasPurchaseAmount() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'purchaseAmount' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearPurchaseAmount() {
      purchaseAmount = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'saleAmount' field.
      * @return The value.
      */
    public java.lang.String getSaleAmount() {
      return saleAmount;
    }


    /**
      * Sets the value of the 'saleAmount' field.
      * @param value The value of 'saleAmount'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setSaleAmount(java.lang.String value) {
      validate(fields()[5], value);
      this.saleAmount = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'saleAmount' field has been set.
      * @return True if the 'saleAmount' field has been set, false otherwise.
      */
    public boolean hasSaleAmount() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'saleAmount' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearSaleAmount() {
      saleAmount = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'rate' field.
      * @return The value.
      */
    public float getRate() {
      return rate;
    }


    /**
      * Sets the value of the 'rate' field.
      * @param value The value of 'rate'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setRate(float value) {
      validate(fields()[6], value);
      this.rate = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'rate' field has been set.
      * @return True if the 'rate' field has been set, false otherwise.
      */
    public boolean hasRate() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'rate' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearRate() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'rateCode' field.
      * @return The value.
      */
    public java.lang.String getRateCode() {
      return rateCode;
    }


    /**
      * Sets the value of the 'rateCode' field.
      * @param value The value of 'rateCode'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setRateCode(java.lang.String value) {
      validate(fields()[7], value);
      this.rateCode = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'rateCode' field has been set.
      * @return True if the 'rateCode' field has been set, false otherwise.
      */
    public boolean hasRateCode() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'rateCode' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearRateCode() {
      rateCode = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'buySell' field.
      * @return The value.
      */
    public java.lang.String getBuySell() {
      return buySell;
    }


    /**
      * Sets the value of the 'buySell' field.
      * @param value The value of 'buySell'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setBuySell(java.lang.String value) {
      validate(fields()[8], value);
      this.buySell = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'buySell' field has been set.
      * @return True if the 'buySell' field has been set, false otherwise.
      */
    public boolean hasBuySell() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'buySell' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearBuySell() {
      buySell = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'clientID' field.
      * @return The value.
      */
    public java.lang.String getClientID() {
      return clientID;
    }


    /**
      * Sets the value of the 'clientID' field.
      * @param value The value of 'clientID'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setClientID(java.lang.String value) {
      validate(fields()[9], value);
      this.clientID = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'clientID' field has been set.
      * @return True if the 'clientID' field has been set, false otherwise.
      */
    public boolean hasClientID() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'clientID' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearClientID() {
      clientID = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'channel' field.
      * @return The value.
      */
    public java.lang.String getChannel() {
      return channel;
    }


    /**
      * Sets the value of the 'channel' field.
      * @param value The value of 'channel'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder setChannel(java.lang.String value) {
      validate(fields()[10], value);
      this.channel = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'channel' field has been set.
      * @return True if the 'channel' field has been set, false otherwise.
      */
    public boolean hasChannel() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'channel' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Purchase.Builder clearChannel() {
      channel = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Purchase build() {
      try {
        Purchase record = new Purchase();
        record.transactionID = fieldSetFlags()[0] ? this.transactionID : (java.lang.String) defaultValue(fields()[0]);
        record.transactionDate = fieldSetFlags()[1] ? this.transactionDate : (java.lang.Long) defaultValue(fields()[1]);
        record.purchaseCurrency = fieldSetFlags()[2] ? this.purchaseCurrency : (java.lang.String) defaultValue(fields()[2]);
        record.saleCurrency = fieldSetFlags()[3] ? this.saleCurrency : (java.lang.String) defaultValue(fields()[3]);
        record.purchaseAmount = fieldSetFlags()[4] ? this.purchaseAmount : (java.lang.String) defaultValue(fields()[4]);
        record.saleAmount = fieldSetFlags()[5] ? this.saleAmount : (java.lang.String) defaultValue(fields()[5]);
        record.rate = fieldSetFlags()[6] ? this.rate : (java.lang.Float) defaultValue(fields()[6]);
        record.rateCode = fieldSetFlags()[7] ? this.rateCode : (java.lang.String) defaultValue(fields()[7]);
        record.buySell = fieldSetFlags()[8] ? this.buySell : (java.lang.String) defaultValue(fields()[8]);
        record.clientID = fieldSetFlags()[9] ? this.clientID : (java.lang.String) defaultValue(fields()[9]);
        record.channel = fieldSetFlags()[10] ? this.channel : (java.lang.String) defaultValue(fields()[10]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Purchase>
    WRITER$ = (org.apache.avro.io.DatumWriter<Purchase>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Purchase>
    READER$ = (org.apache.avro.io.DatumReader<Purchase>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.transactionID);

    out.writeLong(this.transactionDate);

    out.writeString(this.purchaseCurrency);

    out.writeString(this.saleCurrency);

    out.writeString(this.purchaseAmount);

    out.writeString(this.saleAmount);

    out.writeFloat(this.rate);

    out.writeString(this.rateCode);

    out.writeString(this.buySell);

    out.writeString(this.clientID);

    out.writeString(this.channel);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.transactionID = in.readString();

      this.transactionDate = in.readLong();

      this.purchaseCurrency = in.readString();

      this.saleCurrency = in.readString();

      this.purchaseAmount = in.readString();

      this.saleAmount = in.readString();

      this.rate = in.readFloat();

      this.rateCode = in.readString();

      this.buySell = in.readString();

      this.clientID = in.readString();

      this.channel = in.readString();

    } else {
      for (int i = 0; i < 11; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.transactionID = in.readString();
          break;

        case 1:
          this.transactionDate = in.readLong();
          break;

        case 2:
          this.purchaseCurrency = in.readString();
          break;

        case 3:
          this.saleCurrency = in.readString();
          break;

        case 4:
          this.purchaseAmount = in.readString();
          break;

        case 5:
          this.saleAmount = in.readString();
          break;

        case 6:
          this.rate = in.readFloat();
          break;

        case 7:
          this.rateCode = in.readString();
          break;

        case 8:
          this.buySell = in.readString();
          break;

        case 9:
          this.clientID = in.readString();
          break;

        case 10:
          this.channel = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










