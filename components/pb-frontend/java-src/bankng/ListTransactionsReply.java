// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bankng.proto

package bankng;

/**
 * Protobuf type {@code bankng.ListTransactionsReply}
 */
public final class ListTransactionsReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:bankng.ListTransactionsReply)
    ListTransactionsReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ListTransactionsReply.newBuilder() to construct.
  private ListTransactionsReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ListTransactionsReply() {
    transactions_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ListTransactionsReply();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return bankng.Bankng.internal_static_bankng_ListTransactionsReply_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return bankng.Bankng.internal_static_bankng_ListTransactionsReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            bankng.ListTransactionsReply.class, bankng.ListTransactionsReply.Builder.class);
  }

  public static final int TRANSACTIONS_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<bankng.Transaction> transactions_;
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  @java.lang.Override
  public java.util.List<bankng.Transaction> getTransactionsList() {
    return transactions_;
  }
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends bankng.TransactionOrBuilder> 
      getTransactionsOrBuilderList() {
    return transactions_;
  }
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  @java.lang.Override
  public int getTransactionsCount() {
    return transactions_.size();
  }
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  @java.lang.Override
  public bankng.Transaction getTransactions(int index) {
    return transactions_.get(index);
  }
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  @java.lang.Override
  public bankng.TransactionOrBuilder getTransactionsOrBuilder(
      int index) {
    return transactions_.get(index);
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
    for (int i = 0; i < transactions_.size(); i++) {
      output.writeMessage(1, transactions_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < transactions_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, transactions_.get(i));
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
    if (!(obj instanceof bankng.ListTransactionsReply)) {
      return super.equals(obj);
    }
    bankng.ListTransactionsReply other = (bankng.ListTransactionsReply) obj;

    if (!getTransactionsList()
        .equals(other.getTransactionsList())) return false;
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
    if (getTransactionsCount() > 0) {
      hash = (37 * hash) + TRANSACTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getTransactionsList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static bankng.ListTransactionsReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static bankng.ListTransactionsReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static bankng.ListTransactionsReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static bankng.ListTransactionsReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static bankng.ListTransactionsReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static bankng.ListTransactionsReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static bankng.ListTransactionsReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static bankng.ListTransactionsReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static bankng.ListTransactionsReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static bankng.ListTransactionsReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static bankng.ListTransactionsReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static bankng.ListTransactionsReply parseFrom(
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
  public static Builder newBuilder(bankng.ListTransactionsReply prototype) {
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
   * Protobuf type {@code bankng.ListTransactionsReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:bankng.ListTransactionsReply)
      bankng.ListTransactionsReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return bankng.Bankng.internal_static_bankng_ListTransactionsReply_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return bankng.Bankng.internal_static_bankng_ListTransactionsReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              bankng.ListTransactionsReply.class, bankng.ListTransactionsReply.Builder.class);
    }

    // Construct using bankng.ListTransactionsReply.newBuilder()
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
      if (transactionsBuilder_ == null) {
        transactions_ = java.util.Collections.emptyList();
      } else {
        transactions_ = null;
        transactionsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return bankng.Bankng.internal_static_bankng_ListTransactionsReply_descriptor;
    }

    @java.lang.Override
    public bankng.ListTransactionsReply getDefaultInstanceForType() {
      return bankng.ListTransactionsReply.getDefaultInstance();
    }

    @java.lang.Override
    public bankng.ListTransactionsReply build() {
      bankng.ListTransactionsReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public bankng.ListTransactionsReply buildPartial() {
      bankng.ListTransactionsReply result = new bankng.ListTransactionsReply(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(bankng.ListTransactionsReply result) {
      if (transactionsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          transactions_ = java.util.Collections.unmodifiableList(transactions_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.transactions_ = transactions_;
      } else {
        result.transactions_ = transactionsBuilder_.build();
      }
    }

    private void buildPartial0(bankng.ListTransactionsReply result) {
      int from_bitField0_ = bitField0_;
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
      if (other instanceof bankng.ListTransactionsReply) {
        return mergeFrom((bankng.ListTransactionsReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(bankng.ListTransactionsReply other) {
      if (other == bankng.ListTransactionsReply.getDefaultInstance()) return this;
      if (transactionsBuilder_ == null) {
        if (!other.transactions_.isEmpty()) {
          if (transactions_.isEmpty()) {
            transactions_ = other.transactions_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureTransactionsIsMutable();
            transactions_.addAll(other.transactions_);
          }
          onChanged();
        }
      } else {
        if (!other.transactions_.isEmpty()) {
          if (transactionsBuilder_.isEmpty()) {
            transactionsBuilder_.dispose();
            transactionsBuilder_ = null;
            transactions_ = other.transactions_;
            bitField0_ = (bitField0_ & ~0x00000001);
            transactionsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getTransactionsFieldBuilder() : null;
          } else {
            transactionsBuilder_.addAllMessages(other.transactions_);
          }
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
              bankng.Transaction m =
                  input.readMessage(
                      bankng.Transaction.parser(),
                      extensionRegistry);
              if (transactionsBuilder_ == null) {
                ensureTransactionsIsMutable();
                transactions_.add(m);
              } else {
                transactionsBuilder_.addMessage(m);
              }
              break;
            } // case 10
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
    private int bitField0_;

    private java.util.List<bankng.Transaction> transactions_ =
      java.util.Collections.emptyList();
    private void ensureTransactionsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        transactions_ = new java.util.ArrayList<bankng.Transaction>(transactions_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        bankng.Transaction, bankng.Transaction.Builder, bankng.TransactionOrBuilder> transactionsBuilder_;

    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public java.util.List<bankng.Transaction> getTransactionsList() {
      if (transactionsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(transactions_);
      } else {
        return transactionsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public int getTransactionsCount() {
      if (transactionsBuilder_ == null) {
        return transactions_.size();
      } else {
        return transactionsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public bankng.Transaction getTransactions(int index) {
      if (transactionsBuilder_ == null) {
        return transactions_.get(index);
      } else {
        return transactionsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder setTransactions(
        int index, bankng.Transaction value) {
      if (transactionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTransactionsIsMutable();
        transactions_.set(index, value);
        onChanged();
      } else {
        transactionsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder setTransactions(
        int index, bankng.Transaction.Builder builderForValue) {
      if (transactionsBuilder_ == null) {
        ensureTransactionsIsMutable();
        transactions_.set(index, builderForValue.build());
        onChanged();
      } else {
        transactionsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder addTransactions(bankng.Transaction value) {
      if (transactionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTransactionsIsMutable();
        transactions_.add(value);
        onChanged();
      } else {
        transactionsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder addTransactions(
        int index, bankng.Transaction value) {
      if (transactionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTransactionsIsMutable();
        transactions_.add(index, value);
        onChanged();
      } else {
        transactionsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder addTransactions(
        bankng.Transaction.Builder builderForValue) {
      if (transactionsBuilder_ == null) {
        ensureTransactionsIsMutable();
        transactions_.add(builderForValue.build());
        onChanged();
      } else {
        transactionsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder addTransactions(
        int index, bankng.Transaction.Builder builderForValue) {
      if (transactionsBuilder_ == null) {
        ensureTransactionsIsMutable();
        transactions_.add(index, builderForValue.build());
        onChanged();
      } else {
        transactionsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder addAllTransactions(
        java.lang.Iterable<? extends bankng.Transaction> values) {
      if (transactionsBuilder_ == null) {
        ensureTransactionsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, transactions_);
        onChanged();
      } else {
        transactionsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder clearTransactions() {
      if (transactionsBuilder_ == null) {
        transactions_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        transactionsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public Builder removeTransactions(int index) {
      if (transactionsBuilder_ == null) {
        ensureTransactionsIsMutable();
        transactions_.remove(index);
        onChanged();
      } else {
        transactionsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public bankng.Transaction.Builder getTransactionsBuilder(
        int index) {
      return getTransactionsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public bankng.TransactionOrBuilder getTransactionsOrBuilder(
        int index) {
      if (transactionsBuilder_ == null) {
        return transactions_.get(index);  } else {
        return transactionsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public java.util.List<? extends bankng.TransactionOrBuilder> 
         getTransactionsOrBuilderList() {
      if (transactionsBuilder_ != null) {
        return transactionsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(transactions_);
      }
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public bankng.Transaction.Builder addTransactionsBuilder() {
      return getTransactionsFieldBuilder().addBuilder(
          bankng.Transaction.getDefaultInstance());
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public bankng.Transaction.Builder addTransactionsBuilder(
        int index) {
      return getTransactionsFieldBuilder().addBuilder(
          index, bankng.Transaction.getDefaultInstance());
    }
    /**
     * <code>repeated .bankng.Transaction transactions = 1;</code>
     */
    public java.util.List<bankng.Transaction.Builder> 
         getTransactionsBuilderList() {
      return getTransactionsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        bankng.Transaction, bankng.Transaction.Builder, bankng.TransactionOrBuilder> 
        getTransactionsFieldBuilder() {
      if (transactionsBuilder_ == null) {
        transactionsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            bankng.Transaction, bankng.Transaction.Builder, bankng.TransactionOrBuilder>(
                transactions_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        transactions_ = null;
      }
      return transactionsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:bankng.ListTransactionsReply)
  }

  // @@protoc_insertion_point(class_scope:bankng.ListTransactionsReply)
  private static final bankng.ListTransactionsReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new bankng.ListTransactionsReply();
  }

  public static bankng.ListTransactionsReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListTransactionsReply>
      PARSER = new com.google.protobuf.AbstractParser<ListTransactionsReply>() {
    @java.lang.Override
    public ListTransactionsReply parsePartialFrom(
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

  public static com.google.protobuf.Parser<ListTransactionsReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListTransactionsReply> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public bankng.ListTransactionsReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

