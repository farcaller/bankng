// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: accounts.proto

package bankng;

public final class AccountsOuterClass {
  private AccountsOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
    registry.add(bankng.AccountsOuterClass.requiresAuth);
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public static final int REQUIRES_AUTH_FIELD_NUMBER = 50001;
  /**
   * <code>extend .google.protobuf.MethodOptions { ... }</code>
   */
  public static final
    com.google.protobuf.GeneratedMessage.GeneratedExtension<
      com.google.protobuf.DescriptorProtos.MethodOptions,
      java.lang.Boolean> requiresAuth = com.google.protobuf.GeneratedMessage
          .newFileScopedGeneratedExtension(
        java.lang.Boolean.class,
        null);
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_ListAccountsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_ListAccountsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_ListAccountsReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_ListAccountsReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_Account_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_Account_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_ListTransactionsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_ListTransactionsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_ListTransactionsReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_ListTransactionsReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_Transaction_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_Transaction_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bankng_Correspondent_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_bankng_Correspondent_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016accounts.proto\022\006bankng\032\037google/protobu" +
      "f/timestamp.proto\032 google/protobuf/descr" +
      "iptor.proto\"\025\n\023ListAccountsRequest\"6\n\021Li" +
      "stAccountsReply\022!\n\010accounts\030\001 \003(\0132\017.bank" +
      "ng.Account\"_\n\007Account\022\014\n\004iban\030\001 \001(\t\022\014\n\004n" +
      "ame\030\002 \001(\t\022\020\n\010currency\030\003 \001(\t\022\025\n\rcurrency_" +
      "code\030\004 \001(\t\022\017\n\007balance\030\005 \001(\t\"\'\n\027ListTrans" +
      "actionsRequest\022\014\n\004iban\030\001 \001(\t\"B\n\025ListTran" +
      "sactionsReply\022)\n\014transactions\030\001 \003(\0132\023.ba" +
      "nkng.Transaction\"z\n\013Transaction\022,\n\rcorre" +
      "spondent\030\001 \001(\0132\025.bankng.Correspondent\022\016\n" +
      "\006amount\030\002 \001(\t\022-\n\ttimestamp\030\005 \001(\0132\032.googl" +
      "e.protobuf.Timestamp\".\n\rCorrespondent\022\014\n" +
      "\004name\030\001 \001(\t\022\017\n\007pfp_url\030\002 \001(\t2\262\001\n\010Account" +
      "s\022L\n\014ListAccounts\022\033.bankng.ListAccountsR" +
      "equest\032\031.bankng.ListAccountsReply\"\004\210\265\030\001\022" +
      "X\n\020ListTransactions\022\037.bankng.ListTransac" +
      "tionsRequest\032\035.bankng.ListTransactionsRe" +
      "ply\"\004\210\265\030\001:7\n\rrequires_auth\022\036.google.prot" +
      "obuf.MethodOptions\030\321\206\003 \001(\010B\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
          com.google.protobuf.DescriptorProtos.getDescriptor(),
        });
    internal_static_bankng_ListAccountsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_bankng_ListAccountsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_ListAccountsRequest_descriptor,
        new java.lang.String[] { });
    internal_static_bankng_ListAccountsReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_bankng_ListAccountsReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_ListAccountsReply_descriptor,
        new java.lang.String[] { "Accounts", });
    internal_static_bankng_Account_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_bankng_Account_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_Account_descriptor,
        new java.lang.String[] { "Iban", "Name", "Currency", "CurrencyCode", "Balance", });
    internal_static_bankng_ListTransactionsRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_bankng_ListTransactionsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_ListTransactionsRequest_descriptor,
        new java.lang.String[] { "Iban", });
    internal_static_bankng_ListTransactionsReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_bankng_ListTransactionsReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_ListTransactionsReply_descriptor,
        new java.lang.String[] { "Transactions", });
    internal_static_bankng_Transaction_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_bankng_Transaction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_Transaction_descriptor,
        new java.lang.String[] { "Correspondent", "Amount", "Timestamp", });
    internal_static_bankng_Correspondent_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_bankng_Correspondent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_bankng_Correspondent_descriptor,
        new java.lang.String[] { "Name", "PfpUrl", });
    requiresAuth.internalInit(descriptor.getExtensions().get(0));
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(bankng.AccountsOuterClass.requiresAuth);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.DescriptorProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}