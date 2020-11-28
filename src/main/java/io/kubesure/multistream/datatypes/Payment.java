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

/** currency payment details */
@org.apache.avro.specific.AvroGenerated
public class Payment extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7196930328199665064L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Payment\",\"namespace\":\"io.kubesure.multistream.datatypes\",\"doc\":\"currency payment details\",\"fields\":[{\"name\":\"transactionID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"transactionDate\",\"type\":\"long\",\"logicalType\":\"local-timestamp-millis\"},{\"name\":\"clientID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"status\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"account\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"amount\",\"type\":\"float\"},{\"name\":\"referenceNumber\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Payment> ENCODER =
      new BinaryMessageEncoder<Payment>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Payment> DECODER =
      new BinaryMessageDecoder<Payment>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Payment> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Payment> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Payment> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Payment>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Payment to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Payment from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Payment instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Payment fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.String transactionID;
   private long transactionDate;
   private java.lang.String clientID;
   private java.lang.String status;
   private java.lang.String account;
   private float amount;
   private java.lang.String referenceNumber;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Payment() {}

  /**
   * All-args constructor.
   * @param transactionID The new value for transactionID
   * @param transactionDate The new value for transactionDate
   * @param clientID The new value for clientID
   * @param status The new value for status
   * @param account The new value for account
   * @param amount The new value for amount
   * @param referenceNumber The new value for referenceNumber
   */
  public Payment(java.lang.String transactionID, java.lang.Long transactionDate, java.lang.String clientID, java.lang.String status, java.lang.String account, java.lang.Float amount, java.lang.String referenceNumber) {
    this.transactionID = transactionID;
    this.transactionDate = transactionDate;
    this.clientID = clientID;
    this.status = status;
    this.account = account;
    this.amount = amount;
    this.referenceNumber = referenceNumber;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return transactionID;
    case 1: return transactionDate;
    case 2: return clientID;
    case 3: return status;
    case 4: return account;
    case 5: return amount;
    case 6: return referenceNumber;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: transactionID = (java.lang.String)value$; break;
    case 1: transactionDate = (java.lang.Long)value$; break;
    case 2: clientID = (java.lang.String)value$; break;
    case 3: status = (java.lang.String)value$; break;
    case 4: account = (java.lang.String)value$; break;
    case 5: amount = (java.lang.Float)value$; break;
    case 6: referenceNumber = (java.lang.String)value$; break;
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
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public java.lang.String getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.String value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'account' field.
   * @return The value of the 'account' field.
   */
  public java.lang.String getAccount() {
    return account;
  }


  /**
   * Sets the value of the 'account' field.
   * @param value the value to set.
   */
  public void setAccount(java.lang.String value) {
    this.account = value;
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public float getAmount() {
    return amount;
  }


  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(float value) {
    this.amount = value;
  }

  /**
   * Gets the value of the 'referenceNumber' field.
   * @return The value of the 'referenceNumber' field.
   */
  public java.lang.String getReferenceNumber() {
    return referenceNumber;
  }


  /**
   * Sets the value of the 'referenceNumber' field.
   * @param value the value to set.
   */
  public void setReferenceNumber(java.lang.String value) {
    this.referenceNumber = value;
  }

  /**
   * Creates a new Payment RecordBuilder.
   * @return A new Payment RecordBuilder
   */
  public static io.kubesure.multistream.datatypes.Payment.Builder newBuilder() {
    return new io.kubesure.multistream.datatypes.Payment.Builder();
  }

  /**
   * Creates a new Payment RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Payment RecordBuilder
   */
  public static io.kubesure.multistream.datatypes.Payment.Builder newBuilder(io.kubesure.multistream.datatypes.Payment.Builder other) {
    if (other == null) {
      return new io.kubesure.multistream.datatypes.Payment.Builder();
    } else {
      return new io.kubesure.multistream.datatypes.Payment.Builder(other);
    }
  }

  /**
   * Creates a new Payment RecordBuilder by copying an existing Payment instance.
   * @param other The existing instance to copy.
   * @return A new Payment RecordBuilder
   */
  public static io.kubesure.multistream.datatypes.Payment.Builder newBuilder(io.kubesure.multistream.datatypes.Payment other) {
    if (other == null) {
      return new io.kubesure.multistream.datatypes.Payment.Builder();
    } else {
      return new io.kubesure.multistream.datatypes.Payment.Builder(other);
    }
  }

  /**
   * RecordBuilder for Payment instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Payment>
    implements org.apache.avro.data.RecordBuilder<Payment> {

    private java.lang.String transactionID;
    private long transactionDate;
    private java.lang.String clientID;
    private java.lang.String status;
    private java.lang.String account;
    private float amount;
    private java.lang.String referenceNumber;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.kubesure.multistream.datatypes.Payment.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.transactionID)) {
        this.transactionID = data().deepCopy(fields()[0].schema(), other.transactionID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.transactionDate)) {
        this.transactionDate = data().deepCopy(fields()[1].schema(), other.transactionDate);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.clientID)) {
        this.clientID = data().deepCopy(fields()[2].schema(), other.clientID);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.status)) {
        this.status = data().deepCopy(fields()[3].schema(), other.status);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.account)) {
        this.account = data().deepCopy(fields()[4].schema(), other.account);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.amount)) {
        this.amount = data().deepCopy(fields()[5].schema(), other.amount);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.referenceNumber)) {
        this.referenceNumber = data().deepCopy(fields()[6].schema(), other.referenceNumber);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing Payment instance
     * @param other The existing instance to copy.
     */
    private Builder(io.kubesure.multistream.datatypes.Payment other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.transactionID)) {
        this.transactionID = data().deepCopy(fields()[0].schema(), other.transactionID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.transactionDate)) {
        this.transactionDate = data().deepCopy(fields()[1].schema(), other.transactionDate);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.clientID)) {
        this.clientID = data().deepCopy(fields()[2].schema(), other.clientID);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.status)) {
        this.status = data().deepCopy(fields()[3].schema(), other.status);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.account)) {
        this.account = data().deepCopy(fields()[4].schema(), other.account);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.amount)) {
        this.amount = data().deepCopy(fields()[5].schema(), other.amount);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.referenceNumber)) {
        this.referenceNumber = data().deepCopy(fields()[6].schema(), other.referenceNumber);
        fieldSetFlags()[6] = true;
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
    public io.kubesure.multistream.datatypes.Payment.Builder setTransactionID(java.lang.String value) {
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
    public io.kubesure.multistream.datatypes.Payment.Builder clearTransactionID() {
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
    public io.kubesure.multistream.datatypes.Payment.Builder setTransactionDate(long value) {
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
    public io.kubesure.multistream.datatypes.Payment.Builder clearTransactionDate() {
      fieldSetFlags()[1] = false;
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
    public io.kubesure.multistream.datatypes.Payment.Builder setClientID(java.lang.String value) {
      validate(fields()[2], value);
      this.clientID = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'clientID' field has been set.
      * @return True if the 'clientID' field has been set, false otherwise.
      */
    public boolean hasClientID() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'clientID' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder clearClientID() {
      clientID = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.String getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder setStatus(java.lang.String value) {
      validate(fields()[3], value);
      this.status = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder clearStatus() {
      status = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'account' field.
      * @return The value.
      */
    public java.lang.String getAccount() {
      return account;
    }


    /**
      * Sets the value of the 'account' field.
      * @param value The value of 'account'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder setAccount(java.lang.String value) {
      validate(fields()[4], value);
      this.account = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'account' field has been set.
      * @return True if the 'account' field has been set, false otherwise.
      */
    public boolean hasAccount() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'account' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder clearAccount() {
      account = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public float getAmount() {
      return amount;
    }


    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder setAmount(float value) {
      validate(fields()[5], value);
      this.amount = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder clearAmount() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'referenceNumber' field.
      * @return The value.
      */
    public java.lang.String getReferenceNumber() {
      return referenceNumber;
    }


    /**
      * Sets the value of the 'referenceNumber' field.
      * @param value The value of 'referenceNumber'.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder setReferenceNumber(java.lang.String value) {
      validate(fields()[6], value);
      this.referenceNumber = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'referenceNumber' field has been set.
      * @return True if the 'referenceNumber' field has been set, false otherwise.
      */
    public boolean hasReferenceNumber() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'referenceNumber' field.
      * @return This builder.
      */
    public io.kubesure.multistream.datatypes.Payment.Builder clearReferenceNumber() {
      referenceNumber = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Payment build() {
      try {
        Payment record = new Payment();
        record.transactionID = fieldSetFlags()[0] ? this.transactionID : (java.lang.String) defaultValue(fields()[0]);
        record.transactionDate = fieldSetFlags()[1] ? this.transactionDate : (java.lang.Long) defaultValue(fields()[1]);
        record.clientID = fieldSetFlags()[2] ? this.clientID : (java.lang.String) defaultValue(fields()[2]);
        record.status = fieldSetFlags()[3] ? this.status : (java.lang.String) defaultValue(fields()[3]);
        record.account = fieldSetFlags()[4] ? this.account : (java.lang.String) defaultValue(fields()[4]);
        record.amount = fieldSetFlags()[5] ? this.amount : (java.lang.Float) defaultValue(fields()[5]);
        record.referenceNumber = fieldSetFlags()[6] ? this.referenceNumber : (java.lang.String) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Payment>
    WRITER$ = (org.apache.avro.io.DatumWriter<Payment>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Payment>
    READER$ = (org.apache.avro.io.DatumReader<Payment>)MODEL$.createDatumReader(SCHEMA$);

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

    out.writeString(this.clientID);

    out.writeString(this.status);

    out.writeString(this.account);

    out.writeFloat(this.amount);

    out.writeString(this.referenceNumber);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.transactionID = in.readString();

      this.transactionDate = in.readLong();

      this.clientID = in.readString();

      this.status = in.readString();

      this.account = in.readString();

      this.amount = in.readFloat();

      this.referenceNumber = in.readString();

    } else {
      for (int i = 0; i < 7; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.transactionID = in.readString();
          break;

        case 1:
          this.transactionDate = in.readLong();
          break;

        case 2:
          this.clientID = in.readString();
          break;

        case 3:
          this.status = in.readString();
          break;

        case 4:
          this.account = in.readString();
          break;

        case 5:
          this.amount = in.readFloat();
          break;

        case 6:
          this.referenceNumber = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










