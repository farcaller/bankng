// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bankng.proto

package bankng;

public interface ListTransactionsReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:bankng.ListTransactionsReply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  java.util.List<bankng.Transaction> 
      getTransactionsList();
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  bankng.Transaction getTransactions(int index);
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  int getTransactionsCount();
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  java.util.List<? extends bankng.TransactionOrBuilder> 
      getTransactionsOrBuilderList();
  /**
   * <code>repeated .bankng.Transaction transactions = 1;</code>
   */
  bankng.TransactionOrBuilder getTransactionsOrBuilder(
      int index);
}
