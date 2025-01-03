// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bankng.proto

package bankng;

/**
 * Protobuf type {@code bankng.CreateTransactionRequest}
 */
public final class CreateTransactionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:bankng.CreateTransactionRequest)
    CreateTransactionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CreateTransactionRequest.newBuilder() to construct.
  private CreateTransactionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateTransactionRequest() {
    fromIban_ = "";
    amount_ = "";
    transactionId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CreateTransactionRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return bankng.Bankng.internal_static_bankng_CreateTransactionRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return bankng.Bankng.internal_static_bankng_CreateTransactionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            bankng.CreateTransactionRequest.class, bankng.CreateTransactionRequest.Builder.class);
  }

  private int toCase_ = 0;
  @SuppressWarnings("serial")
  private java.lang.Object to_;
  public enum ToCase
      implements com.google.protobuf.Internal.EnumLite,
          com.google.protobuf.AbstractMessage.InternalOneOfEnum {
    IBAN(2),
    NAME(3),
    TO_NOT_SET(0);
    private final int value;
    private ToCase(int value) {
      this.value = value;
    }
    /**
     * @param value The number of the enum to look for.
     * @return The enum associated with the given number.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ToCase valueOf(int value) {
      return forNumber(value);
    }

    public static ToCase forNumber(int value) {
      switch (value) {
        case 2: return IBAN;
        case 3: return NAME;
        case 0: return TO_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public ToCase
  getToCase() {
    return ToCase.forNumber(
        toCase_);
  }

  public static final int FROM_IBAN_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object fromIban_ = "";
  /**
   * <code>string from_iban = 1;</code>
   * @return The fromIban.
   */
  @java.lang.Override
  public java.lang.String getFromIban() {
    java.lang.Object ref = fromIban_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      fromIban_ = s;
      return s;
    }
  }
  /**
   * <code>string from_iban = 1;</code>
   * @return The bytes for fromIban.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getFromIbanBytes() {
    java.lang.Object ref = fromIban_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      fromIban_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int IBAN_FIELD_NUMBER = 2;
  /**
   * <code>string iban = 2;</code>
   * @return Whether the iban field is set.
   */
  public boolean hasIban() {
    return toCase_ == 2;
  }
  /**
   * <code>string iban = 2;</code>
   * @return The iban.
   */
  public java.lang.String getIban() {
    java.lang.Object ref = "";
    if (toCase_ == 2) {
      ref = to_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (toCase_ == 2) {
        to_ = s;
      }
      return s;
    }
  }
  /**
   * <code>string iban = 2;</code>
   * @return The bytes for iban.
   */
  public com.google.protobuf.ByteString
      getIbanBytes() {
    java.lang.Object ref = "";
    if (toCase_ == 2) {
      ref = to_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      if (toCase_ == 2) {
        to_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAME_FIELD_NUMBER = 3;
  /**
   * <code>string name = 3;</code>
   * @return Whether the name field is set.
   */
  public boolean hasName() {
    return toCase_ == 3;
  }
  /**
   * <code>string name = 3;</code>
   * @return The name.
   */
  public java.lang.String getName() {
    java.lang.Object ref = "";
    if (toCase_ == 3) {
      ref = to_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (toCase_ == 3) {
        to_ = s;
      }
      return s;
    }
  }
  /**
   * <code>string name = 3;</code>
   * @return The bytes for name.
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = "";
    if (toCase_ == 3) {
      ref = to_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      if (toCase_ == 3) {
        to_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AMOUNT_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object amount_ = "";
  /**
   * <code>string amount = 4;</code>
   * @return The amount.
   */
  @java.lang.Override
  public java.lang.String getAmount() {
    java.lang.Object ref = amount_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      amount_ = s;
      return s;
    }
  }
  /**
   * <code>string amount = 4;</code>
   * @return The bytes for amount.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAmountBytes() {
    java.lang.Object ref = amount_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      amount_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TRANSACTION_ID_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private volatile java.lang.Object transactionId_ = "";
  /**
   * <code>string transaction_id = 5;</code>
   * @return The transactionId.
   */
  @java.lang.Override
  public java.lang.String getTransactionId() {
    java.lang.Object ref = transactionId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      transactionId_ = s;
      return s;
    }
  }
  /**
   * <code>string transaction_id = 5;</code>
   * @return The bytes for transactionId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTransactionIdBytes() {
    java.lang.Object ref = transactionId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      transactionId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(fromIban_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, fromIban_);
    }
    if (toCase_ == 2) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, to_);
    }
    if (toCase_ == 3) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, to_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(amount_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, amount_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(transactionId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, transactionId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(fromIban_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, fromIban_);
    }
    if (toCase_ == 2) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, to_);
    }
    if (toCase_ == 3) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, to_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(amount_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, amount_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(transactionId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, transactionId_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof bankng.CreateTransactionRequest)) {
      return super.equals(obj);
    }
    bankng.CreateTransactionRequest other = (bankng.CreateTransactionRequest) obj;

    if (!getFromIban()
        .equals(other.getFromIban())) return false;
    if (!getAmount()
        .equals(other.getAmount())) return false;
    if (!getTransactionId()
        .equals(other.getTransactionId())) return false;
    if (!getToCase().equals(other.getToCase())) return false;
    switch (toCase_) {
      case 2:
        if (!getIban()
            .equals(other.getIban())) return false;
        break;
      case 3:
        if (!getName()
            .equals(other.getName())) return false;
        break;
      case 0:
      default:
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + FROM_IBAN_FIELD_NUMBER;
    hash = (53 * hash) + getFromIban().hashCode();
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getAmount().hashCode();
    hash = (37 * hash) + TRANSACTION_ID_FIELD_NUMBER;
    hash = (53 * hash) + getTransactionId().hashCode();
    switch (toCase_) {
      case 2:
        hash = (37 * hash) + IBAN_FIELD_NUMBER;
        hash = (53 * hash) + getIban().hashCode();
        break;
      case 3:
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static bankng.CreateTransactionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static bankng.CreateTransactionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static bankng.CreateTransactionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static bankng.CreateTransactionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static bankng.CreateTransactionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static bankng.CreateTransactionRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(bankng.CreateTransactionRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code bankng.CreateTransactionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:bankng.CreateTransactionRequest)
      bankng.CreateTransactionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return bankng.Bankng.internal_static_bankng_CreateTransactionRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return bankng.Bankng.internal_static_bankng_CreateTransactionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              bankng.CreateTransactionRequest.class, bankng.CreateTransactionRequest.Builder.class);
    }

    // Construct using bankng.CreateTransactionRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      fromIban_ = "";
      amount_ = "";
      transactionId_ = "";
      toCase_ = 0;
      to_ = null;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return bankng.Bankng.internal_static_bankng_CreateTransactionRequest_descriptor;
    }

    @java.lang.Override
    public bankng.CreateTransactionRequest getDefaultInstanceForType() {
      return bankng.CreateTransactionRequest.getDefaultInstance();
    }

    @java.lang.Override
    public bankng.CreateTransactionRequest build() {
      bankng.CreateTransactionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public bankng.CreateTransactionRequest buildPartial() {
      bankng.CreateTransactionRequest result = new bankng.CreateTransactionRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      buildPartialOneofs(result);
      onBuilt();
      return result;
    }

    private void buildPartial0(bankng.CreateTransactionRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.fromIban_ = fromIban_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.amount_ = amount_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.transactionId_ = transactionId_;
      }
    }

    private void buildPartialOneofs(bankng.CreateTransactionRequest result) {
      result.toCase_ = toCase_;
      result.to_ = this.to_;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof bankng.CreateTransactionRequest) {
        return mergeFrom((bankng.CreateTransactionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(bankng.CreateTransactionRequest other) {
      if (other == bankng.CreateTransactionRequest.getDefaultInstance()) return this;
      if (!other.getFromIban().isEmpty()) {
        fromIban_ = other.fromIban_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getAmount().isEmpty()) {
        amount_ = other.amount_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      if (!other.getTransactionId().isEmpty()) {
        transactionId_ = other.transactionId_;
        bitField0_ |= 0x00000010;
        onChanged();
      }
      switch (other.getToCase()) {
        case IBAN: {
          toCase_ = 2;
          to_ = other.to_;
          onChanged();
          break;
        }
        case NAME: {
          toCase_ = 3;
          to_ = other.to_;
          onChanged();
          break;
        }
        case TO_NOT_SET: {
          break;
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              fromIban_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();
              toCase_ = 2;
              to_ = s;
              break;
            } // case 18
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();
              toCase_ = 3;
              to_ = s;
              break;
            } // case 26
            case 34: {
              amount_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            case 42: {
              transactionId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000010;
              break;
            } // case 42
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int toCase_ = 0;
    private java.lang.Object to_;
    public ToCase
        getToCase() {
      return ToCase.forNumber(
          toCase_);
    }

    public Builder clearTo() {
      toCase_ = 0;
      to_ = null;
      onChanged();
      return this;
    }

    private int bitField0_;

    private java.lang.Object fromIban_ = "";
    /**
     * <code>string from_iban = 1;</code>
     * @return The fromIban.
     */
    public java.lang.String getFromIban() {
      java.lang.Object ref = fromIban_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        fromIban_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string from_iban = 1;</code>
     * @return The bytes for fromIban.
     */
    public com.google.protobuf.ByteString
        getFromIbanBytes() {
      java.lang.Object ref = fromIban_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromIban_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string from_iban = 1;</code>
     * @param value The fromIban to set.
     * @return This builder for chaining.
     */
    public Builder setFromIban(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      fromIban_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string from_iban = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFromIban() {
      fromIban_ = getDefaultInstance().getFromIban();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string from_iban = 1;</code>
     * @param value The bytes for fromIban to set.
     * @return This builder for chaining.
     */
    public Builder setFromIbanBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      fromIban_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>string iban = 2;</code>
     * @return Whether the iban field is set.
     */
    @java.lang.Override
    public boolean hasIban() {
      return toCase_ == 2;
    }
    /**
     * <code>string iban = 2;</code>
     * @return The iban.
     */
    @java.lang.Override
    public java.lang.String getIban() {
      java.lang.Object ref = "";
      if (toCase_ == 2) {
        ref = to_;
      }
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (toCase_ == 2) {
          to_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string iban = 2;</code>
     * @return The bytes for iban.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getIbanBytes() {
      java.lang.Object ref = "";
      if (toCase_ == 2) {
        ref = to_;
      }
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        if (toCase_ == 2) {
          to_ = b;
        }
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string iban = 2;</code>
     * @param value The iban to set.
     * @return This builder for chaining.
     */
    public Builder setIban(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      toCase_ = 2;
      to_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string iban = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearIban() {
      if (toCase_ == 2) {
        toCase_ = 0;
        to_ = null;
        onChanged();
      }
      return this;
    }
    /**
     * <code>string iban = 2;</code>
     * @param value The bytes for iban to set.
     * @return This builder for chaining.
     */
    public Builder setIbanBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      toCase_ = 2;
      to_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>string name = 3;</code>
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
      return toCase_ == 3;
    }
    /**
     * <code>string name = 3;</code>
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
      java.lang.Object ref = "";
      if (toCase_ == 3) {
        ref = to_;
      }
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (toCase_ == 3) {
          to_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = "";
      if (toCase_ == 3) {
        ref = to_;
      }
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        if (toCase_ == 3) {
          to_ = b;
        }
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      toCase_ = 3;
      to_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      if (toCase_ == 3) {
        toCase_ = 0;
        to_ = null;
        onChanged();
      }
      return this;
    }
    /**
     * <code>string name = 3;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      toCase_ = 3;
      to_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object amount_ = "";
    /**
     * <code>string amount = 4;</code>
     * @return The amount.
     */
    public java.lang.String getAmount() {
      java.lang.Object ref = amount_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        amount_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string amount = 4;</code>
     * @return The bytes for amount.
     */
    public com.google.protobuf.ByteString
        getAmountBytes() {
      java.lang.Object ref = amount_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        amount_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string amount = 4;</code>
     * @param value The amount to set.
     * @return This builder for chaining.
     */
    public Builder setAmount(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      amount_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string amount = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearAmount() {
      amount_ = getDefaultInstance().getAmount();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string amount = 4;</code>
     * @param value The bytes for amount to set.
     * @return This builder for chaining.
     */
    public Builder setAmountBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      amount_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }

    private java.lang.Object transactionId_ = "";
    /**
     * <code>string transaction_id = 5;</code>
     * @return The transactionId.
     */
    public java.lang.String getTransactionId() {
      java.lang.Object ref = transactionId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        transactionId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string transaction_id = 5;</code>
     * @return The bytes for transactionId.
     */
    public com.google.protobuf.ByteString
        getTransactionIdBytes() {
      java.lang.Object ref = transactionId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        transactionId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string transaction_id = 5;</code>
     * @param value The transactionId to set.
     * @return This builder for chaining.
     */
    public Builder setTransactionId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      transactionId_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>string transaction_id = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearTransactionId() {
      transactionId_ = getDefaultInstance().getTransactionId();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }
    /**
     * <code>string transaction_id = 5;</code>
     * @param value The bytes for transactionId to set.
     * @return This builder for chaining.
     */
    public Builder setTransactionIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      transactionId_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:bankng.CreateTransactionRequest)
  }

  // @@protoc_insertion_point(class_scope:bankng.CreateTransactionRequest)
  private static final bankng.CreateTransactionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new bankng.CreateTransactionRequest();
  }

  public static bankng.CreateTransactionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateTransactionRequest>
      PARSER = new com.google.protobuf.AbstractParser<CreateTransactionRequest>() {
    @java.lang.Override
    public CreateTransactionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<CreateTransactionRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateTransactionRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public bankng.CreateTransactionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

